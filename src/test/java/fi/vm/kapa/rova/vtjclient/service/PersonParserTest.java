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
import fi.vm.kapa.rova.soap.vtj.model.*;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by Juha Korkalainen on 10/7/16.
 */
public class PersonParserTest {
    private fi.vm.kapa.rova.soap.vtj.model.Person soapPersonMock;
    private Hetu hetuMock;
    private CurrentFirstNames firstNameMock;
    private StringNode firstNameStringMock;
    private CurrentLastName lastNameMock;
    private StringNode lastNameStringMock;
    private CurrentNickName nickNameMock;
    private StringNode nickNameStringMock;
    private Deceased deceasedMock;
    private StringNode deceasedStringMock;
    private Huostaanotto huostaanottoMock;
    private StringNode huostaanottoStringMock;
    private Turvakielto turvakieltoMock;
    private StringNode turvakieltoStringMock;

    private Edunvalvonta edunvalvontaMock;
    private StringNode edunvalvontatietoStringMock;
    private StringNode rajoituskoodiStringMock;

    private EdunvalvojaHenkilo edunvalvojaHenkilo1;
    private StringNode edunvalvojaHenkiloHetu1;
    private CurrentFirstNames edunvalvojaHenkiloFirstNameMock1;
    private StringNode edunvalvojaHenkiloFirstNameStringMock1;
    private StringNode edunvalvojaHenkiloLastName1;
    private StringNode edunvalvojaHenkiloBDay1;

    private EdunvalvojaHenkilo edunvalvojaHenkilo2;
    private StringNode edunvalvojaHenkiloHetu2;
    private CurrentFirstNames edunvalvojaHenkiloFirstNameMock2;
    private StringNode edunvalvojaHenkiloFirstNameStringMock2;
    private StringNode edunvalvojaHenkiloLastName2;
    private StringNode edunvalvojaHenkiloBDay2;

    private Huoltaja huoltaja1;
    private StringNode huoltajaHetu1;
    private StringNode huoltajaFirstName1;
    private StringNode huoltajaLastName1;
    private Huoltotieto huoltotieto1;
    private StringNode divisionCode1;

    private Huoltaja huoltaja2;
    private StringNode huoltajaHetu2;
    private StringNode huoltajaFirstName2;
    private StringNode huoltajaLastName2;
    private Huoltotieto huoltotieto2;
    private StringNode divisionCode2;

    private Huoltaja huoltaja3;
    private StringNode huoltajaHetu3;
    private StringNode huoltajaFirstName3;
    private StringNode huoltajaLastName3;

    private EdunvalvontaValtuutus edunvalvontaValtuutus;
    private StringNode eduvalvontaRajoituskoodiStringMock;

    private EdunvalvontaValtuutettuHenkilo edunvalvontaValtuutettuHenkilo1;
    private StringNode edunvalvontaValtuutettuHenkiloHetu1;
    private CurrentFirstNames edunvalvontaValtuutettuHenkiloFirstNameMock1;
    private StringNode edunvalvontaValtuutettuHenkiloFirstNameStringMock1;
    private StringNode edunvalvontaValtuutettuHenkiloLastName1;
    private StringNode edunvalvontaValtuutettuHenkiloBDay1;

    private EdunvalvontaValtuutettuHenkilo edunvalvontaValtuutettuHenkilo2;
    private StringNode edunvalvontaValtuutettuHenkiloHetu2;
    private CurrentFirstNames edunvalvontaValtuutettuHenkiloFirstNameMock2;
    private StringNode edunvalvontaValtuutettuHenkiloFirstNameStringMock2;
    private StringNode edunvalvontaValtuutettuHenkiloLastName2;
    private StringNode edunvalvontaValtuutettuHenkiloBDay2;

    private Principal principal1;
    private StringNode principalFirstName1;
    private StringNode principalHetu1;
    private StringNode principalLastName1;

    private Principal principal2;
    private StringNode principalHetu2;
    private StringNode principalFirstName2;
    private StringNode principalLastName2;

