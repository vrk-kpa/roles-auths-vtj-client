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

@XmlRootElement(name = "Huoltaja")
@XmlAccessorType(XmlAccessType.FIELD)
public class Huoltaja {

    @XmlElement(name = "Henkilotunnus")
    private StringNode id;

    @XmlElement(name = "Syntymaaika")
    private StringNode birthDate;

    @XmlElement(name = "Sukunimi")
    private StringNode lastName;

    @XmlElement(name = "Etunimet")
    private StringNode firstNames;

    @XmlElement(name = "Huoltotiedot")
    private Huoltotieto huoltotieto;

    public StringNode getId() {
        return id;
    }

    public void setId(StringNode id) {
        this.id = id;
    }

    public StringNode getLastName() {
        return lastName;
    }

    public void setLastName(StringNode lastName) {
        this.lastName = lastName;
    }

    public StringNode getFirstNames() {
        return firstNames;
    }

    public void setFirstNames(StringNode firstNames) {
        this.firstNames = firstNames;
    }

    public Huoltotieto getHuoltotieto() {
        return huoltotieto;
    }

    public void setHuoltotieto(Huoltotieto huoltotieto) {
        this.huoltotieto = huoltotieto;
    }

    public StringNode getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(StringNode birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Principal [id=" + id + ", lastName=" + lastName
                + ", firstNames=" + firstNames + "]";
    }

}
