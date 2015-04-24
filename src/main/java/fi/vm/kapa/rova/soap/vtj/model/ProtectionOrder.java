package fi.vm.kapa.rova.soap.vtj.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Turvakielto")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProtectionOrder {
	
	@XmlElement(name = "TurvakieltoTieto")
	private StringNode protectionorder;

	public StringNode getProtectionorder() {
		return protectionorder;
	}

	public void setProtectionorder(StringNode protectionorder) {
		this.protectionorder = protectionorder;
	}

}
