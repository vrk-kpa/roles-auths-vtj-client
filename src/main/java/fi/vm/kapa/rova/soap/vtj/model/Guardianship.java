package fi.vm.kapa.rova.soap.vtj.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Edunvalvonta")
@XmlAccessorType(XmlAccessType.FIELD)
public class Guardianship {

	@XmlElement(name = "EdunvalvontaTieto")
	private StringNode guardianship;

	@XmlElement(name = "Rajoituskoodi", required = false)
	private StringNode rajoituskoodi;

	@XmlElement(name = "HenkiloEdunvalvoja", required = false)
	private List<GuardianshipPerson> guardianshipPerson;

	public StringNode getGuardianship() {
		return guardianship;
	}

	public void setGuardianship(StringNode guardianship) {
		this.guardianship = guardianship;
	}

	public StringNode getRajoituskoodi() {
		return rajoituskoodi;
	}

	public void setRajoituskoodi(StringNode rajoituskoodi) {
		this.rajoituskoodi = rajoituskoodi;
	}

	public List<GuardianshipPerson> getGuardianshipPerson() {
		return guardianshipPerson;
	}

	public void setGuardianshipPerson(
			List<GuardianshipPerson> guardianshipPerson) {
		this.guardianshipPerson = guardianshipPerson;
	}
}
