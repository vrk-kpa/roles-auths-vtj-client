package fi.vm.kapa.rova.soap.vtj.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Kuolintiedot")
@XmlAccessorType(XmlAccessType.FIELD)
public class Deceased {

    @XmlElement(name = "Kuollut")
    private StringNode deceased;

    public StringNode getDeceased() {
        return deceased;
    }

    public void setDeceased(StringNode deceased) {
        this.deceased = deceased;
    }

    @Override
    public String toString() {
        return "Deceased [deceased=" + deceased + "]";
    }
}