    @Before
    public void init() {
        soapPersonMock = createMock(fi.vm.kapa.rova.soap.vtj.model.Person.class);
        hetuMock = createMock(Hetu.class);
        firstNameMock = createMock(CurrentFirstNames.class);
        firstNameStringMock = createMock(StringNode.class);
        lastNameMock = createMock(CurrentLastName.class);
        lastNameStringMock = createMock(StringNode.class);
        nickNameMock = createMock(CurrentNickName.class);
        nickNameStringMock = createMock(StringNode.class);
        deceasedMock = createMock(Deceased.class);
        deceasedStringMock = createMock(StringNode.class);
        huostaanottoMock = createMock(Huostaanotto.class);
        huostaanottoStringMock = createMock(StringNode.class);
        turvakieltoMock = createMock(Turvakielto.class);
        turvakieltoStringMock = createMock(StringNode.class);

        edunvalvontaMock = createMock(Edunvalvonta.class);
        edunvalvontatietoStringMock = createMock(StringNode.class);
        rajoituskoodiStringMock = createMock(StringNode.class);

        edunvalvojaHenkilo1 = createMock(EdunvalvojaHenkilo.class);
        edunvalvojaHenkiloHetu1 = createMock(StringNode.class);
        edunvalvojaHenkiloFirstNameMock1 = createMock(CurrentFirstNames.class);
        edunvalvojaHenkiloFirstNameStringMock1 = createMock(StringNode.class);
        edunvalvojaHenkiloLastName1 = createMock(StringNode.class);
        edunvalvojaHenkiloBDay1 = createMock(StringNode.class);

        edunvalvojaHenkilo2 = createMock(EdunvalvojaHenkilo.class);
        edunvalvojaHenkiloHetu2 = createMock(StringNode.class);
        edunvalvojaHenkiloFirstNameMock2 = createMock(CurrentFirstNames.class);
        edunvalvojaHenkiloFirstNameStringMock2 = createMock(StringNode.class);
        edunvalvojaHenkiloLastName2 = createMock(StringNode.class);
        edunvalvojaHenkiloBDay2 = createMock(StringNode.class);

        huoltaja1 = createMock(Huoltaja.class);
        huoltajaHetu1 = createMock(StringNode.class);
        huoltajaFirstName1 = createMock(StringNode.class);
        huoltajaLastName1 = createMock(StringNode.class);
        huoltotieto1 = createMock(Huoltotieto.class);
        divisionCode1 = createMock(StringNode.class);

        huoltaja2 = createMock(Huoltaja.class);
        huoltajaHetu2 = createMock(StringNode.class);
        huoltajaFirstName2 = createMock(StringNode.class);
        huoltajaLastName2 = createMock(StringNode.class);
        huoltotieto2 = createMock(Huoltotieto.class);
        divisionCode2 = createMock(StringNode.class);

        huoltaja3 = createMock(Huoltaja.class);
        huoltajaHetu3 = createMock(StringNode.class);
        huoltajaFirstName3 = createMock(StringNode.class);
        huoltajaLastName3 = createMock(StringNode.class);

        edunvalvontaValtuutus = createMock(EdunvalvontaValtuutus.class);
        eduvalvontaRajoituskoodiStringMock = createMock(StringNode.class);

        edunvalvontaValtuutettuHenkilo1 = createMock(EdunvalvontaValtuutettuHenkilo.class);
        edunvalvontaValtuutettuHenkiloHetu1 = createMock(StringNode.class);
        edunvalvontaValtuutettuHenkiloFirstNameMock1 = createMock(CurrentFirstNames.class);
        edunvalvontaValtuutettuHenkiloFirstNameStringMock1 = createMock(StringNode.class);
        edunvalvontaValtuutettuHenkiloLastName1 = createMock(StringNode.class);
        edunvalvontaValtuutettuHenkiloBDay1 = createMock(StringNode.class);

        edunvalvontaValtuutettuHenkilo2 = createMock(EdunvalvontaValtuutettuHenkilo.class);
        edunvalvontaValtuutettuHenkiloHetu2 = createMock(StringNode.class);
        edunvalvontaValtuutettuHenkiloFirstNameMock2 = createMock(CurrentFirstNames.class);
        edunvalvontaValtuutettuHenkiloFirstNameStringMock2 = createMock(StringNode.class);
        edunvalvontaValtuutettuHenkiloLastName2 = createMock(StringNode.class);
        edunvalvontaValtuutettuHenkiloBDay2 = createMock(StringNode.class);

        principal1 = createMock(Principal.class);
        principalFirstName1 = createMock(StringNode.class);
        principalHetu1 = createMock(StringNode.class);
        principalLastName1 = createMock(StringNode.class);

        principal2 = createMock(Principal.class);
        principalHetu2 = createMock(StringNode.class);
        principalFirstName2 = createMock(StringNode.class);
        principalLastName2 = createMock(StringNode.class);

    }

    @Test
    public void parseHetuValidTest() {
        String hetuString = "123456-1234";
        expect(soapPersonMock.getHetu()).andReturn(hetuMock).once();
        expect(hetuMock.getHetu()).andReturn(hetuString).once();
        expect(hetuMock.getValidityCode()).andReturn("1").once();

        replay(soapPersonMock, hetuMock);

        PersonParser parser = new PersonParser();
        Person person = new Person();
        parser.parseHetu(soapPersonMock, person);

        verify(soapPersonMock, hetuMock);

        assertEquals(hetuString, person.getHetu());
        assertEquals(true, person.isHetuValid());
    }

    @Test
    public void parseHetuNotValidTest() {
        EasyMock.expect(soapPersonMock.getHetu()).andReturn(hetuMock).once();
        replay(soapPersonMock);

        expect(hetuMock.getHetu()).andReturn("hetu").once();
        expect(hetuMock.getValidityCode()).andReturn("0").once();
        replay(hetuMock);

        PersonParser parser = new PersonParser();
        Person person = new Person();
        parser.parseHetu(soapPersonMock, person);

        EasyMock.verify(soapPersonMock);
        EasyMock.verify(hetuMock);

        assertEquals(false, person.isHetuValid());
    }

