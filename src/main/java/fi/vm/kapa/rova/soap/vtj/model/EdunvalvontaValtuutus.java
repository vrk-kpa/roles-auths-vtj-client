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

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Edunvalvontavaltuutus")
@XmlAccessorType(XmlAccessType.FIELD)
public class EdunvalvontaValtuutus {

    @XmlElement(name = "Alkupvm")
    private StringNode startDate;

    @XmlElement(name = "EdunvalvontavaltuutusTieto")
    private StringNode edunvalvontaValtuutustieto;

    @XmlElement(name = "HenkiloEdunvalvontavaltuutettu")
    private List<EdunvalvontaValtuutettuHenkilo> edunvalvontaValtuutettuHenkilo;

    public StringNode getEdunvalvontaValtuutustieto() {
        return edunvalvontaValtuutustieto;
    }

    public void setEdunvalvontaValtuutustieto(
            StringNode edunvalvontaValtuutustieto) {
        this.edunvalvontaValtuutustieto = edunvalvontaValtuutustieto;
    }

    public List<EdunvalvontaValtuutettuHenkilo> getEdunvalvontaValtuutettuHenkilo() {
        return edunvalvontaValtuutettuHenkilo;
    }

    public void setEdunvalvontaValtuutettuHenkilo(
            List<EdunvalvontaValtuutettuHenkilo> edunvalvontaValtuutettuHenkilo) {
        this.edunvalvontaValtuutettuHenkilo = edunvalvontaValtuutettuHenkilo;
    }
}
