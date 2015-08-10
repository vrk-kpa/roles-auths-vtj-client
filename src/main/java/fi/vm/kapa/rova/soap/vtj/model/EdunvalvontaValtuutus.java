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
