package fi.vm.kapa.rova.soap.vtj.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "HuoltajaLkm")
@XmlAccessorType(XmlAccessType.FIELD)
public class HuoltajaMaara {

    private String custodyCount;

    public String getCustodyCount() {
        return custodyCount;
    }

    public void setCustodyCount(String custodyCount) {
        this.custodyCount = custodyCount;
    }
}
