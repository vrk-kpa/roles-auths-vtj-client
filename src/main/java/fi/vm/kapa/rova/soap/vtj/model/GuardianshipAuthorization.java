package fi.vm.kapa.rova.soap.vtj.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="Edunvalvontavaltuutus")
@XmlAccessorType(XmlAccessType.FIELD)
public class GuardianshipAuthorization {
	
	@XmlElement(name = "EdunvalvontavaltuutusTieto")
	StringNode guardianshipAuthorization;

	public StringNode getGuardianshipAuthorization() {
		return guardianshipAuthorization;
	}

	public void setGuardianshipAuthorization(StringNode guardianshipAuthorization) {
		this.guardianshipAuthorization = guardianshipAuthorization;
	}
	
	
}
