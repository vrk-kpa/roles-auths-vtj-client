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

@XmlRootElement(name = "HenkiloEdunvalvontavaltuutettu")
@XmlAccessorType(XmlAccessType.FIELD)
public class EdunvalvontaValtuutettuHenkilo {

    @XmlElement(name = "Henkilotunnus")
    private StringNode hetu;

    @XmlElement(name = "Syntymaaika")
    private StringNode birthday;

    @XmlElement(name = "NykyinenSukunimi")
    private StringNode lastName;

    @XmlElement(name = "NykyisetEtunimet")
    private CurrentFirstNames firstName;

    public StringNode getHetu() {
        return hetu;
    }

    public void setHetu(StringNode hetu) {
        this.hetu = hetu;
    }

    public StringNode getBirthday() {
        return birthday;
    }

    public void setBirthday(StringNode birthday) {
        this.birthday = birthday;
    }

    public StringNode getLastName() {
        return lastName;
    }

    public void setLastName(StringNode lastName) {
        this.lastName = lastName;
    }

    public CurrentFirstNames getFirstName() {
        return firstName;
    }

    public void setFirstName(CurrentFirstNames firstName) {
        this.firstName = firstName;
    }

}
