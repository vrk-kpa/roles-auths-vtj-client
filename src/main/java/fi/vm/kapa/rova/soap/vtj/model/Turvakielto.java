package fi.vm.kapa.rova.soap.vtj.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Turvakielto")
@XmlAccessorType(XmlAccessType.FIELD)
public class Turvakielto {

    @XmlElement(name = "TurvakieltoTieto")
    private StringNode turvakielto;

    public StringNode getTurvakielto() {
        return turvakielto;
    }

    public void setTurvakielto(StringNode turvakielto) {
        this.turvakielto = turvakielto;
    }

}
