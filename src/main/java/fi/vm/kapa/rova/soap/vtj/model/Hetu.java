package fi.vm.kapa.rova.soap.vtj.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement(name = "Henkilotunnus")
@XmlAccessorType(XmlAccessType.FIELD)
public class Hetu {

	@XmlValue
	private String hetu;

	@XmlAttribute(name = "voimassaolokoodi")
	private String validityCode;

	public String getHetu() {
		return hetu;
	}

	public void setHetu(String hetu) {
		this.hetu = hetu;
	}

	public String getValidityCode() {
		return validityCode;
	}

	public void setValidityCode(String validityCode) {
		this.validityCode = validityCode;
	}

	@Override
	public String toString() {
		return "Hetu [hetu=" + hetu + ", validityCode=" + validityCode + "]";
	}

}
