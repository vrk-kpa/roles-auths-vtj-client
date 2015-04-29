package fi.vm.kapa.rova.soap.vtj.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Edunvalvontavaltuutus")
@XmlAccessorType(XmlAccessType.FIELD)
public class GuardianshipAuthorization {

	@XmlElement(name = "Alkupvm")
	private StringNode startDate;
	
	@XmlElement(name = "EdunvalvontavaltuutusTieto")
	private StringNode guardianshipAuthorization;

	@XmlElement(name = "HenkiloEdunvalvontavaltuutettu")
	private List<GuardianshipAuthorizedPerson> guardianshipAuthorizedPerson;

	public StringNode getGuardianshipAuthorization() {
		return guardianshipAuthorization;
	}

	public void setGuardianshipAuthorization(
			StringNode guardianshipAuthorization) {
		this.guardianshipAuthorization = guardianshipAuthorization;
	}

	public List<GuardianshipAuthorizedPerson> getGuardianshipAuthorizedPerson() {
		return guardianshipAuthorizedPerson;
	}

	public void setGuardianshipAuthorizedPerson(
			List<GuardianshipAuthorizedPerson> guardianshipAuthorizedPerson) {
		this.guardianshipAuthorizedPerson = guardianshipAuthorizedPerson;
	}
}