/**
 * The MIT License
 * Copyright (c) 2016 Population Register Centre
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package fi.vm.kapa.rova.soap.vtj.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "Henkilo")
@XmlAccessorType(XmlAccessType.FIELD)
public class Person {

    @XmlElement(name = "Henkilotunnus")
    private Hetu hetu;

    @XmlElement(name = "NykyinenSukunimi")
    private CurrentLastName lastName;

    @XmlElement(name = "NykyisetEtunimet")
    private CurrentFirstNames firstName;

    @XmlElement(name = "NykyinenKutsumanimi")
    private CurrentNickName callingName;

    @XmlElement(name = "Huollettava", required = false)
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

    @XmlElement(name = "Huoltotiedot", required = false)
    private Huoltotieto huoltotieto;

    @XmlElement(name = "HuoltajaLkm")
    private StringNode huoltajaMaara;

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
    
    public CurrentNickName getCallingName() {
        return callingName;
    }
    
    public void setCallingName(CurrentNickName callingName) {
        this.callingName = callingName;
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

    public StringNode getHuoltajaMaara() {
        return huoltajaMaara;
    }

    public void setHuoltajaMaara(StringNode huoltajaMaara) {
        this.huoltajaMaara = huoltajaMaara;
    }

    @Override
    public String toString() {
        return "Person [hetu=" + hetu + ", lastName=" + lastName
                + ", firstName=" + firstName + ", principal=" + principal
                + ", deceased=" + deceased + ", edunvalvonta=" + edunvalvonta
                + ", edunvalvontaValtuutus=" + edunvalvontaValtuutus
                + ", huoltaja=" + huoltaja + ", turvakielto=" + turvakielto
                + ", huostaanotto=" + huostaanotto + ", huoltotieto="
                + huoltotieto + ", huoltajaMaara=" + huoltajaMaara + "]";
    }

}
