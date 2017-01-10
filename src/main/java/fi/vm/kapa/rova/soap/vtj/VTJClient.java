/**
 * The MIT License
 * Copyright (c) 2016 Population Register Centre
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package fi.vm.kapa.rova.soap.vtj;

import static org.apache.commons.lang3.StringUtils.isBlank;

import fi.vm.kapa.rova.config.SpringPropertyNames;
import fi.vm.kapa.rova.logging.Logger;
import fi.vm.kapa.rova.rest.identification.RequestIdentificationFilter;
import fi.vm.kapa.rova.soap.vtj.model.StringNode;
import fi.vm.kapa.rova.soap.vtj.model.VTJResponseMessage;
import fi.vm.kapa.rova.soap.vtj.model.faultCode;
import fi.vm.kapa.rova.soap.vtj.model.faultString;
import fi.vm.kapa.rova.vtjclient.service.VTJServiceException;
import fi.vrk.xml.ws.vtj.vtjkysely._1.HenkiloTunnusKyselyReqBodyTiedot;
import fi.vrk.xml.ws.vtj.vtjkysely._1.HenkiloTunnusKyselyResType;
import fi.vrk.xml.ws.vtj.vtjkysely._1.ISoSoAdapterService60;
import fi.vrk.xml.ws.vtj.vtjkysely._1.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;

import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;
import javax.xml.ws.Holder;

import java.util.List;

@Component
@WebService(endpointInterface = "fi.vrk.xml.rova.vtj.ISoSoAdapterService60")
public class VTJClient implements SpringPropertyNames {
    private static Logger LOG = Logger.getLogger(VTJClient.class);

    public static final String FAULT_CODE_NAME = "faultCode";
    public static final String FAULT_CODE_MISSING = "6666";
    public static final String FAULT_CODE_UNKNOWN = "7777";
    public static final String FAULT_CODE_PARSE_FAILED = "8888";
    public static final String FAULT_STRING_NAME = "faultString";
    
    private ObjectFactory factory = new ObjectFactory();
    private JAXBContext context;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    ISoSoAdapterService60 vtjClient;

    @Value(VTJ_USERNAME)
    private String vtjUsername;
    @Value(VTJ_PASSWORD)
    private String vtjPassword;
    @Value(XROAD_ENDPOINT)
    private String xrdEndPoint;

    public VTJClient() throws JAXBException {
        context = JAXBContext.newInstance(VTJResponseMessage.class);
    }

    public VTJResponseMessage getResponse(String hetu, String schema) {
        LOG.debug("VTJClient.getResponse() starts");
        ISoSoAdapterService60 iService = vtjClient;

        HenkiloTunnusKyselyReqBodyTiedot reqBodyTiedot = factory.createHenkiloTunnusKyselyReqBodyTiedot();
        reqBodyTiedot.setHenkilotunnus(hetu);
        reqBodyTiedot.setKayttajatunnus(vtjUsername);
        reqBodyTiedot.setSalasana(vtjPassword);
        reqBodyTiedot.setSoSoNimi(schema);
        
        String origUserId = request.getHeader(RequestIdentificationFilter.ORIG_END_USER);
        if (origUserId == null) {
            throw new IllegalArgumentException("End user header missing");
        }
        reqBodyTiedot.setLoppukayttaja(origUserId);

        Holder<HenkiloTunnusKyselyReqBodyTiedot> request = new Holder<HenkiloTunnusKyselyReqBodyTiedot>(reqBodyTiedot);

        HenkiloTunnusKyselyResType resType = factory.createHenkiloTunnusKyselyResType();
        Holder<HenkiloTunnusKyselyResType> response = new Holder<HenkiloTunnusKyselyResType>(resType);
        iService.henkilonTunnusKysely(request, response);

        VTJResponseMessage result = new VTJResponseMessage();

        resType = response.value;
        List<Object> list = resType.getAny();

        if (list == null || list.isEmpty()) {
            result.setFaultCode(FAULT_CODE_MISSING);
            result.setFaultString("VTJ error: missing response content");
        } else {
            Unmarshaller um = null;
            try {
                um = context.createUnmarshaller();
                um.setEventHandler(new CustomValidationEventHandler());
                result = (VTJResponseMessage) um.unmarshal((Node) list.get(0));
            } catch (Exception e) {
                fetchFault(list, result);
            }
        }

        return result;
    }

    @SuppressWarnings({ "rawtypes" })
    private void fetchFault(List<Object> list, VTJResponseMessage result) {
        try {
            Unmarshaller um = JAXBContext.newInstance().createUnmarshaller();
            um.setEventHandler(new CustomValidationEventHandler());
            for (int i = 0; i < list.size(); i++) {
                JAXBElement element = (JAXBElement) um.unmarshal((Node) list.get(i), String.class);
                if (FAULT_CODE_NAME.equals(element.getName().getLocalPart())) {
                    result.setFaultCode((String)element.getValue());
                } else if (FAULT_STRING_NAME.equals(element.getName().getLocalPart())) {
                    result.setFaultString((String)element.getValue());
                }
            }

            if (isBlank(result.getFaultCode())) {
                result.setFaultCode(FAULT_CODE_UNKNOWN);
            }
            if (result.getFaultString() == null) {
                if (result.getFaultCode().equals(FAULT_CODE_UNKNOWN)) {
                    result.setFaultString("VTJ error with unidentified fault type");
                } else {
                    result.setFaultString("VTJ error with unidentifed fault string");
                }
            }
        } catch (Exception ex) {
            result.setFaultCode(FAULT_CODE_PARSE_FAILED);
            result.setFaultString(ex.getMessage());
        }
    }

}
