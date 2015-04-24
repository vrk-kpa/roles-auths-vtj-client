package fi.vm.kapa.rova.soap.vtj.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "HenkiloEdunvalvontavaltuutettu")
@XmlAccessorType(XmlAccessType.FIELD)
public class GuardianshipAuthorizedPerson {

	@XmlElement(name = "Henkilotunnus")
	private StringNode hetu;

	@XmlElement(name = "Syntymaaika")
	private StringNode birthday;

	@XmlElement(name = "NykyinenSukunimi")
	private StringNode lastName;

	@XmlElement(name = "NykyisetEtunimet")
	private StringNode firstName;

	public StringNode getHetu() {
		return hetu;
	}

	public void setHetu(StringNode hetu) {
		this.hetu = hetu;
	}

	public StringNode getBirthday() {
		return birthday;
	}

	public void setBirthday(StringNode birthday) {
		this.birthday = birthday;
	}

	public StringNode getLastName() {
		return lastName;
	}

	public void setLastName(StringNode lastName) {
		this.lastName = lastName;
	}

	public StringNode getFirstName() {
		return firstName;
	}

	public void setFirstName(StringNode firstName) {
		this.firstName = firstName;
	}

}
