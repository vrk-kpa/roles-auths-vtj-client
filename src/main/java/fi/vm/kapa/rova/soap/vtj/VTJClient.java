package fi.vm.kapa.rova.soap.vtj;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.jws.WebService;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;

import fi.vm.kapa.rova.config.SpringPropertyNames;
import fi.vm.kapa.rova.logging.Logger;
import fi.vm.kapa.rova.soap.handlers.XroadHeaderHandler;
import fi.vm.kapa.rova.soap.vtj.model.VTJResponseMessage;
import fi.vrk.xml.rova.vtj.HenkiloTunnusKyselyReqBodyTiedot;
import fi.vrk.xml.rova.vtj.HenkiloTunnusKyselyResType;
import fi.vrk.xml.rova.vtj.ISoSoAdapterService60;
import fi.vrk.xml.rova.vtj.ObjectFactory;
import fi.vrk.xml.rova.vtj.SoSoAdapterService60;

@Component
@WebService(endpointInterface = "fi.vrk.xml.rova.vtj.ISoSoAdapterService60")
public class VTJClient implements SpringPropertyNames {

    SoSoAdapterService60 service = new SoSoAdapterService60();
    ObjectFactory factory = new ObjectFactory();

    @Autowired
    private XroadHeaderHandler xroadHeaderHandler;

    @Value(VTJ_USERNAME)
    private String vtjUsername;
    @Value(VTJ_PASSWORD)
    private String vtjPassword;
    @Value(XROAD_ENDPOINT)
    private String xrdEndPoint;

    private static Logger LOG = Logger.getLogger(VTJClient.class, Logger.VTJ_CLIENT);

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

    public VTJResponseMessage getResponse(String hetu, String schema, String origUserId, String origRequestId) throws JAXBException {
        LOG.debug("VTJClient.getResponse() starts");
        ISoSoAdapterService60 iService = service.getBasicHttpBindingISoSoAdapterService60();
        BindingProvider bp = (BindingProvider) iService;

        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, xrdEndPoint);
        bp.getRequestContext().put(XroadHeaderHandler.ORIG_USERID_HEADER, origUserId);
        bp.getRequestContext().put(XroadHeaderHandler.ORIG_REQUEST_ID_HEADER, origRequestId);

        HenkiloTunnusKyselyReqBodyTiedot reqBodyTiedot = factory.createHenkiloTunnusKyselyReqBodyTiedot();
        reqBodyTiedot.setHenkilotunnus(hetu);
        reqBodyTiedot.setKayttajatunnus(vtjUsername);
        reqBodyTiedot.setSalasana(vtjPassword);
        reqBodyTiedot.setSoSoNimi(schema);
        reqBodyTiedot.setLoppukayttaja(origUserId == null ? "rova-end-user-unknown" :  origUserId);

        Holder<HenkiloTunnusKyselyReqBodyTiedot> request = new Holder<HenkiloTunnusKyselyReqBodyTiedot>(reqBodyTiedot);

        HenkiloTunnusKyselyResType resType = factory.createHenkiloTunnusKyselyResType();
        Holder<HenkiloTunnusKyselyResType> response = new Holder<HenkiloTunnusKyselyResType>(resType);
        iService.henkilonTunnusKysely(request, response);

        resType = response.value;
        List<Object> list = resType.getAny();
        for (Object o : list) {
            JAXBContext context = JAXBContext
                    .newInstance(VTJResponseMessage.class);
            Unmarshaller um = context.createUnmarshaller();
            um.setEventHandler(new CustomValidationEventHandler());

            return (VTJResponseMessage) um.unmarshal((Node) o);
        }
        return null;
    }

}
