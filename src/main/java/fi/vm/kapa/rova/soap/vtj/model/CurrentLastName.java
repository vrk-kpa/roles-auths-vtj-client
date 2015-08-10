package fi.vm.kapa.rova.soap.vtj.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "NykyinenSukunimi")
@XmlAccessorType(XmlAccessType.FIELD)
public class CurrentLastName {

    @XmlElement(name = "Sukunimi")
    private StringNode lastName;

    public StringNode getLastName() {
        return lastName;
    }

    public void StringNode(StringNode lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "CurrentLastName [lastName=" + lastName + "]";
    }

}
