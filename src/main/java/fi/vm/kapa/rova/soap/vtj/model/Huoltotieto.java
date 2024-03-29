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

@XmlRootElement(name = "Huoltotiedot")
@XmlAccessorType(XmlAccessType.FIELD)
public class Huoltotieto {

    @XmlElement(name = "Huollonjakokoodi")
    private StringNode custodyDivisionCode;

    @XmlElement(name = "Huollonjakokoodi2")
    private StringNode custodyDivisionCode2;

    public StringNode getCustodyDivisionCode() {
        return custodyDivisionCode;
    }

    public void setCustodyDivisionCode(StringNode custodyDivisionCode) {
        this.custodyDivisionCode = custodyDivisionCode;
    }

    public StringNode getCustodyDivisionCode2() {
        return custodyDivisionCode2;
    }

    public void setCustodyDivisionCode2(StringNode custodyDivisionCode2) {
        this.custodyDivisionCode2 = custodyDivisionCode2;
    }

    @Override
    public String toString() {
        return "Huoltotieto{" +
                "custodyDivisionCode=" + custodyDivisionCode +
                ", custodyDivisionCode2=" + custodyDivisionCode2 +
                '}';
    }

}
