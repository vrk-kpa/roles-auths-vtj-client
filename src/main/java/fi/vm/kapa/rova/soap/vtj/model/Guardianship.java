package fi.vm.kapa.rova.soap.vtj.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="Edunvalvonta")
@XmlAccessorType(XmlAccessType.FIELD)
public class Guardianship {
	
	@XmlElement(name = "EdunvalvontaTieto")
	StringNode guardianship;

	public StringNode getGuardianship() {
		return guardianship;
	}

	public void setGuardianship(StringNode guardianship) {
		this.guardianship = guardianship;
	}

}