    @Test
    public void parseIdentityTest() {
        expect(firstNameMock.getFirstName()).andReturn(firstNameStringMock).once();
        expect(firstNameStringMock.getValue()).andReturn("firstName").once();
        expect(lastNameMock.getLastName()).andReturn(lastNameStringMock).once();
        expect(lastNameStringMock.getValue()).andReturn("lastName").once();
        expect(nickNameMock.getNickName()).andReturn(nickNameStringMock).once();
        expect(nickNameStringMock.getValue()).andReturn("nickName").once();
        expect(soapPersonMock.getFirstName()).andReturn(firstNameMock).times(2);
        expect(soapPersonMock.getLastName()).andReturn(lastNameMock).times(2);
        expect(soapPersonMock.getCallingName()).andReturn(nickNameMock).times(2);

        replay(firstNameMock, firstNameStringMock, lastNameMock, lastNameStringMock, nickNameMock, nickNameStringMock,
                soapPersonMock);

        PersonParser parser = new PersonParser();
        Person person = new Person();
        parser.parseIdentity(soapPersonMock, person);

        verify(soapPersonMock, firstNameMock, firstNameStringMock, lastNameMock, lastNameStringMock, nickNameMock,
                nickNameStringMock);

        assertEquals("firstName", person.getFirstNames());
        assertEquals("lastName", person.getLastName());
        assertEquals("nickName", person.getCallingName());
    }

    @Test
    public void parseIsDeceasedTest() {

        expect(soapPersonMock.getDeceased()).andReturn(deceasedMock).times(4);
        expect(deceasedMock.getDeceased()).andReturn(deceasedStringMock).times(3);
        expect(deceasedStringMock.getValue()).andReturn("1").times(2);

        replay(soapPersonMock, deceasedMock, deceasedStringMock);

        PersonParser parser = new PersonParser();
        Person person = new Person();
        parser.parseIsDeceased(soapPersonMock, person);

        verify(soapPersonMock, deceasedMock, deceasedStringMock);
    }

    @Test
    public void parseIsDeceasedNotTest() {
        expect(soapPersonMock.getDeceased()).andReturn(null).once();

        replay(soapPersonMock);

        PersonParser parser = new PersonParser();
        Person person = new Person();
        parser.parseIsDeceased(soapPersonMock, person);

        verify(soapPersonMock);
    }

    @Test
    public void parseHuostaanottoTest() {

        expect(soapPersonMock.getHuostaanotto()).andReturn(huostaanottoMock).times(4);
        expect(huostaanottoMock.getHuostaanottoTieto()).andReturn(huostaanottoStringMock).times(3);
        expect(huostaanottoStringMock.getValue()).andReturn("1").times(2);

        replay(soapPersonMock, huostaanottoMock, huostaanottoStringMock);

        PersonParser parser = new PersonParser();
        Person person = new Person();
        parser.parseHuostaanotto(soapPersonMock, person);

        verify(soapPersonMock, huostaanottoMock, huostaanottoStringMock);
    }

    @Test
    public void parseHuostaanottoNotTest() {
        expect(soapPersonMock.getHuostaanotto()).andReturn(null).once();

        replay(soapPersonMock);

        PersonParser parser = new PersonParser();
        Person person = new Person();
        parser.parseHuostaanotto(soapPersonMock, person);
        verify(soapPersonMock);

        assertEquals(false, person.isHuostaanotettu());
    }

    @Test
    public void parseTurvakieltoTest() {

        expect(soapPersonMock.getTurvakielto()).andReturn(turvakieltoMock).times(4);
        expect(turvakieltoMock.getTurvakielto()).andReturn(turvakieltoStringMock).times(3);
        expect(turvakieltoStringMock.getValue()).andReturn("1").times(2);

        replay(soapPersonMock, turvakieltoMock, turvakieltoStringMock);

        PersonParser parser = new PersonParser();
        Person person = new Person();
        parser.parseTurvakielto(soapPersonMock, person);

        verify(soapPersonMock, turvakieltoMock, turvakieltoStringMock);
        assertEquals(true, person.isTurvakielto());
    }

    @Test
    public void parseTurvakieltoNotTest() {
        expect(soapPersonMock.getTurvakielto()).andReturn(null).once();
        replay(soapPersonMock);

        PersonParser parser = new PersonParser();
        Person person = new Person();
        parser.parseTurvakielto(soapPersonMock, person);
        verify(soapPersonMock);
    }

