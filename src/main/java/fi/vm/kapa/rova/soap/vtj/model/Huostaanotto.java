package fi.vm.kapa.rova.soap.vtj.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Huostaanotto")
@XmlAccessorType(XmlAccessType.FIELD)
public class Huostaanotto {
	@XmlElement(name = "HuostaanottoTieto")
	private StringNode huostaanottoTieto;

	public StringNode getHuostaanottoTieto() {
		return this.huostaanottoTieto;
	}

	public void setHuostaanottoTieto(StringNode huostaanottoTieto) {
		this.huostaanottoTieto = huostaanottoTieto;
	}
}
