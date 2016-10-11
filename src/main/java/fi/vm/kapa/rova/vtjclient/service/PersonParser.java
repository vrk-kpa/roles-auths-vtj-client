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
package fi.vm.kapa.rova.vtjclient.service;

import fi.vm.kapa.rova.external.model.vtj.Person;
import fi.vm.kapa.rova.logging.Logger;
import fi.vm.kapa.rova.soap.vtj.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juha Korkalainen on 10/7/16.
 */
@Component
public class PersonParser {

    private static Logger LOG = Logger.getLogger(PersonParser.class);

    void parseHetu(fi.vm.kapa.rova.soap.vtj.model.Person sPerson, Person person) {
        fi.vm.kapa.rova.soap.vtj.model.Hetu soapHetu = sPerson.getHetu();
        person.setHetu(soapHetu.getHetu());
        if (soapHetu.getValidityCode().equals("1")) { // "1" = hetu voimassa
            person.setHetuValid(true);
        } else {
            person.setHetuValid(false);
        }
    }

    void parseIdentity(fi.vm.kapa.rova.soap.vtj.model.Person sPerson, Person person) {
        if (sPerson.getFirstName() != null) {
            person.setFirstNames(sPerson.getFirstName().getFirstName().getValue());
        }
        if (sPerson.getLastName() != null) {
            person.setLastName(sPerson.getLastName().getLastName().getValue());
        }
        if (sPerson.getCallingName() != null) {
            person.setCallingName(sPerson.getCallingName().getNickName().getValue());
        }
    }

    void parseIsDeceased(fi.vm.kapa.rova.soap.vtj.model.Person sPerson, Person person) {
        if (sPerson.getDeceased() != null && sPerson.getDeceased().getDeceased() != null && sPerson.getDeceased().getDeceased().getValue() != null) {
            person.setDeceased(sPerson.getDeceased().getDeceased().getValue().equals("1")); // "1" = Kuollut
        } else {
            person.setDeceased(false);
        }
    }

    void parseHuostaanotto(fi.vm.kapa.rova.soap.vtj.model.Person sPerson, Person person) {
        if (sPerson.getHuostaanotto() != null && sPerson.getHuostaanotto().getHuostaanottoTieto() != null && sPerson.getHuostaanotto().getHuostaanottoTieto().getValue() != null) {
            person.setHuostaanotettu(
                    sPerson.getHuostaanotto().getHuostaanottoTieto().getValue().equals("1")); // "1" = huostaanotettu
        } else {
            person.setHuostaanotettu(false);
        }

    }

    void parseTurvakielto(fi.vm.kapa.rova.soap.vtj.model.Person sPerson, Person person) {
        if (sPerson.getTurvakielto() != null && sPerson.getTurvakielto().getTurvakielto() != null && sPerson.getTurvakielto().getTurvakielto().getValue() != null) {
            person.setTurvakielto(sPerson.getTurvakielto().getTurvakielto().getValue().equals("1"));
        } else {
            person.setTurvakielto(false);
        }
    }

    void parseEdunvalvontaTieto(fi.vm.kapa.rova.soap.vtj.model.Person sPerson, Person person) {
        if (sPerson.getEdunvalvonta() != null && sPerson.getEdunvalvonta().getEdunvalvontatieto() != null && sPerson.getEdunvalvonta().getEdunvalvontatieto().getValue() != null) {
            person.setEdunvalvonta(
                    sPerson.getEdunvalvonta().getEdunvalvontatieto().getValue().equals("1")); // "1" = Edunvalvonnassa
        } else {
            person.setEdunvalvonta(false);
        }
    }