    @Test
    public void parseEdunvalvontatietoTest() {

        expect(soapPersonMock.getEdunvalvonta()).andReturn(edunvalvontaMock).times(4);
        expect(edunvalvontaMock.getEdunvalvontatieto()).andReturn(edunvalvontatietoStringMock).times(3);
        expect(edunvalvontatietoStringMock.getValue()).andReturn("1").times(2);

        replay(soapPersonMock, edunvalvontaMock, edunvalvontatietoStringMock);

        PersonParser parser = new PersonParser();
        Person person = new Person();
        parser.parseEdunvalvontaTieto(soapPersonMock, person);

        verify(soapPersonMock, edunvalvontaMock, edunvalvontatietoStringMock);

        assertEquals(true, person.isEdunvalvonta());
    }

    @Test
    public void parseEdunvalvontaTietoNotTest() {
        fi.vm.kapa.rova.soap.vtj.model.Person soapPersonMock = createMockBuilder(
                fi.vm.kapa.rova.soap.vtj.model.Person.class).addMockedMethod("getEdunvalvonta").createMock();

        expect(soapPersonMock.getEdunvalvonta()).andReturn(null).once();

        replay(soapPersonMock);

        PersonParser parser = new PersonParser();
        Person person = new Person();
        parser.parseEdunvalvontaTieto(soapPersonMock, person);

        verify(soapPersonMock);
    }

    @Test
    public void parseEdunvalvontaRajoitusKoodiEiRajoitettuTest() {
        fi.vm.kapa.rova.soap.vtj.model.Person soapPersonMock = createMockBuilder(
                fi.vm.kapa.rova.soap.vtj.model.Person.class).addMockedMethod("getEdunvalvonta").createMock();
        fi.vm.kapa.rova.soap.vtj.model.Edunvalvonta EdunvalvontaMock = createMockBuilder(
                Edunvalvonta.class).addMockedMethod("getRajoituskoodi").createMock();
        StringNode rajoituskoodiStringMock = createMockBuilder(StringNode.class).addMockedMethod(
                "getValue").createMock();

        expect(soapPersonMock.getEdunvalvonta()).andReturn(EdunvalvontaMock).times(4);
        expect(EdunvalvontaMock.getRajoituskoodi()).andReturn(rajoituskoodiStringMock).times(3);
        expect(rajoituskoodiStringMock.getValue()).andReturn("1").times(2);

        replay(soapPersonMock, EdunvalvontaMock, rajoituskoodiStringMock);

        PersonParser parser = new PersonParser();
        Person person = new Person();
        parser.parseEdunvalvontaRajoitusKoodi(soapPersonMock, person);
        verify(soapPersonMock, EdunvalvontaMock, rajoituskoodiStringMock);

        assertEquals(true, person.isEdunvalvontaEiRajoitettu());
        assertEquals(false, person.isEdunvalvontaRajoitettu());
        assertEquals(false, person.isEdunvalvontaJulistettu());
    }

    @Test
    public void parseEdunvalvontaRajoitusKoodiRajoitettuTest() {
        expect(soapPersonMock.getEdunvalvonta()).andReturn(edunvalvontaMock).times(5);
        expect(edunvalvontaMock.getRajoituskoodi()).andReturn(rajoituskoodiStringMock).times(4);
        expect(rajoituskoodiStringMock.getValue()).andReturn("2").times(3);

        replay(soapPersonMock, edunvalvontaMock, rajoituskoodiStringMock);

        PersonParser parser = new PersonParser();
        Person person = new Person();
        parser.parseEdunvalvontaRajoitusKoodi(soapPersonMock, person);

        verify(soapPersonMock, edunvalvontaMock, rajoituskoodiStringMock);

        assertEquals(false, person.isEdunvalvontaEiRajoitettu());
        assertEquals(true, person.isEdunvalvontaRajoitettu());
        assertEquals(false, person.isEdunvalvontaJulistettu());
    }

    @Test
    public void parseEdunvalvontaRajoitusKoodiJulistettuTest() {

        expect(soapPersonMock.getEdunvalvonta()).andReturn(edunvalvontaMock).times(6);
        expect(edunvalvontaMock.getRajoituskoodi()).andReturn(eduvalvontaRajoituskoodiStringMock).times(5);
        expect(eduvalvontaRajoituskoodiStringMock.getValue()).andReturn("3").times(4);

        replay(soapPersonMock, edunvalvontaMock, eduvalvontaRajoituskoodiStringMock);
        PersonParser parser = new PersonParser();
        Person person = new Person();
        parser.parseEdunvalvontaRajoitusKoodi(soapPersonMock, person);
        verify(soapPersonMock, edunvalvontaMock, eduvalvontaRajoituskoodiStringMock);

        assertEquals(false, person.isEdunvalvontaEiRajoitettu());
        assertEquals(false, person.isEdunvalvontaRajoitettu());
        assertEquals(true, person.isEdunvalvontaJulistettu());
    }

