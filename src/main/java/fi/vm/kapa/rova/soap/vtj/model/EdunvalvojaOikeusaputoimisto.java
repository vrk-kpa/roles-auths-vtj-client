package fi.vm.kapa.rova.soap.vtj.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "OikeusaputoimistoEdunvalvoja")
@XmlAccessorType(XmlAccessType.FIELD)
public class EdunvalvojaOikeusaputoimisto {

	@XmlElement(name = "Viranomaisnumero")
	private StringNode viranomaisnumero;

	@XmlElement(name = "ViranomainenS")
	private StringNode viranomainenS;
	
	@XmlElement(name = "ViranomainenR")
	private StringNode viranomainenR;

	
	public StringNode getViranomaisnumero() {
		return viranomaisnumero;
	}

	public void setViranomaisnumero(StringNode viranomaisnumero) {
		this.viranomaisnumero = viranomaisnumero;
	}

	public StringNode getViranomainenS() {
		return viranomainenS;
	}

	public void setViranomainenS(StringNode viranomainenS) {
		this.viranomainenS = viranomainenS;
	}

	public StringNode getViranomainenR() {
		return viranomainenR;
	}

	public void setViranomainenR(StringNode viranomainenR) {
		this.viranomainenR = viranomainenR;
	}
	
}
