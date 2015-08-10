package fi.vm.kapa.rova.soap.vtj.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "YritysJaYhteisoEdunvalvoja")
@XmlAccessorType(XmlAccessType.FIELD)
public class EdunvalvojaYritys {

    @XmlElement(name = "Ytunnus")
    private StringNode ytunnus;

    @XmlElement(name = "Nimi")
    private StringNode nimi;

    public StringNode getYtunnus() {
        return ytunnus;
    }

    public void setYtunnus(StringNode ytunnus) {
        this.ytunnus = ytunnus;
    }

    public StringNode getNimi() {
        return nimi;
    }

    public void setNimi(StringNode nimi) {
        this.nimi = nimi;
    }
}
