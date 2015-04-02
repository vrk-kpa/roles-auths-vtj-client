package fi.vm.kapa.rova.soap.vtj.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Huoltotiedot")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustodyInformation {

	@XmlElement(name = "Huollonjakokoodi")
	private StringNode custodyDivisionCode;

	public StringNode getCustodyDivisionCode() {
		return custodyDivisionCode;
	}

	public void setCustodyDivisionCode(StringNode custodyDivisionCode) {
		this.custodyDivisionCode = custodyDivisionCode;
	}

	@Override
	public String toString() {
		return "CustodyInformation [custodyDivisionCode=" + custodyDivisionCode
				+ "]";
	}

}
