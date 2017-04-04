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

import fi.vm.kapa.rova.config.SpringPropertyNames;
import fi.vm.kapa.rova.logging.Logger;
import fi.vm.kapa.rova.rest.identification.RequestIdentificationInterceptor;
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
import javax.xml.bind.JAXBException;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;
import javax.xml.ws.Holder;

import java.util.List;

@Component
@WebService(endpointInterface = "fi.vrk.xml.rova.vtj.ISoSoAdapterService60")
public class VTJClient implements SpringPropertyNames {
    ObjectFactory factory = new ObjectFactory();

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

    private static Logger LOG = Logger.getLogger(VTJClient.class);

    public VTJResponseMessage getResponse(String hetu, String schema) throws JAXBException, VTJServiceException {
        LOG.debug("VTJClient.getResponse() starts");
        ISoSoAdapterService60 iService = vtjClient;

        HenkiloTunnusKyselyReqBodyTiedot reqBodyTiedot = factory.createHenkiloTunnusKyselyReqBodyTiedot();
        reqBodyTiedot.setHenkilotunnus(hetu);
        reqBodyTiedot.setKayttajatunnus(vtjUsername);
        reqBodyTiedot.setSalasana(vtjPassword);
        reqBodyTiedot.setSoSoNimi(schema);
        
        String origUserId = request.getHeader(RequestIdentificationInterceptor.ORIG_END_USER);
        if (origUserId == null) {
            throw new IllegalArgumentException("End user header missing");
        }
        reqBodyTiedot.setLoppukayttaja(origUserId);

        Holder<HenkiloTunnusKyselyReqBodyTiedot> request = new Holder<HenkiloTunnusKyselyReqBodyTiedot>(reqBodyTiedot);

        HenkiloTunnusKyselyResType resType = factory.createHenkiloTunnusKyselyResType();
        Holder<HenkiloTunnusKyselyResType> response = new Holder<HenkiloTunnusKyselyResType>(resType);
        iService.henkilonTunnusKysely(request, response);

        VTJResponseMessage result = null;

        resType = response.value;
        List<Object> list = resType.getAny();
        if (list != null && !list.isEmpty()) {
            JAXBContext context = JAXBContext.newInstance(VTJResponseMessage.class);
            Unmarshaller um = context.createUnmarshaller();
            um.setEventHandler(new CustomValidationEventHandler());
            try {
                result = (VTJResponseMessage) um.unmarshal((Node) list.get(0));
            } catch (UnmarshalException e) {
                JAXBContext errorContext = JAXBContext.newInstance(faultCode.class, faultString.class);
                faultCode code = (faultCode) errorContext.createUnmarshaller()
                        .unmarshal((Node) list.get(0));
                faultString string = (faultString) errorContext.createUnmarshaller()
                        .unmarshal((Node) list.get(1));
                throw new VTJServiceException(code.toString() + " " + string.toString());
            }
        }
        return result;
    }

}