    @Test
    public void parseEdunvalvontaRajoituskoodiNotTest() {
        fi.vm.kapa.rova.soap.vtj.model.Person soapPersonMock = createMockBuilder(
                fi.vm.kapa.rova.soap.vtj.model.Person.class).addMockedMethod("getEdunvalvonta").createMock();

        expect(soapPersonMock.getEdunvalvonta()).andReturn(null).once();

        replay(soapPersonMock);
        PersonParser parser = new PersonParser();
        Person person = new Person();
        parser.parseEdunvalvontaTieto(soapPersonMock, person);
        verify(soapPersonMock);

        assertEquals(false, person.isEdunvalvontaEiRajoitettu());
        assertEquals(false, person.isEdunvalvontaRajoitettu());
        assertEquals(false, person.isEdunvalvontaJulistettu());
    }

    @Test
    public void parseEdunvalvojaEmptyTest() {
        fi.vm.kapa.rova.soap.vtj.model.Person soapPersonMock = createMockBuilder(
                fi.vm.kapa.rova.soap.vtj.model.Person.class).addMockedMethod("getEdunvalvonta").createMock();
        expect(soapPersonMock.getEdunvalvonta()).andReturn(null).once();
        replay(soapPersonMock);

        PersonParser parser = new PersonParser();
        Person person = new Person();
        parser.parseEdunvalvojat(soapPersonMock, person);

        verify(soapPersonMock);
    }

    @Test
    public void parseEdunvalvojaMultipleTest() {
        List<EdunvalvojaHenkilo> edunvalvojat = new ArrayList();
        edunvalvojat.add(edunvalvojaHenkilo1);
        edunvalvojat.add(edunvalvojaHenkilo2);

        expect(soapPersonMock.getEdunvalvonta()).andReturn(edunvalvontaMock).times(2);
        expect(edunvalvontaMock.getEdunvalvojaHenkilo()).andReturn(edunvalvojat).once();
        expect(edunvalvojaHenkilo1.getHetu()).andReturn(edunvalvojaHenkiloHetu1).times(2);
        expect(edunvalvojaHenkiloHetu1.getValue()).andReturn("hetu1").times(2);
        expect(edunvalvojaHenkilo1.getFirstName()).andReturn(edunvalvojaHenkiloFirstNameMock1).once();
        expect(edunvalvojaHenkiloFirstNameMock1.getFirstName()).andReturn(
                edunvalvojaHenkiloFirstNameStringMock1).once();
        expect(edunvalvojaHenkiloFirstNameStringMock1.getValue()).andReturn("firstName1").once();
        expect(edunvalvojaHenkilo1.getLastName()).andReturn(edunvalvojaHenkiloLastName1).once();
        expect(edunvalvojaHenkiloLastName1.getValue()).andReturn("lastName1").once();
        expect(edunvalvojaHenkilo1.getBirthday()).andReturn(edunvalvojaHenkiloBDay1).once();
        expect(edunvalvojaHenkiloBDay1.getValue()).andReturn("bday1").once();
        expect(edunvalvojaHenkilo2.getHetu()).andReturn(edunvalvojaHenkiloHetu2).times(2);
        expect(edunvalvojaHenkiloHetu2.getValue()).andReturn("hetu2").times(2);
        expect(edunvalvojaHenkilo2.getFirstName()).andReturn(edunvalvojaHenkiloFirstNameMock2).once();
        expect(edunvalvojaHenkiloFirstNameMock2.getFirstName()).andReturn(
                edunvalvojaHenkiloFirstNameStringMock2).once();
        expect(edunvalvojaHenkiloFirstNameStringMock2.getValue()).andReturn("firstName2").once();
        expect(edunvalvojaHenkilo2.getLastName()).andReturn(edunvalvojaHenkiloLastName2).once();
        expect(edunvalvojaHenkiloLastName2.getValue()).andReturn("lastName2").once();
        expect(edunvalvojaHenkilo2.getBirthday()).andReturn(edunvalvojaHenkiloBDay2).once();
        expect(edunvalvojaHenkiloBDay2.getValue()).andReturn("bday2").once();

        replay(soapPersonMock, edunvalvontaMock, edunvalvojaHenkilo1, edunvalvojaHenkiloHetu1,
                edunvalvojaHenkiloFirstNameMock1, edunvalvojaHenkiloFirstNameStringMock1, edunvalvojaHenkiloLastName1,
                edunvalvojaHenkiloBDay1, edunvalvojaHenkilo2, edunvalvojaHenkiloHetu2, edunvalvojaHenkiloFirstNameMock2,
                edunvalvojaHenkiloFirstNameStringMock2, edunvalvojaHenkiloLastName2, edunvalvojaHenkiloBDay2);

        PersonParser parser = new PersonParser();
        Person person = new Person();
        parser.parseEdunvalvojat(soapPersonMock, person);

        verify(soapPersonMock, edunvalvontaMock, edunvalvojaHenkilo1, edunvalvojaHenkiloHetu1,
                edunvalvojaHenkiloFirstNameMock1, edunvalvojaHenkiloFirstNameStringMock1, edunvalvojaHenkiloLastName1,
                edunvalvojaHenkiloBDay1, edunvalvojaHenkilo2, edunvalvojaHenkiloHetu2, edunvalvojaHenkiloFirstNameMock2,
                edunvalvojaHenkiloFirstNameStringMock2, edunvalvojaHenkiloLastName2, edunvalvojaHenkiloBDay2);

        assertEquals(2, person.getEdunvalvojat().size());
        for (int i = 0; i < person.getEdunvalvojat().size(); i++) {
            Person h = person.getEdunvalvojat().get(i);
            switch (i) {
                case 0: {
                    assertEquals("hetu1", h.getHetu());
                    assertEquals("firstName1", h.getFirstNames());
                    assertEquals("lastName1", h.getLastName());
                    assertEquals("bday1", h.getBirthdate());
                    break;
                }
                case 1: {
                    assertEquals("hetu2", h.getHetu());
                    assertEquals("firstName2", h.getFirstNames());
                    assertEquals("lastName2", h.getLastName());
                    assertEquals("bday2", h.getBirthdate());
                    break;
                }
            }
        }
    }

