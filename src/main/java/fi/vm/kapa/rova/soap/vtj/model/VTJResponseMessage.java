package fi.vm.kapa.rova.soap.vtj.model;

import java.util.List;

import javax.xml.bind.Element;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "VTJHenkiloVastaussanoma", namespace = "http://xml.vrk.fi/schema/vtjkysely")
@XmlAccessorType(XmlAccessType.FIELD)
public class VTJResponseMessage {

	@XmlElement(name = "Henkilo")
	private Person person;

	@XmlElement(name = "Paluukoodi")
	private ResponseCode responseCode;
	
	@XmlAttribute(name = "tietojenPoimintaaika")
	private String dataTimeStamp;

	@XmlAttribute(name = "sanomatunnus")
	private VTJSchema messageID;

	@XmlAttribute(name = "versio")
	private String version;

	public String getDataTimeStamp() {
		return dataTimeStamp;
	}

	public void setDataTimeStamp(String dataTimeStamp) {
		this.dataTimeStamp = dataTimeStamp;
	}

	public VTJSchema getMessageID() {
		return messageID;
	}

	public void setMessageID(VTJSchema messageID) {
		this.messageID = messageID;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public ResponseCode getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(ResponseCode responseCode) {
		this.responseCode = responseCode;
	}

	@Override
	public String toString() {
		return "VTJResponseMessage [person=" + person + ", responseCode="
				+ responseCode + ", dataTimeStamp=" + dataTimeStamp
				+ ", messageID=" + messageID + ", version=" + version + "]";
	}



}
