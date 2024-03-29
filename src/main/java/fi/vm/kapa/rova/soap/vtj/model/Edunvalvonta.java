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

@XmlRootElement(name = "Edunvalvonta")
@XmlAccessorType(XmlAccessType.FIELD)
public class Edunvalvonta {

    @XmlElement(name = "Alkupvm")
    private StringNode startDate;

    @XmlElement(name = "EdunvalvontaTieto")
    private StringNode edunvalvontaTieto;

    @XmlElement(name = "Rajoituskoodi", required = false)
    private StringNode rajoituskoodi;

    @XmlElement(name = "RajoitustekstiS", required = false)
    private StringNode rajoitustekstiS;

    @XmlElement(name = "RajoitustekstiR", required = false)
    private StringNode rajoitustekstiR;

    @XmlElement(name = "HenkiloEdunvalvoja", required = false)
    private List<EdunvalvojaHenkilo> edunvalvojaHenkilo;

    @XmlElement(name = "OikeusaputoimistoEdunvalvoja", required = false)
    private List<EdunvalvojaOikeusaputoimisto> edunvalvojaOikeusaputoimisto;

    @XmlElement(name = "YritysJaYhteisoEdunvalvoja", required = false)
    private List<EdunvalvojaYritys> edunvalvojaYritys;

    public StringNode getEdunvalvontatieto() {
        return edunvalvontaTieto;
    }

    public void setEdunvalvontatieto(StringNode edunvalvontaTieto) {
        this.edunvalvontaTieto = edunvalvontaTieto;
    }

    public StringNode getRajoituskoodi() {
        return rajoituskoodi;
    }

    public void setRajoituskoodi(StringNode rajoituskoodi) {
        this.rajoituskoodi = rajoituskoodi;
    }

    public List<EdunvalvojaHenkilo> getEdunvalvojaHenkilo() {
        return edunvalvojaHenkilo;
    }

    public void setEdunvalvojaHenkilo(
            List<EdunvalvojaHenkilo> edunvalvojaHenkilo) {
        this.edunvalvojaHenkilo = edunvalvojaHenkilo;
    }
}
