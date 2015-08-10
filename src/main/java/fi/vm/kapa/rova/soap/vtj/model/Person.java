package fi.vm.kapa.rova.soap.vtj.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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

    @XmlElement(name = "Edunvalvonta", required = false)
    private Edunvalvonta edunvalvonta;

    @XmlElement(name = "Edunvalvontavaltuutus", required = false)
    private EdunvalvontaValtuutus edunvalvontaValtuutus;

    @XmlElement(name = "Huoltaja", required = false)
    private List<Huoltaja> huoltaja;

    @XmlElement(name = "Turvakielto", required = false)
    private Turvakielto turvakielto;

    @XmlElement(name = "Huostaanotto", required = false)
    private Huostaanotto huostaanotto;

    @XmlElement(name = "Huoltotiedot")
    private Huoltotieto huoltotieto;

    @XmlElement(name = "HuoltajaLkm")
    private HuoltajaMaara huoltajaMaara;

    public List<Huoltaja> getHuoltaja() {
        return huoltaja;
    }

    public void setHuoltaja(List<Huoltaja> huoltaja) {
        this.huoltaja = huoltaja;
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

    public Edunvalvonta getEdunvalvonta() {
        return edunvalvonta;
    }

    public void setEdunvalvonta(Edunvalvonta edunvalvonta) {
        this.edunvalvonta = edunvalvonta;
    }

    public EdunvalvontaValtuutus getEdunvalvontaValtuutus() {
        return edunvalvontaValtuutus;
    }

    public void setEdunvalvontaValtuutus(
            EdunvalvontaValtuutus edunvalvontaValtuutus) {
        this.edunvalvontaValtuutus = edunvalvontaValtuutus;
    }

    public Turvakielto getTurvakielto() {
        return turvakielto;
    }

    public void setTurvakielto(Turvakielto turvakielto) {
        this.turvakielto = turvakielto;
    }

    public Huostaanotto getHuostaanotto() {
        return huostaanotto;
    }

    public void setHuostaanotto(Huostaanotto huostaanotto) {
        this.huostaanotto = huostaanotto;
    }

    public Huoltotieto getHuoltotieto() {
        return huoltotieto;
    }

    public void setHuoltotieto(Huoltotieto huoltotieto) {
        this.huoltotieto = huoltotieto;
    }

    public HuoltajaMaara getHuoltajaMaara() {
        return huoltajaMaara;
    }

    public void setHuoltajaMaara(HuoltajaMaara huoltajaMaara) {
        this.huoltajaMaara = huoltajaMaara;
    }

    @Override
    public String toString() {
        return "Person [hetu=" + hetu + ", lastName=" + lastName
                + ", firstName=" + firstName + ", principal=" + principal
                + ", deceased=" + deceased + "]";
    }
}