    @Test
    public void parseHuoltajatEmptyTest() {
        expect(soapPersonMock.getHuoltaja()).andReturn(null).once();

        replay(soapPersonMock);
        PersonParser parser = new PersonParser();
        Person person = new Person();
        parser.parseHuoltajat(soapPersonMock, person);
        verify(soapPersonMock);

        assertEquals(0, person.getHuoltajat().size());
    }

    @Test
    public void parseHuoltajatMultipleTest() {
        List<Huoltaja> huoltajat = new ArrayList();
        huoltajat.add(huoltaja1);
        huoltajat.add(huoltaja2);
        huoltajat.add(huoltaja3);

        expect(soapPersonMock.getHuoltaja()).andReturn(huoltajat).once();
        expect(huoltajaFirstName1.getValue()).andReturn("firstName1").once();
        expect(huoltajaLastName1.getValue()).andReturn("lastName1").once();
        expect(huoltajaHetu1.getValue()).andReturn("hetu1").times(2);
        expect(huoltaja1.getId()).andReturn(huoltajaHetu1).times(2);
        expect(huoltaja1.getFirstNames()).andReturn(huoltajaFirstName1).once();
        expect(huoltaja1.getLastName()).andReturn(huoltajaLastName1).once();
        expect(huoltaja1.getHuoltotieto()).andReturn(huoltotieto1).once();
        expect(huoltotieto1.getCustodyDivisionCode()).andReturn(divisionCode1).once();
        expect(divisionCode1.getValue()).andReturn("1").times(3);

        expect(huoltajaFirstName2.getValue()).andReturn("firstName2").once();
        expect(huoltajaLastName2.getValue()).andReturn("lastName2").once();
        expect(huoltajaHetu2.getValue()).andReturn("hetu2").times(2);
        expect(huoltaja2.getId()).andReturn(huoltajaHetu2).times(2);
        expect(huoltaja2.getFirstNames()).andReturn(huoltajaFirstName2).once();
        expect(huoltaja2.getLastName()).andReturn(huoltajaLastName2).once();
        expect(huoltaja2.getHuoltotieto()).andReturn(huoltotieto2).once();
        expect(huoltotieto2.getCustodyDivisionCode()).andReturn(divisionCode2).once();
        expect(divisionCode2.getValue()).andReturn("2").times(3);

        expect(huoltajaFirstName3.getValue()).andReturn("firstName3").once();
        expect(huoltajaLastName3.getValue()).andReturn("lastName3").once();
        expect(huoltajaHetu3.getValue()).andReturn("hetu3").times(2);
        expect(huoltaja3.getId()).andReturn(huoltajaHetu3).times(2);
        expect(huoltaja3.getFirstNames()).andReturn(huoltajaFirstName3).once();
        expect(huoltaja3.getLastName()).andReturn(huoltajaLastName3).once();
        expect(huoltaja3.getHuoltotieto()).andReturn(null).once();


        replay(soapPersonMock, huoltaja1, huoltajaHetu1, huoltajaFirstName1, huoltajaLastName1, huoltotieto1,
                divisionCode1, huoltaja2, huoltajaHetu2, huoltajaFirstName2, huoltajaLastName2, huoltotieto2,
                divisionCode2, huoltaja3, huoltajaHetu3, huoltajaFirstName3, huoltajaLastName3);

        PersonParser parser = new PersonParser();
        Person person = new Person();
        parser.parseHuoltajat(soapPersonMock, person);

        verify(soapPersonMock, huoltaja1, huoltajaHetu1, huoltajaFirstName1, huoltajaLastName1, huoltotieto1,
                divisionCode1, huoltaja2, huoltajaHetu2, huoltajaFirstName2, huoltajaLastName2, huoltotieto2,
                divisionCode2, huoltaja3, huoltajaHetu3, huoltajaFirstName3, huoltajaLastName3);

        assertEquals(3, person.getHuoltajat().size());
        for (int i = 0; i < person.getHuoltajat().size(); i++) {
            Person h = person.getHuoltajat().get(i);
            switch (i) {
                case 0: {
                    assertEquals("hetu1", h.getHetu());
                    assertEquals("firstName1", h.getFirstNames());
                    assertEquals("lastName1", h.getLastName());
                    assertEquals(true, h.isHuollonjakoMaarays());
                    assertEquals(false, h.isHuollonjakoSopimus());
                    break;
                }
                case 1: {
                    assertEquals("hetu2", h.getHetu());
                    assertEquals("firstName2", h.getFirstNames());
                    assertEquals("lastName2", h.getLastName());
                    assertEquals(false, h.isHuollonjakoMaarays());
                    assertEquals(true, h.isHuollonjakoSopimus());
                    break;
                }
                case 2: {
                    assertEquals("hetu3", h.getHetu());
                    assertEquals("firstName3", h.getFirstNames());
                    assertEquals("lastName3", h.getLastName());
                    assertEquals(false, h.isHuollonjakoMaarays());
                    assertEquals(false, h.isHuollonjakoSopimus());
                    break;
                }
            }

        }
    }

