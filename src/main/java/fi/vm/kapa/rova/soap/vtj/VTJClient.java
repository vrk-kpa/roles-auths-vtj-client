package fi.vm.kapa.rova.soap.vtj;

import fi.vm.kapa.rova.config.SpringPropertyNames;
import fi.vm.kapa.rova.logging.Logger;
import fi.vm.kapa.rova.rest.identification.RequestIdentificationFilter;
import fi.vm.kapa.rova.soap.handlers.XroadHeaderHandler;
import fi.vm.kapa.rova.soap.vtj.model.VTJResponseMessage;
import fi.vrk.xml.ws.vtj.vtjkysely._1.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;

import javax.annotation.PostConstruct;
import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.ws.Holder;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;
import java.util.ArrayList;
import java.util.List;

@Component
@WebService(endpointInterface = "fi.vrk.xml.rova.vtj.ISoSoAdapterService60")
public class VTJClient implements SpringPropertyNames {

    SoSoAdapterService60 service = new SoSoAdapterService60();
    ObjectFactory factory = new ObjectFactory();

    @Autowired
    private XroadHeaderHandler xroadHeaderHandler;

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

    @PostConstruct
    public void init() {
        HandlerResolver hs = new HandlerResolver() {
            @SuppressWarnings("rawtypes")
            @Override
            public List<Handler> getHandlerChain(PortInfo portInfo) {
                List<Handler> handlers = new ArrayList<Handler>();
                handlers.add(xroadHeaderHandler);
                return handlers;
            }
        };
        service.setHandlerResolver(hs);
    }

    public VTJResponseMessage getResponse(String hetu, String schema) throws JAXBException {
        LOG.debug("VTJClient.getResponse() starts");
        ISoSoAdapterService60 iService = vtjClient;

        HenkiloTunnusKyselyReqBodyTiedot reqBodyTiedot = factory.createHenkiloTunnusKyselyReqBodyTiedot();
        reqBodyTiedot.setHenkilotunnus(hetu);
        reqBodyTiedot.setKayttajatunnus(vtjUsername);
        reqBodyTiedot.setSalasana(vtjPassword);
        reqBodyTiedot.setSoSoNimi(schema);
        
        String origUserId = request.getHeader(RequestIdentificationFilter.XROAD_END_USER);
        if (origUserId == null) {
            origUserId = "rova-end-user-unknown";
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
            JAXBContext context = JAXBContext
                    .newInstance(VTJResponseMessage.class);
            Unmarshaller um = context.createUnmarshaller();
            um.setEventHandler(new CustomValidationEventHandler());
            result =  (VTJResponseMessage) um.unmarshal((Node) list.get(0));
        }
        return result;
    }

}