    void parseEdunvalvontaRajoitusKoodi(fi.vm.kapa.rova.soap.vtj.model.Person sPerson, Person person) {
        if (sPerson.getEdunvalvonta() != null) {
            person.setEdunvalvontaEiRajoitettu(false);
            person.setEdunvalvontaRajoitettu(false);
            person.setEdunvalvontaJulistettu(false);

            if (sPerson.getEdunvalvonta().getRajoituskoodi() != null && sPerson.getEdunvalvonta().getRajoituskoodi().getValue() != null) {
                if (sPerson.getEdunvalvonta().getRajoituskoodi().getValue().equals("1")) { // "1" = ei rajoitettu
                    person.setEdunvalvontaEiRajoitettu(true);
                } else if (sPerson.getEdunvalvonta().getRajoituskoodi().getValue().equals("2")) { // "2" = rajoitettu
                    person.setEdunvalvontaRajoitettu(true);
                } else if (sPerson.getEdunvalvonta().getRajoituskoodi().getValue().equals("3")) { // "3" = julistettu
                    person.setEdunvalvontaJulistettu(true);
                }
            }
        }
    }

    void parseHuoltajat(fi.vm.kapa.rova.soap.vtj.model.Person sPerson, Person person) {
        List<Person> result = new ArrayList<Person>();

        List<Huoltaja> huoltajat = sPerson.getHuoltaja();

        if (huoltajat != null) {
            for (Huoltaja g : huoltajat) {
                Person huoltaja = new Person();
                huoltaja.setHetu(g.getId().getValue());
                huoltaja.setFirstNames(g.getFirstNames().getValue());
                huoltaja.setLastName(g.getLastName().getValue());
                Huoltotieto huoltotieto = g.getHuoltotieto();
                if (huoltotieto != null) {
                    StringNode custodyDivisionCode = huoltotieto.getCustodyDivisionCode();
                    if (custodyDivisionCode != null && custodyDivisionCode.getValue() != null) {
                        huoltaja.setHuollonjakoSopimus(custodyDivisionCode.getValue().equalsIgnoreCase("2"));
                        huoltaja.setHuollonjakoMaarays(custodyDivisionCode.getValue().equalsIgnoreCase("1"));
                    }
                }
                result.add(huoltaja);
            }
        }
        person.setHuoltajat(result);
    }

    void parsePrincipals(fi.vm.kapa.rova.soap.vtj.model.Person sPerson, Person person) {

        List<Person> result = new ArrayList<Person>();
        List<Principal> principals = sPerson.getPrincipal();

        if (principals != null) {
            for (Principal p : principals) {
                Person principal = new Person();
                principal.setHetu(p.getId().getValue());
                principal.setFirstNames(p.getFirstNames().getValue());
                principal.setLastName(p.getLastName().getValue());
                result.add(principal);
            }
        }
        person.setPrincipals(result);
    }

    void parseEdunvalvojat(fi.vm.kapa.rova.soap.vtj.model.Person sPerson, Person person) {
        List<Person> result = new ArrayList<Person>();

        if (sPerson.getEdunvalvonta() != null) {
            List<EdunvalvojaHenkilo> henkiloedunvalvojat = sPerson.getEdunvalvonta().getEdunvalvojaHenkilo();
            if (henkiloedunvalvojat != null) {
                for (EdunvalvojaHenkilo p : henkiloedunvalvojat) {
                    if (p.getHetu().getValue() != null) {
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

        person.setEdunvalvojat(result);
    }

    void parseEdunvalvontaValtuutetut(fi.vm.kapa.rova.soap.vtj.model.Person sPerson, Person person) {
        List<Person> result = new ArrayList<Person>();

        if (sPerson.getEdunvalvontaValtuutus() != null) {
            List<EdunvalvontaValtuutettuHenkilo> henkiloEdunvalvontaValtuutetut = sPerson.getEdunvalvontaValtuutus().getEdunvalvontaValtuutettuHenkilo();
            if (henkiloEdunvalvontaValtuutetut != null) {
                for (EdunvalvontaValtuutettuHenkilo p : henkiloEdunvalvontaValtuutetut) {
                    if (p.getHetu().getValue() != null) {
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
        person.setEdunvalvontaValtuutetut(result);
    }
}