    @Test
    public void parsePrincipalsEmptyTest() {
        expect(soapPersonMock.getPrincipal()).andReturn(null).once();

        replay(soapPersonMock);
        PersonParser parser = new PersonParser();
        Person person = new Person();
        parser.parsePrincipals(soapPersonMock, person);
        verify(soapPersonMock);
    }

    @Test
    public void parsePrincipalsMultipleTest() {

        List<Principal> principals = new ArrayList();
        principals.add(principal1);
        principals.add(principal2);

        expect(soapPersonMock.getPrincipal()).andReturn(principals).once();

        expect(principal1.getId()).andReturn(principalHetu1).once();
        expect(principalHetu1.getValue()).andReturn("hetu1").once();

        expect(principal1.getFirstNames()).andReturn(principalFirstName1).once();
        expect(principalFirstName1.getValue()).andReturn("firstName1").once();

        expect(principal1.getLastName()).andReturn(principalLastName1).once();
        expect(principalLastName1.getValue()).andReturn("lastName1").once();

        expect(principal2.getId()).andReturn(principalHetu2);
        expect(principalHetu2.getValue()).andReturn("hetu2").once();

        expect(principal2.getFirstNames()).andReturn(principalFirstName2).once();
        expect(principalFirstName2.getValue()).andReturn("firstName2").once();

        expect(principal2.getLastName()).andReturn(principalLastName2).once();
        expect(principalLastName2.getValue()).andReturn("lastName2").once();


        replay(soapPersonMock, principal1, principalHetu1, principalFirstName1, principalLastName1, principal2,
                principalHetu2, principalFirstName2, principalLastName2);

        PersonParser parser = new PersonParser();
        Person person = new Person();
        parser.parsePrincipals(soapPersonMock, person);

        verify(soapPersonMock, principal1, principalHetu1, principalFirstName1, principalLastName1, principal2,
                principalHetu2, principalFirstName2, principalLastName2);

        assertEquals(2, person.getPrincipals().size());
        for (int i = 0; i < person.getPrincipals().size(); i++) {
            Person h = person.getPrincipals().get(i);
            switch (i) {
                case 0: {
                    assertEquals("hetu1", h.getHetu());
                    assertEquals("firstName1", h.getFirstNames());
                    assertEquals("lastName1", h.getLastName());
                    break;
                }
                case 1: {
                    assertEquals("hetu2", h.getHetu());
                    assertEquals("firstName2", h.getFirstNames());
                    assertEquals("lastName2", h.getLastName());
                    break;
                }
            }
        }
    }

    @Test
    public void parseEdunvalvontaValtuutetutEmptyTest() {
        expect(soapPersonMock.getEdunvalvontaValtuutus()).andReturn(null).once();

        replay(soapPersonMock);
        PersonParser parser = new PersonParser();
        Person person = new Person();
        parser.parseEdunvalvontaValtuutetut(soapPersonMock, person);
        verify(soapPersonMock);

        assertEquals(0, person.getEdunvalvontaValtuutetut().size());
    }

