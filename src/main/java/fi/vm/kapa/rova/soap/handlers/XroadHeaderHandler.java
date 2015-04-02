package fi.vm.kapa.rova.soap.handlers;

import static fi.vm.kapa.rova.config.SpringPropertyNames.CLIENT_MEMBER_CLASS;
import static fi.vm.kapa.rova.config.SpringPropertyNames.CLIENT_MEMBER_CODE;
import static fi.vm.kapa.rova.config.SpringPropertyNames.CLIENT_OBJECT_TYPE;
import static fi.vm.kapa.rova.config.SpringPropertyNames.CLIENT_SDSB_INSTANCE;
import static fi.vm.kapa.rova.config.SpringPropertyNames.CLIENT_SUBSYSTEM_CODE;
import static fi.vm.kapa.rova.config.SpringPropertyNames.ID;
import static fi.vm.kapa.rova.config.SpringPropertyNames.SERVICE_MEMBER_CLASS;
import static fi.vm.kapa.rova.config.SpringPropertyNames.SERVICE_MEMBER_CODE;
import static fi.vm.kapa.rova.config.SpringPropertyNames.SERVICE_OBJECT_TYPE;
import static fi.vm.kapa.rova.config.SpringPropertyNames.SERVICE_SDSB_INSTANCE;
import static fi.vm.kapa.rova.config.SpringPropertyNames.SERVICE_SERVICE_CODE;
import static fi.vm.kapa.rova.config.SpringPropertyNames.SERVICE_SUBSYSTEM_CODE;
import static fi.vm.kapa.rova.config.SpringPropertyNames.USER_ID;

import java.util.Collections;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import fi.vrk.xml.rova.vtj.Client;
import fi.vrk.xml.rova.vtj.ObjectFactory;
import fi.vrk.xml.rova.vtj.Service;

@Component
public class XroadHeaderHandler implements SOAPHandler<SOAPMessageContext> {

	private ObjectFactory factory = new ObjectFactory();
	
	public Set<QName> getHeaders() {
		return Collections.emptySet();
	}

	@Value(ID)
	private String id;
	
	@Value(USER_ID)
	private String userId;
	
	@Value(CLIENT_OBJECT_TYPE)
	private String clientObjectType;
	
	@Value(CLIENT_SDSB_INSTANCE)
	private String clientSdsbInstance;
	
	@Value(CLIENT_MEMBER_CLASS)
	private String clientMemberClass;
	
	@Value(CLIENT_MEMBER_CODE)
	private String clientMemberCode;
	
	@Value(CLIENT_SUBSYSTEM_CODE)
	private String clientSubsystemCode;
	
	@Value(SERVICE_OBJECT_TYPE)
	private String serviceObjectType;
	
	@Value(SERVICE_SDSB_INSTANCE)
	private String serviceSdsbInstance;
	
	@Value(SERVICE_MEMBER_CLASS)
	private String serviceMemberClass;
	
	@Value(SERVICE_MEMBER_CODE)
	private String serviceMemberCode;
	
	@Value(SERVICE_SUBSYSTEM_CODE)
	private String serviceSubsystemCode;
	
	@Value(SERVICE_SERVICE_CODE)
	private String serviceServiceCode;
	
	public boolean handleMessage(SOAPMessageContext messageContext) {
		Boolean outboundProperty = (Boolean) messageContext
				.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

		if (outboundProperty.booleanValue()) {
			SOAPMessage soapMsg = messageContext.getMessage();
			SOAPEnvelope soapEnv;
				try {
					soapEnv = soapMsg.getSOAPPart().getEnvelope();
					SOAPHeader header = soapEnv.getHeader();
					if (header == null) {
						header = soapEnv.addHeader();
					}

					JAXBElement<String> idElement = factory.createId(this.id);
					SOAPHeaderElement idHeaderElement = header.addHeaderElement(idElement.getName());
					idHeaderElement.addTextNode((String) idElement.getValue());

					JAXBElement<String> userIdElement = factory.createUserId(this.userId);
					SOAPHeaderElement uidHeaderElement = header.addHeaderElement(userIdElement.getName());
					uidHeaderElement.addTextNode((String) userIdElement.getValue());

					JAXBElement<String> issueElement = factory.createIssue("");
					SOAPHeaderElement issueHeaderElement = header.addHeaderElement(issueElement.getName());
					issueHeaderElement.addTextNode((String) issueElement.getValue());

					Client client = factory.createClient();
					JAXBElement<Client> clientElement = factory.createClient(client);
					client.setObjectType(this.clientObjectType);
					client.setSdsbInstance(this.clientSdsbInstance);
					client.setMemberClass(this.clientMemberClass);
					client.setMemberCode(this.clientMemberCode);
					client.setSubsystemCode(this.clientSubsystemCode);

					Marshaller marshaller;
					marshaller = JAXBContext.newInstance(Client.class).createMarshaller();
					marshaller.marshal(clientElement, header);
					
					Service service = factory.createService();
					JAXBElement<Service> serviceElement = factory.createService(service);
					service.setObjectType(this.serviceObjectType);
					service.setSdsbInstance(this.serviceSdsbInstance);
					service.setMemberClass(this.serviceMemberClass);
					service.setMemberCode(this.serviceMemberCode);
					service.setSubsystemCode(this.serviceSubsystemCode);
					service.setServiceCode(this.serviceServiceCode);
					
					marshaller = JAXBContext.newInstance(Service.class).createMarshaller();
					marshaller.marshal(serviceElement, header);
					
					soapMsg.saveChanges();
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		return true;
	}

	public boolean handleFault(SOAPMessageContext messageContext) {
		return true;
	}

	public void close(MessageContext messageContext) {
	}
}
