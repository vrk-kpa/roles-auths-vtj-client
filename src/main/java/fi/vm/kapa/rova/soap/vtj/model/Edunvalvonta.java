package fi.vm.kapa.rova.soap.vtj.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Edunvalvonta")
@XmlAccessorType(XmlAccessType.FIELD)
public class Edunvalvonta {

	@XmlElement(name = "Alkupvm")
	private StringNode startDate;
	
	@XmlElement(name = "EdunvalvontaTieto")
	private StringNode edunvalvontaTieto;

	@XmlElement(name = "Rajoituskoodi", required = false)
	private StringNode rajoituskoodi;
	
	@XmlElement(name = "RajoitustekstiS", required = false)
	private StringNode rajoitustekstiS;
	
	@XmlElement(name = "RajoitustekstiR", required = false)
	private StringNode rajoitustekstiR;
	
	@XmlElement(name = "HenkiloEdunvalvoja", required = false)
	private List<EdunvalvojaHenkilo> edunvalvojaHenkilo;
	
	@XmlElement(name = "OikeusaputoimistoEdunvalvoja", required = false)
	private List<EdunvalvojaOikeusaputoimisto> edunvalvojaOikeusaputoimisto;
	
	@XmlElement(name = "YritysJaYhteisoEdunvalvoja", required = false)
	private List<EdunvalvojaYritys> edunvalvojaYritys;
		
	public StringNode getEdunvalvontatieto() {
		return edunvalvontaTieto;
	}

	public void setEdunvalvontatieto(StringNode edunvalvontaTieto) {
		this.edunvalvontaTieto = edunvalvontaTieto;
	}

	public StringNode getRajoituskoodi() {
		return rajoituskoodi;
	}

	public void setRajoituskoodi(StringNode rajoituskoodi) {
		this.rajoituskoodi = rajoituskoodi;
	}

	public List<EdunvalvojaHenkilo> getEdunvalvojaHenkilo() {
		return edunvalvojaHenkilo;
	}

	public void setEdunvalvojaHenkilo(
			List<EdunvalvojaHenkilo> edunvalvojaHenkilo) {
		this.edunvalvojaHenkilo = edunvalvojaHenkilo;
	}
}