    @Test
    public void parseEdunvalvontaValtuutetutMultipleTest() {

        List<EdunvalvontaValtuutettuHenkilo> edunvalvojat = new ArrayList();
        edunvalvojat.add(edunvalvontaValtuutettuHenkilo1);
        edunvalvojat.add(edunvalvontaValtuutettuHenkilo2);

        expect(soapPersonMock.getEdunvalvontaValtuutus()).andReturn(edunvalvontaValtuutus).times(2);
        expect(edunvalvontaValtuutus.getEdunvalvontaValtuutettuHenkilo()).andReturn(edunvalvojat).once();

        expect(edunvalvontaValtuutettuHenkilo1.getHetu()).andReturn(edunvalvontaValtuutettuHenkiloHetu1).times(2);
        expect(edunvalvontaValtuutettuHenkilo1.getFirstName()).andReturn(
                edunvalvontaValtuutettuHenkiloFirstNameMock1).once();
        expect(edunvalvontaValtuutettuHenkilo1.getLastName()).andReturn(edunvalvontaValtuutettuHenkiloLastName1).once();
        expect(edunvalvontaValtuutettuHenkilo1.getBirthday()).andReturn(edunvalvontaValtuutettuHenkiloBDay1).once();
        expect(edunvalvontaValtuutettuHenkiloHetu1.getValue()).andReturn("hetu1").times(2);
        expect(edunvalvontaValtuutettuHenkiloFirstNameMock1.getFirstName()).andReturn(
                edunvalvontaValtuutettuHenkiloFirstNameStringMock1).once();
        expect(edunvalvontaValtuutettuHenkiloFirstNameStringMock1.getValue()).andReturn("firstName1").once();

        expect(edunvalvontaValtuutettuHenkiloLastName1.getValue()).andReturn("lastName1").once();
        expect(edunvalvontaValtuutettuHenkiloBDay1.getValue()).andReturn("bday1").once();

        expect(edunvalvontaValtuutettuHenkilo2.getHetu()).andReturn(edunvalvontaValtuutettuHenkiloHetu2).times(2);
        expect(edunvalvontaValtuutettuHenkilo2.getFirstName()).andReturn(
                edunvalvontaValtuutettuHenkiloFirstNameMock2).once();
        expect(edunvalvontaValtuutettuHenkilo2.getLastName()).andReturn(edunvalvontaValtuutettuHenkiloLastName2).once();
        expect(edunvalvontaValtuutettuHenkilo2.getBirthday()).andReturn(edunvalvontaValtuutettuHenkiloBDay2).once();
        expect(edunvalvontaValtuutettuHenkiloHetu2.getValue()).andReturn("hetu2").times(2);
        expect(edunvalvontaValtuutettuHenkiloFirstNameMock2.getFirstName()).andReturn(
                edunvalvontaValtuutettuHenkiloFirstNameStringMock2).once();
        expect(edunvalvontaValtuutettuHenkiloFirstNameStringMock2.getValue()).andReturn("firstName2").once();
        expect(edunvalvontaValtuutettuHenkiloLastName2.getValue()).andReturn("lastName2").once();
        expect(edunvalvontaValtuutettuHenkiloBDay2.getValue()).andReturn("bday2").once();

        replay(soapPersonMock, edunvalvontaValtuutus, edunvalvontaValtuutettuHenkilo1,
                edunvalvontaValtuutettuHenkiloHetu1, edunvalvontaValtuutettuHenkiloFirstNameMock1,
                edunvalvontaValtuutettuHenkiloFirstNameStringMock1, edunvalvontaValtuutettuHenkiloLastName1,
                edunvalvontaValtuutettuHenkiloBDay1, edunvalvontaValtuutettuHenkilo2,
                edunvalvontaValtuutettuHenkiloHetu2, edunvalvontaValtuutettuHenkiloFirstNameMock2,
                edunvalvontaValtuutettuHenkiloFirstNameStringMock2, edunvalvontaValtuutettuHenkiloLastName2,
                edunvalvontaValtuutettuHenkiloBDay2);

        PersonParser parser = new PersonParser();
        Person person = new Person();
        parser.parseEdunvalvontaValtuutetut(soapPersonMock, person);

        verify(soapPersonMock, edunvalvontaValtuutus, edunvalvontaValtuutettuHenkilo1,
                edunvalvontaValtuutettuHenkiloHetu1, edunvalvontaValtuutettuHenkiloFirstNameMock1,
                edunvalvontaValtuutettuHenkiloFirstNameStringMock1, edunvalvontaValtuutettuHenkiloLastName1,
                edunvalvontaValtuutettuHenkiloBDay1, edunvalvontaValtuutettuHenkilo2,
                edunvalvontaValtuutettuHenkiloHetu2, edunvalvontaValtuutettuHenkiloFirstNameMock2,
                edunvalvontaValtuutettuHenkiloFirstNameStringMock2, edunvalvontaValtuutettuHenkiloLastName2,
                edunvalvontaValtuutettuHenkiloBDay2);

        assertEquals(2, person.getEdunvalvontaValtuutetut().size());
        for (int i = 0; i < person.getEdunvalvontaValtuutetut().size(); i++) {
            Person h = person.getEdunvalvontaValtuutetut().get(i);
            switch (i) {
                case 0: {
                    assertEquals("hetu1", h.getHetu());
                    assertEquals("firstName1", h.getFirstNames());
                    assertEquals("lastName1", h.getLastName());
                    assertEquals("bday1", h.getBirthdate());
                    break;
                }
                case 1: {
                    assertEquals("hetu2", h.getHetu());
                    assertEquals("firstName2", h.getFirstNames());
                    assertEquals("lastName2", h.getLastName());
                    assertEquals("bday2", h.getBirthdate());
                    break;
                }
            }

        }
    }
}