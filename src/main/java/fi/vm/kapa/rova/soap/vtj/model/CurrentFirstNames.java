package fi.vm.kapa.rova.soap.vtj.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "NykyisetEtunimet")
@XmlAccessorType(XmlAccessType.FIELD)
public class CurrentFirstNames {
	
	@XmlElement(name= "Etunimet")
	private StringNode firstName;

	public StringNode getFirstName() {
		return firstName;
	}

	public void setFirstName(StringNode firstName) {
		this.firstName = firstName;
	}

	@Override
	public String toString() {
		return "CurrentFirstName [firstName=" + firstName + "]";
	}
}
