package fi.vm.kapa.rova.vtjclient.service;

import static fi.vm.kapa.rova.logging.Logger.Field.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fi.vm.kapa.rova.logging.Logger;
import fi.vm.kapa.rova.soap.vtj.VTJClient;
import fi.vm.kapa.rova.soap.vtj.model.Huoltaja;
import fi.vm.kapa.rova.soap.vtj.model.EdunvalvontaValtuutettuHenkilo;
import fi.vm.kapa.rova.soap.vtj.model.EdunvalvojaHenkilo;
import fi.vm.kapa.rova.soap.vtj.model.Principal;
import fi.vm.kapa.rova.soap.vtj.model.VTJResponseMessage;
import fi.vm.kapa.rova.vtj.model.Person;
import fi.vm.kapa.rova.vtj.model.VTJResponse;

@Service
public class VTJService {

    private static Logger LOG = Logger.getLogger(VTJService.class);

    @Autowired
    private VTJClient client;

    final int HETU_LENGTH = 11;

    public VTJResponse getVTJResponse(String hetu, String schema) {
        Person person = null;
        VTJResponse vtjResponse = new VTJResponse(); // person == null & success == false as default
        
        try {
            long startTime = System.currentTimeMillis();

            VTJResponseMessage response = client.getResponse(hetu, schema);
            
            logVTJRequest(schema, startTime, System.currentTimeMillis());

            try {
                person = fromSoapMessage(response);
                vtjResponse.setPerson(person);
                vtjResponse.setSuccess(true);
            } catch (Throwable e) {
                logVTJError(schema, "Person parsing failed reason:" + e.getMessage());
                vtjResponse.setError("vtj.parsinta.epaonnistui");
            }
            
        } catch (Throwable e) {
            logVTJError(schema, "VTJ request failed:" + e.getMessage());
            vtjResponse.setError("vtj.haku.epaonnistui");
        }
        
        return vtjResponse;
    }

    private void logVTJError(String schema, String errorString) {
        Logger.LogMap logmap = LOG.errorMap();
        logmap.set(OPERATION, schema);
        logmap.set(ERRORSTR, errorString);
        logmap.log();
    }

    private void logVTJRequest(String schema, long startTime, long currentTimeMillis) {
        Logger.LogMap logmap = LOG.infoMap();
        logmap.set(OPERATION, schema);
        logmap.set(DURATION, currentTimeMillis - startTime);
        logmap.log();
    }

    private Person fromSoapMessage(VTJResponseMessage message) {
        fi.vm.kapa.rova.soap.vtj.model.Person sPerson = message.getPerson();
        Person person = new Person();
        person.setHetu(sPerson.getHetu().getHetu());
        if (sPerson.getHetu().getValidityCode().equals("1")) { // "1" = hetu voimassa
            person.setHetuValid(true);
        } else {
            person.setHetuValid(false);
        }

        if (sPerson.getFirstName() != null) {
            person.setFirstNames(sPerson.getFirstName().getFirstName().getValue());
        }
        if (sPerson.getLastName() != null) {
            person.setLastName(sPerson.getLastName().getLastName().getValue());
        }
        if (sPerson.getDeceased() != null && sPerson.getDeceased().getDeceased() != null
                && sPerson.getDeceased().getDeceased().getValue() != null) {
            person.setDeceased(sPerson.getDeceased().getDeceased().getValue().equals("1")); // "1" = Kuollut 
        } else {
            person.setDeceased(false);
        }

        person.setPrincipals(getPrincipals(sPerson));
        person.setHuoltajat(getHuoltajat(sPerson));
        person.setEdunvalvojat(getEdunvalvojat(sPerson));
        person.setEdunvalvontaValtuutetut(getEdunvalvontaValtuutetut(sPerson));

        if (sPerson.getHuostaanotto() != null && sPerson.getHuostaanotto().getHuostaanottoTieto() != null
                && sPerson.getHuostaanotto().getHuostaanottoTieto().getValue() != null) {
            person.setHuostaanotettu(sPerson.getHuostaanotto().getHuostaanottoTieto()
                    .getValue().equals("1")); // "1" = huostaanotettu
        } else {
            person.setHuostaanotettu(false);
        }

        if (sPerson.getEdunvalvonta() != null && sPerson.getEdunvalvonta().getEdunvalvontatieto() != null
                && sPerson.getEdunvalvonta().getEdunvalvontatieto().getValue() != null) {
            person.setEdunvalvonta(sPerson.getEdunvalvonta().getEdunvalvontatieto().getValue().equals("1")); // "1" = Edunvalvonnassa

            person.setEdunvalvontaEiRajoitettu(false);
            person.setEdunvalvontaRajoitettu(false);
            person.setEdunvalvontaJulistettu(false);

            if (sPerson.getEdunvalvonta().getRajoituskoodi() != null && sPerson.getEdunvalvonta().getRajoituskoodi().getValue() != null) {
                if (person.isEdunvalvonta() && sPerson.getEdunvalvonta().getRajoituskoodi().getValue().equals("1")) { // "1" = ei rajoitettu
                    person.setEdunvalvontaEiRajoitettu(true);
                } else if (person.isEdunvalvonta() && sPerson.getEdunvalvonta().getRajoituskoodi().getValue().equals("2")) { // "2" = rajoitettu
                    person.setEdunvalvontaRajoitettu(true);
                } else if (person.isEdunvalvonta() && sPerson.getEdunvalvonta().getRajoituskoodi().getValue().equals("3")) { // "3" = julistettu
                    person.setEdunvalvontaJulistettu(true);
                }
            }
        } else {
            person.setEdunvalvonta(false);
        }

        if (sPerson.getTurvakielto() != null && sPerson.getTurvakielto().getTurvakielto() != null
                && sPerson.getTurvakielto().getTurvakielto().getValue() != null) {
            person.setTurvakielto(sPerson.getTurvakielto().getTurvakielto().getValue().equals("1"));
        } else {
            person.setTurvakielto(false);
        }
        LOG.debug("fromSoapMessage: person=" + person);

        return person;
    }

