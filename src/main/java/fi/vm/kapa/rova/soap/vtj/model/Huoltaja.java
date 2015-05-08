package fi.vm.kapa.rova.soap.vtj.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Huoltaja")
@XmlAccessorType(XmlAccessType.FIELD)
public class Huoltaja {

	@XmlElement(name = "Henkilotunnus")
	private StringNode id;
	
	@XmlElement(name = "Syntymaaika")
	private StringNode birthDate;

	@XmlElement(name = "Sukunimi")
	private StringNode lastName;

	@XmlElement(name = "Etunimet")
	private StringNode firstNames;

	@XmlElement(name = "Huoltotiedot")
	private Huoltotieto huoltotieto;

	public StringNode getId() {
		return id;
	}

	public void setId(StringNode id) {
		this.id = id;
	}

	public StringNode getLastName() {
		return lastName;
	}

	public void setLastName(StringNode lastName) {
		this.lastName = lastName;
	}

	public StringNode getFirstNames() {
		return firstNames;
	}

	public void setFirstNames(StringNode firstNames) {
		this.firstNames = firstNames;
	}

	public Huoltotieto getHuoltotieto() {
		return huoltotieto;
	}

	public void setHuoltotieto(Huoltotieto huoltotieto) {
		this.huoltotieto = huoltotieto;
	}

	public StringNode getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(StringNode birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "Principal [id=" + id + ", lastName=" + lastName
				+ ", firstNames=" + firstNames + "]";
	}

}
