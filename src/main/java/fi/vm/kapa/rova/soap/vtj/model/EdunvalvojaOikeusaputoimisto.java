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

@XmlRootElement(name = "OikeusaputoimistoEdunvalvoja")
@XmlAccessorType(XmlAccessType.FIELD)
public class EdunvalvojaOikeusaputoimisto {

    @XmlElement(name = "Viranomaisnumero")
    private StringNode viranomaisnumero;

    @XmlElement(name = "ViranomainenS")
    private StringNode viranomainenS;

    @XmlElement(name = "ViranomainenR")
    private StringNode viranomainenR;

    public StringNode getViranomaisnumero() {
        return viranomaisnumero;
    }

    public void setViranomaisnumero(StringNode viranomaisnumero) {
        this.viranomaisnumero = viranomaisnumero;
    }

    public StringNode getViranomainenS() {
        return viranomainenS;
    }

    public void setViranomainenS(StringNode viranomainenS) {
        this.viranomainenS = viranomainenS;
    }

    public StringNode getViranomainenR() {
        return viranomainenR;
    }

    public void setViranomainenR(StringNode viranomainenR) {
        this.viranomainenR = viranomainenR;
    }

}