    private List<Person> getHuoltajat(
            fi.vm.kapa.rova.soap.vtj.model.Person sPerson) {
        List<Person> result = new ArrayList<Person>();

        //check if custodian is valid
        if (sPerson.getHuoltaja() != null && sPerson.getHuoltaja().get(0).getId().getValue().length() == HETU_LENGTH) {
            List<Huoltaja> huoltajat = sPerson.getHuoltaja();

            for (Huoltaja g : huoltajat) {
                Person huoltaja = new Person();
                huoltaja.setHetu(g.getId().getValue());
                huoltaja.setFirstNames(g.getFirstNames().getValue());
                huoltaja.setLastName(g.getLastName().getValue());
                if (g.getHuoltotieto() != null && g.getHuoltotieto().getCustodyDivisionCode() != null
                        && g.getHuoltotieto().getCustodyDivisionCode().getValue() != null) {
                    huoltaja.setHuollonjakoSopimus(g.getHuoltotieto().getCustodyDivisionCode().getValue().equalsIgnoreCase("2"));
                    huoltaja.setHuollonjakoMaarays(g.getHuoltotieto().getCustodyDivisionCode().getValue().equalsIgnoreCase("1"));
                }
                result.add(huoltaja);
            }
        }
        return result;
    }

    private List<Person> getPrincipals(
            fi.vm.kapa.rova.soap.vtj.model.Person sPerson) {

        List<Person> result = new ArrayList<Person>();
        List<Principal> principals = sPerson.getPrincipal();

        if (principals != null && principals.get(0).getId().getValue().length() == HETU_LENGTH) {
            for (Principal p : principals) {
                Person principal = new Person();
                principal.setHetu(p.getId().getValue());
                principal.setFirstNames(p.getFirstNames().getValue());
                principal.setLastName(p.getLastName().getValue());
                result.add(principal);
            }
        }
        return result;
    }

    private List<Person> getEdunvalvojat(
            fi.vm.kapa.rova.soap.vtj.model.Person sPerson) {
        List<Person> result = new ArrayList<Person>();

        if (sPerson.getEdunvalvonta() != null) {
            List<EdunvalvojaHenkilo> henkiloedunvalvojat = sPerson.getEdunvalvonta().getEdunvalvojaHenkilo();
            if (henkiloedunvalvojat != null) {
                for (EdunvalvojaHenkilo p : henkiloedunvalvojat) {
                    if (p.getHetu().getValue() != null && p.getHetu().getValue().length() == HETU_LENGTH) {
                        Person edunvalvoja = new Person();
                        edunvalvoja.setHetu(p.getHetu().getValue());
                        edunvalvoja.setBirthdate(p.getBirthday().getValue());
                        edunvalvoja.setFirstNames(p.getFirstName().getFirstName().getValue());
                        edunvalvoja.setLastName(p.getLastName().getValue());
                        result.add(edunvalvoja);
                    }
                }
            }
        }
        return result;
    }

    private List<Person> getEdunvalvontaValtuutetut(
            fi.vm.kapa.rova.soap.vtj.model.Person sPerson) {
        List<Person> result = new ArrayList<Person>();

        if (sPerson.getEdunvalvontaValtuutus() != null) {
            List<EdunvalvontaValtuutettuHenkilo> henkiloEdunvalvontaValtuutetut = sPerson.getEdunvalvontaValtuutus().getEdunvalvontaValtuutettuHenkilo();
            if (henkiloEdunvalvontaValtuutetut != null) {
                for (EdunvalvontaValtuutettuHenkilo p : henkiloEdunvalvontaValtuutetut) {
                    if (p.getHetu().getValue() != null && p.getHetu().getValue().length() == HETU_LENGTH) {
                        Person edunvalvontaValtuutettu = new Person();
                        edunvalvontaValtuutettu.setHetu(p.getHetu().getValue());
                        edunvalvontaValtuutettu.setBirthdate(p.getBirthday().getValue());
                        edunvalvontaValtuutettu.setFirstNames(p.getFirstName().getFirstName().getValue());
                        edunvalvontaValtuutettu.setLastName(p.getLastName().getValue());
                        result.add(edunvalvontaValtuutettu);
                    }
                }
            }
        }
        return result;
    }
}
