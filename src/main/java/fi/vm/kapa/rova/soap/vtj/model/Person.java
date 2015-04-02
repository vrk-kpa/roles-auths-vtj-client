package fi.vm.kapa.rova.soap.vtj.model;

import java.util.List;

import javax.xml.bind.Element;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Henkilo")
@XmlAccessorType(XmlAccessType.FIELD)
public class Person {

	@XmlElement(name = "Henkilotunnus")
	private Hetu hetu;

	@XmlElement(name = "NykyinenSukunimi")
	private CurrentLastName lastName;

	@XmlElement(name = "NykyisetEtunimet")
	private CurrentFirstNames firstName;

	@XmlElement(name = "Huollettava")
	private List<Principal> principal;

	@XmlElement(name = "Kuolintiedot", required = false)
	private Deceased deceased;
	
	@XmlElement(name="Edunvalvonta", required = false)
	private Guardianship guardianship;

	@XmlElement(name = "Huoltaja", required = false)
	private List<Custodian> custodian;
	
	@XmlElement(name = "Turvakielto", required = false)
	private ProtectionOrder protectionorder;
	
	public List<Custodian> getCustodian() {
		return custodian;
	}

	public void setCustodian(List<Custodian> custodian) {
		this.custodian = custodian;
	}

	public Hetu getHetu() {
		return hetu;
	}
	
	public void setHetu(Hetu hetu) {
		this.hetu = hetu;
	}

	public Deceased getDeceased() {
		return deceased;
	}

	public void setDeceased(Deceased deceased) {
		this.deceased = deceased;
	}

	public CurrentFirstNames getFirstName() {
		return firstName;
	}

	public void setFirstName(CurrentFirstNames firstName) {
		this.firstName = firstName;
	}

	public CurrentLastName getLastName() {
		return lastName;
	}

	public void setLastName(CurrentLastName lastName) {
		this.lastName = lastName;
	}

	public List<Principal> getPrincipal() {
		return principal;
	}

	public void setPrincipal(List<Principal> principal) {
		this.principal = principal;
	}
	
	public Guardianship getGuardianship() {
		return guardianship;
	}

	public void setGuardianship(Guardianship guardianship) {
		this.guardianship = guardianship;
	}

	public ProtectionOrder getProtectionorder() {
		return protectionorder;
	}

	public void setProtectionorder(ProtectionOrder protectionorder) {
		this.protectionorder = protectionorder;
	}

	@Override
	public String toString() {
		return "Person [hetu=" + hetu + ", lastName=" + lastName
				+ ", firstName=" + firstName + ", principal=" + principal
				+ ", deceased=" + deceased + "]";
	}
}
