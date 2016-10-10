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
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;


/**
 * Created by Juha Korkalainen on 10/7/16.
 */
public class PersonParserTest {


    @Test
    public void parseHetuValidTest() {
        String hetuString = "123456-1234";
        fi.vm.kapa.rova.soap.vtj.model.Person soapPersonMock = createMockBuilder(
                fi.vm.kapa.rova.soap.vtj.model.Person.class).addMockedMethod("getHetu").createMock();
        fi.vm.kapa.rova.soap.vtj.model.Hetu hetuMock = createMockBuilder(
                fi.vm.kapa.rova.soap.vtj.model.Hetu.class).addMockedMethod("getValidityCode").addMockedMethod(
                "getHetu").createMock();

        EasyMock.expect(soapPersonMock.getHetu()).andReturn(hetuMock).once();
        replay(soapPersonMock);

        expect(hetuMock.getHetu()).andReturn(hetuString).once();
        expect(hetuMock.getValidityCode()).andReturn("1").once();
        replay(hetuMock);

        PersonParser parser = new PersonParser();
        Person person = new Person();
        parser.parseHetu(soapPersonMock, person);

        assertEquals(hetuString, person.getHetu());
        assertEquals(true, person.isHetuValid());

        verify(soapPersonMock);
        verify(hetuMock);
    }

    @Test
    public void parseHetuNotValidTest() {
        fi.vm.kapa.rova.soap.vtj.model.Person soapPersonMock = createMockBuilder(
                fi.vm.kapa.rova.soap.vtj.model.Person.class).addMockedMethod("getHetu").createMock();
        fi.vm.kapa.rova.soap.vtj.model.Hetu hetuMock = createMockBuilder(
                fi.vm.kapa.rova.soap.vtj.model.Hetu.class).addMockedMethod("getValidityCode").addMockedMethod(
                "getHetu").createMock();

        EasyMock.expect(soapPersonMock.getHetu()).andReturn(hetuMock).once();
        replay(soapPersonMock);

        expect(hetuMock.getHetu()).andReturn("hetu").once();
        expect(hetuMock.getValidityCode()).andReturn("0").once();
        replay(hetuMock);

        PersonParser parser = new PersonParser();
        Person person = new Person();
        parser.parseHetu(soapPersonMock, person);

        assertEquals(false, person.isHetuValid());

        EasyMock.verify(soapPersonMock);
        EasyMock.verify(hetuMock);
    }

    @Test
    public void parseIdentityTest() {
        fi.vm.kapa.rova.soap.vtj.model.Person soapPersonMock = createMockBuilder(
                fi.vm.kapa.rova.soap.vtj.model.Person.class).addMockedMethod("getFirstName").addMockedMethod(
                "getLastName").addMockedMethod("getCallingName").createMock();

        fi.vm.kapa.rova.soap.vtj.model.CurrentFirstNames firstNameMock = createMockBuilder(
                fi.vm.kapa.rova.soap.vtj.model.CurrentFirstNames.class).addMockedMethod("getFirstName").createMock();
        fi.vm.kapa.rova.soap.vtj.model.StringNode firstNameStringMock = createMockBuilder(
                fi.vm.kapa.rova.soap.vtj.model.StringNode.class).addMockedMethod("getValue").createMock();

        fi.vm.kapa.rova.soap.vtj.model.CurrentLastName lastNameMock = createMockBuilder(
                fi.vm.kapa.rova.soap.vtj.model.CurrentLastName.class).addMockedMethod("getLastName").createMock();
        fi.vm.kapa.rova.soap.vtj.model.StringNode lastNameStringMock = createMockBuilder(
                fi.vm.kapa.rova.soap.vtj.model.StringNode.class).addMockedMethod("getValue").createMock();

        fi.vm.kapa.rova.soap.vtj.model.CurrentNickName nickNameMock = createMockBuilder(
                fi.vm.kapa.rova.soap.vtj.model.CurrentNickName.class).addMockedMethod("getNickName").createMock();
        fi.vm.kapa.rova.soap.vtj.model.StringNode nickNameStringMock = createMockBuilder(
                fi.vm.kapa.rova.soap.vtj.model.StringNode.class).addMockedMethod("getValue").createMock();

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

        assertEquals("firstName", person.getFirstNames());
        assertEquals("lastName", person.getLastName());
        assertEquals("nickName", person.getCallingName());

        verify(soapPersonMock, firstNameMock, firstNameStringMock, lastNameMock, lastNameStringMock, nickNameMock,
                nickNameStringMock);
    }

    @Test
    public void parseIsDeceasedTest() {
        fi.vm.kapa.rova.soap.vtj.model.Person soapPersonMock = createMockBuilder(
                fi.vm.kapa.rova.soap.vtj.model.Person.class).addMockedMethod("getDeceased").createMock();
        fi.vm.kapa.rova.soap.vtj.model.Deceased deceasedMock = createMockBuilder(Deceased.class).addMockedMethod(
                "getDeceased").createMock();
        StringNode deceasedStringMock = createMockBuilder(StringNode.class).addMockedMethod("getValue").createMock();

        expect(soapPersonMock.getDeceased()).andReturn(deceasedMock).times(4);
        expect(deceasedMock.getDeceased()).andReturn(deceasedStringMock).times(3);
        expect(deceasedStringMock.getValue()).andReturn("1").times(2);

        replay(soapPersonMock, deceasedMock, deceasedStringMock);

        PersonParser parser = new PersonParser();
        Person person = new Person();
        parser.parseIsDeceased(soapPersonMock, person);

        assertEquals(true, person.isDeceased());

        verify(soapPersonMock, deceasedMock, deceasedStringMock);
    }

    @Test
    public void parseIsDeceasedNotTest() {
        fi.vm.kapa.rova.soap.vtj.model.Person soapPersonMock = createMockBuilder(
                fi.vm.kapa.rova.soap.vtj.model.Person.class).addMockedMethod("getDeceased").createMock();

        expect(soapPersonMock.getDeceased()).andReturn(null).once();

        replay(soapPersonMock);

        PersonParser parser = new PersonParser();
        Person person = new Person();
        parser.parseIsDeceased(soapPersonMock, person);

        assertEquals(false, person.isDeceased());

        verify(soapPersonMock);
    }

    @Test
    public void parseHuostaanottoTest() {
        fi.vm.kapa.rova.soap.vtj.model.Person soapPersonMock = createMockBuilder(
                fi.vm.kapa.rova.soap.vtj.model.Person.class).addMockedMethod("getHuostaanotto").createMock();
        fi.vm.kapa.rova.soap.vtj.model.Huostaanotto huostaanottoMock = createMockBuilder(
                Huostaanotto.class).addMockedMethod("getHuostaanottoTieto").createMock();
        StringNode huostaanottoStringMock = createMockBuilder(StringNode.class).addMockedMethod(
                "getValue").createMock();

        expect(soapPersonMock.getHuostaanotto()).andReturn(huostaanottoMock).times(4);
        expect(huostaanottoMock.getHuostaanottoTieto()).andReturn(huostaanottoStringMock).times(3);
        expect(huostaanottoStringMock.getValue()).andReturn("1").times(2);

        replay(soapPersonMock, huostaanottoMock, huostaanottoStringMock);

        PersonParser parser = new PersonParser();
        Person person = new Person();
        parser.parseHuostaanotto(soapPersonMock, person);

        assertEquals(true, person.isHuostaanotettu());

        verify(soapPersonMock, huostaanottoMock, huostaanottoStringMock);
    }

    @Test
    public void parseHuostaanottoNotTest() {
        fi.vm.kapa.rova.soap.vtj.model.Person soapPersonMock = createMockBuilder(
                fi.vm.kapa.rova.soap.vtj.model.Person.class).addMockedMethod("getHuostaanotto").createMock();

        expect(soapPersonMock.getHuostaanotto()).andReturn(null).once();

        replay(soapPersonMock);

        PersonParser parser = new PersonParser();
        Person person = new Person();
        parser.parseHuostaanotto(soapPersonMock, person);

        assertEquals(false, person.isHuostaanotettu());

        verify(soapPersonMock);
    }

    @Test
    public void parseTurvakieltoTest() {
        fi.vm.kapa.rova.soap.vtj.model.Person soapPersonMock = createMockBuilder(
                fi.vm.kapa.rova.soap.vtj.model.Person.class).addMockedMethod("getTurvakielto").createMock();
        fi.vm.kapa.rova.soap.vtj.model.Turvakielto TurvakieltoMock = createMockBuilder(
                Turvakielto.class).addMockedMethod("getTurvakielto").createMock();
        StringNode TurvakieltoStringMock = createMockBuilder(StringNode.class).addMockedMethod("getValue").createMock();

        expect(soapPersonMock.getTurvakielto()).andReturn(TurvakieltoMock).times(4);
        expect(TurvakieltoMock.getTurvakielto()).andReturn(TurvakieltoStringMock).times(3);
        expect(TurvakieltoStringMock.getValue()).andReturn("1").times(2);

        replay(soapPersonMock, TurvakieltoMock, TurvakieltoStringMock);

        PersonParser parser = new PersonParser();
        Person person = new Person();
        parser.parseTurvakielto(soapPersonMock, person);

        assertEquals(true, person.isTurvakielto());

        verify(soapPersonMock, TurvakieltoMock, TurvakieltoStringMock);
    }

    @Test
    public void parseTurvakieltoNotTest() {
        fi.vm.kapa.rova.soap.vtj.model.Person soapPersonMock = createMockBuilder(
                fi.vm.kapa.rova.soap.vtj.model.Person.class).addMockedMethod("getTurvakielto").createMock();

        expect(soapPersonMock.getTurvakielto()).andReturn(null).once();

        replay(soapPersonMock);

        PersonParser parser = new PersonParser();
        Person person = new Person();
        parser.parseTurvakielto(soapPersonMock, person);

        assertEquals(false, person.isTurvakielto());

        verify(soapPersonMock);
    }

    @Test
    public void parseEdunvalvontatietoTest() {
        fi.vm.kapa.rova.soap.vtj.model.Person soapPersonMock = createMockBuilder(
                fi.vm.kapa.rova.soap.vtj.model.Person.class).addMockedMethod("getEdunvalvonta").createMock();
        fi.vm.kapa.rova.soap.vtj.model.Edunvalvonta EdunvalvontaMock = createMockBuilder(
                Edunvalvonta.class).addMockedMethod("getEdunvalvontatieto").createMock();
        StringNode EdunvalvontatietoStringMock = createMockBuilder(StringNode.class).addMockedMethod(
                "getValue").createMock();

        expect(soapPersonMock.getEdunvalvonta()).andReturn(EdunvalvontaMock).times(4);
        expect(EdunvalvontaMock.getEdunvalvontatieto()).andReturn(EdunvalvontatietoStringMock).times(3);
        expect(EdunvalvontatietoStringMock.getValue()).andReturn("1").times(2);

        replay(soapPersonMock, EdunvalvontaMock, EdunvalvontatietoStringMock);

        PersonParser parser = new PersonParser();
        Person person = new Person();
        parser.parseEdunvalvontaTieto(soapPersonMock, person);

        assertEquals(true, person.isEdunvalvonta());

        verify(soapPersonMock, EdunvalvontaMock, EdunvalvontatietoStringMock);
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

        assertEquals(false, person.isEdunvalvonta());

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

        assertEquals(true, person.isEdunvalvontaEiRajoitettu());
        assertEquals(false, person.isEdunvalvontaRajoitettu());
        assertEquals(false, person.isEdunvalvontaJulistettu());

        verify(soapPersonMock, EdunvalvontaMock, rajoituskoodiStringMock);
    }

    @Test
    public void parseEdunvalvontaRajoitusKoodiRajoitettuTest() {
        fi.vm.kapa.rova.soap.vtj.model.Person soapPersonMock = createMockBuilder(
                fi.vm.kapa.rova.soap.vtj.model.Person.class).addMockedMethod("getEdunvalvonta").createMock();
        fi.vm.kapa.rova.soap.vtj.model.Edunvalvonta EdunvalvontaMock = createMockBuilder(
                Edunvalvonta.class).addMockedMethod("getRajoituskoodi").createMock();
        StringNode rajoituskoodiStringMock = createMockBuilder(StringNode.class).addMockedMethod(
                "getValue").createMock();

        expect(soapPersonMock.getEdunvalvonta()).andReturn(EdunvalvontaMock).times(5);
        expect(EdunvalvontaMock.getRajoituskoodi()).andReturn(rajoituskoodiStringMock).times(4);
        expect(rajoituskoodiStringMock.getValue()).andReturn("2").times(3);

        replay(soapPersonMock, EdunvalvontaMock, rajoituskoodiStringMock);

        PersonParser parser = new PersonParser();
        Person person = new Person();
        parser.parseEdunvalvontaRajoitusKoodi(soapPersonMock, person);

        assertEquals(false, person.isEdunvalvontaEiRajoitettu());
        assertEquals(true, person.isEdunvalvontaRajoitettu());
        assertEquals(false, person.isEdunvalvontaJulistettu());

        verify(soapPersonMock, EdunvalvontaMock, rajoituskoodiStringMock);
    }

    @Test
    public void parseEdunvalvontaRajoitusKoodiJulistettuTest() {
        fi.vm.kapa.rova.soap.vtj.model.Person soapPersonMock = createMockBuilder(
                fi.vm.kapa.rova.soap.vtj.model.Person.class).addMockedMethod("getEdunvalvonta").createMock();
        fi.vm.kapa.rova.soap.vtj.model.Edunvalvonta EdunvalvontaMock = createMockBuilder(
                Edunvalvonta.class).addMockedMethod("getRajoituskoodi").createMock();
        StringNode rajoituskoodiStringMock = createMockBuilder(StringNode.class).addMockedMethod(
                "getValue").createMock();

        expect(soapPersonMock.getEdunvalvonta()).andReturn(EdunvalvontaMock).times(6);
        expect(EdunvalvontaMock.getRajoituskoodi()).andReturn(rajoituskoodiStringMock).times(5);
        expect(rajoituskoodiStringMock.getValue()).andReturn("3").times(4);

        replay(soapPersonMock, EdunvalvontaMock, rajoituskoodiStringMock);

        PersonParser parser = new PersonParser();
        Person person = new Person();
        parser.parseEdunvalvontaRajoitusKoodi(soapPersonMock, person);

        assertEquals(false, person.isEdunvalvontaEiRajoitettu());
        assertEquals(false, person.isEdunvalvontaRajoitettu());
        assertEquals(true, person.isEdunvalvontaJulistettu());

        verify(soapPersonMock, EdunvalvontaMock, rajoituskoodiStringMock);
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

        assertEquals(false, person.isEdunvalvontaEiRajoitettu());
        assertEquals(false, person.isEdunvalvontaRajoitettu());
        assertEquals(false, person.isEdunvalvontaJulistettu());
        verify(soapPersonMock);
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
        assertEquals(0, person.getEdunvalvojat().size());
        verify(soapPersonMock);
    }

    @Test
    public void parseEdunvalvojaMultipleTest() {
        fi.vm.kapa.rova.soap.vtj.model.Person soapPersonMock = createMockBuilder(
                fi.vm.kapa.rova.soap.vtj.model.Person.class).addMockedMethod("getEdunvalvonta").createMock();
        Edunvalvonta edunvalvonta = createMockBuilder(Edunvalvonta.class).addMockedMethod(
                "getEdunvalvojaHenkilo").createMock();

        EdunvalvojaHenkilo e1 = createMockBuilder(EdunvalvojaHenkilo.class).addMockedMethod("getHetu").addMockedMethod(
                "getFirstName").addMockedMethod("getLastName").addMockedMethod("getBirthday").createMock();
        StringNode hetu1 = createMockBuilder(StringNode.class).addMockedMethod("getValue").createMock();
        CurrentFirstNames firstNameMock1 = createMockBuilder(CurrentFirstNames.class).addMockedMethod(
                "getFirstName").createMock();
        StringNode firstNameStringMock1 = createMockBuilder(StringNode.class).addMockedMethod("getValue").createMock();
        StringNode lastName1 = createMockBuilder(StringNode.class).addMockedMethod("getValue").createMock();
        StringNode bDay1 = createMockBuilder(StringNode.class).addMockedMethod("getValue").createMock();

        EdunvalvojaHenkilo e2 = createMockBuilder(EdunvalvojaHenkilo.class).addMockedMethod("getHetu").addMockedMethod(
                "getFirstName").addMockedMethod("getLastName").addMockedMethod("getBirthday").createMock();
        StringNode hetu2 = createMockBuilder(StringNode.class).addMockedMethod("getValue").createMock();
        CurrentFirstNames firstNameMock2 = createMockBuilder(CurrentFirstNames.class).addMockedMethod(
                "getFirstName").createMock();
        StringNode firstNameStringMock2 = createMockBuilder(StringNode.class).addMockedMethod("getValue").createMock();
        StringNode lastName2 = createMockBuilder(StringNode.class).addMockedMethod("getValue").createMock();
        StringNode bDay2 = createMockBuilder(StringNode.class).addMockedMethod("getValue").createMock();

        List<EdunvalvojaHenkilo> edunvalvojat = new ArrayList();
        edunvalvojat.add(e1);
        edunvalvojat.add(e2);

        expect(soapPersonMock.getEdunvalvonta()).andReturn(edunvalvonta).times(2);
        expect(edunvalvonta.getEdunvalvojaHenkilo()).andReturn(edunvalvojat).once();

        expect(e1.getHetu()).andReturn(hetu1).times(2);
        expect(e1.getFirstName()).andReturn(firstNameMock1).once();
        expect(e1.getLastName()).andReturn(lastName1).once();
        expect(e1.getBirthday()).andReturn(bDay1).once();
        expect(hetu1.getValue()).andReturn("hetu1").times(2);
        expect(firstNameMock1.getFirstName()).andReturn(firstNameStringMock1).once();
        expect(firstNameStringMock1.getValue()).andReturn("firstName1").once();
        expect(lastName1.getValue()).andReturn("lastName1").once();
        expect(bDay1.getValue()).andReturn("bday1").once();

        expect(e2.getHetu()).andReturn(hetu2).times(2);
        expect(e2.getFirstName()).andReturn(firstNameMock2).once();
        expect(e2.getLastName()).andReturn(lastName2).once();
        expect(e2.getBirthday()).andReturn(bDay2).once();
        expect(hetu2.getValue()).andReturn("hetu2").times(2);
        expect(firstNameMock2.getFirstName()).andReturn(firstNameStringMock2).once();
        expect(firstNameStringMock2.getValue()).andReturn("firstName2").once();
        expect(lastName2.getValue()).andReturn("lastName2").once();
        expect(bDay2.getValue()).andReturn("bday2").once();

        replay(soapPersonMock, edunvalvonta, e1, hetu1, firstNameMock1, firstNameStringMock1, lastName1, bDay1, e2,
                hetu2, firstNameMock2, firstNameStringMock2, lastName2, bDay2);

        PersonParser parser = new PersonParser();
        Person person = new Person();
        parser.parseEdunvalvojat(soapPersonMock, person);
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
        verify(soapPersonMock, edunvalvonta, e1, hetu1, firstNameMock1, firstNameStringMock1, lastName1, bDay1, e2,
                hetu2, firstNameMock2, firstNameStringMock2, lastName2, bDay2);
    }

    @Test
    public void parseEdunvalvontaValtuutetutTest() {

    }

    @Test
    public void parseHuoltajatEmptyTest() {
        fi.vm.kapa.rova.soap.vtj.model.Person soapPersonMock = createMockBuilder(
                fi.vm.kapa.rova.soap.vtj.model.Person.class).addMockedMethod("getHuoltaja").createMock();
        expect(soapPersonMock.getHuoltaja()).andReturn(null).once();
        replay(soapPersonMock);

        PersonParser parser = new PersonParser();
        Person person = new Person();
        parser.parseHuoltajat(soapPersonMock, person);
        assertEquals(0, person.getHuoltajat().size());
        verify(soapPersonMock);
    }

    @Test
    public void parseHuoltajatMultipleTest() {
        fi.vm.kapa.rova.soap.vtj.model.Person soapPersonMock = createMockBuilder(
                fi.vm.kapa.rova.soap.vtj.model.Person.class).addMockedMethod("getHuoltaja").createMock();

        Huoltaja huoltaja1 = createMockBuilder(Huoltaja.class).addMockedMethod("getId").addMockedMethod(
                "getFirstNames").addMockedMethod("getLastName").addMockedMethod("getHuoltotieto").createMock();
        StringNode hetu1 = createMockBuilder(StringNode.class).addMockedMethod("getValue").createMock();
        StringNode firstName1 = createMockBuilder(StringNode.class).addMockedMethod("getValue").createMock();
        StringNode lastName1 = createMockBuilder(StringNode.class).addMockedMethod("getValue").createMock();
        Huoltotieto huoltotieto1 = createMockBuilder(Huoltotieto.class).addMockedMethod(
                "getCustodyDivisionCode").createMock();
        StringNode divisionCode1 = createMockBuilder(StringNode.class).addMockedMethod("getValue").createMock();

        Huoltaja huoltaja2 = createMockBuilder(Huoltaja.class).addMockedMethod("getId").addMockedMethod(
                "getFirstNames").addMockedMethod("getLastName").addMockedMethod("getHuoltotieto").createMock();
        StringNode hetu2 = createMockBuilder(StringNode.class).addMockedMethod("getValue").createMock();
        StringNode firstName2 = createMockBuilder(StringNode.class).addMockedMethod("getValue").createMock();
        StringNode lastName2 = createMockBuilder(StringNode.class).addMockedMethod("getValue").createMock();
        Huoltotieto huoltotieto2 = createMockBuilder(Huoltotieto.class).addMockedMethod(
                "getCustodyDivisionCode").createMock();
        StringNode divisionCode2 = createMockBuilder(StringNode.class).addMockedMethod("getValue").createMock();

        Huoltaja huoltaja3 = createMockBuilder(Huoltaja.class).addMockedMethod("getId").addMockedMethod(
                "getFirstNames").addMockedMethod("getLastName").addMockedMethod("getHuoltotieto").createMock();
        StringNode hetu3 = createMockBuilder(StringNode.class).addMockedMethod("getValue").createMock();
        StringNode firstName3 = createMockBuilder(StringNode.class).addMockedMethod("getValue").createMock();
        StringNode lastName3 = createMockBuilder(StringNode.class).addMockedMethod("getValue").createMock();

        List<Huoltaja> huoltajat = new ArrayList();
        huoltajat.add(huoltaja1);
        huoltajat.add(huoltaja2);
        huoltajat.add(huoltaja3);

        expect(soapPersonMock.getHuoltaja()).andReturn(huoltajat).once();
        expect(firstName1.getValue()).andReturn("firstName1").once();
        expect(lastName1.getValue()).andReturn("lastName1").once();
        expect(hetu1.getValue()).andReturn("hetu1").once();
        expect(huoltaja1.getId()).andReturn(hetu1).once();
        expect(huoltaja1.getFirstNames()).andReturn(firstName1).once();
        expect(huoltaja1.getLastName()).andReturn(lastName1).once();
        expect(huoltaja1.getHuoltotieto()).andReturn(huoltotieto1).once();
        expect(huoltotieto1.getCustodyDivisionCode()).andReturn(divisionCode1).once();
        expect(divisionCode1.getValue()).andReturn("1").times(3);

        expect(firstName2.getValue()).andReturn("firstName2").once();
        expect(lastName2.getValue()).andReturn("lastName2").once();
        expect(hetu2.getValue()).andReturn("hetu2").once();
        expect(huoltaja2.getId()).andReturn(hetu2).once();
        expect(huoltaja2.getFirstNames()).andReturn(firstName2).once();
        expect(huoltaja2.getLastName()).andReturn(lastName2).once();
        expect(huoltaja2.getHuoltotieto()).andReturn(huoltotieto2).once();
        expect(huoltotieto2.getCustodyDivisionCode()).andReturn(divisionCode2).once();
        expect(divisionCode2.getValue()).andReturn("2").times(3);

        expect(firstName3.getValue()).andReturn("firstName3").once();
        expect(lastName3.getValue()).andReturn("lastName3").once();
        expect(hetu3.getValue()).andReturn("hetu3").once();
        expect(huoltaja3.getId()).andReturn(hetu3).once();
        expect(huoltaja3.getFirstNames()).andReturn(firstName3).once();
        expect(huoltaja3.getLastName()).andReturn(lastName3).once();
        expect(huoltaja3.getHuoltotieto()).andReturn(null).once();

        replay(soapPersonMock, huoltaja1, hetu1, firstName1, lastName1, huoltotieto1, divisionCode1, huoltaja2, hetu2,
                firstName2, lastName2, huoltotieto2, divisionCode2, huoltaja3, hetu3, firstName3, lastName3);

        PersonParser parser = new PersonParser();
        Person person = new Person();
        parser.parseHuoltajat(soapPersonMock, person);
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
        verify(soapPersonMock, huoltaja1, hetu1, firstName1, lastName1, huoltotieto1, divisionCode1, huoltaja2, hetu2,
                firstName2, lastName2, huoltotieto2, divisionCode2, huoltaja3, hetu3, firstName3, lastName3);
    }

    @Test
    public void parsePrincipalsEmptyTest() {
        fi.vm.kapa.rova.soap.vtj.model.Person soapPersonMock = createMockBuilder(
                fi.vm.kapa.rova.soap.vtj.model.Person.class).addMockedMethod("getPrincipal").createMock();
        expect(soapPersonMock.getPrincipal()).andReturn(null).once();
        replay(soapPersonMock);

        PersonParser parser = new PersonParser();
        Person person = new Person();
        parser.parsePrincipals(soapPersonMock, person);
        assertEquals(0, person.getPrincipals().size());
        verify(soapPersonMock);
    }

    @Test
    public void parsePrincipalsMultipleTest() {
        fi.vm.kapa.rova.soap.vtj.model.Person soapPersonMock = createMockBuilder(
                fi.vm.kapa.rova.soap.vtj.model.Person.class).addMockedMethod("getPrincipal").createMock();

        Principal p1 = createMockBuilder(Principal.class).addMockedMethod("getId").addMockedMethod(
                "getFirstNames").addMockedMethod("getLastName").createMock();
        StringNode hetu1 = createMockBuilder(StringNode.class).addMockedMethod("getValue").createMock();
        StringNode firstName1 = createMockBuilder(StringNode.class).addMockedMethod("getValue").createMock();
        StringNode lastName1 = createMockBuilder(StringNode.class).addMockedMethod("getValue").createMock();

        Principal p2 = createMockBuilder(Principal.class).addMockedMethod("getId").addMockedMethod(
                "getFirstNames").addMockedMethod("getLastName").createMock();
        StringNode hetu2 = createMockBuilder(StringNode.class).addMockedMethod("getValue").createMock();
        StringNode firstName2 = createMockBuilder(StringNode.class).addMockedMethod("getValue").createMock();
        StringNode lastName2 = createMockBuilder(StringNode.class).addMockedMethod("getValue").createMock();

        List<Principal> principals = new ArrayList();
        principals.add(p1);
        principals.add(p2);

        expect(soapPersonMock.getPrincipal()).andReturn(principals).once();
        expect(p1.getId()).andReturn(hetu1).once();
        expect(p1.getFirstNames()).andReturn(firstName1).once();
        expect(p1.getLastName()).andReturn(lastName1).once();
        expect(firstName1.getValue()).andReturn("firstName1").once();
        expect(lastName1.getValue()).andReturn("lastName1").once();
        expect(hetu1.getValue()).andReturn("hetu1").once();

        expect(p2.getId()).andReturn(hetu2);
        expect(p2.getFirstNames()).andReturn(firstName2).once();
        expect(p2.getLastName()).andReturn(lastName2).once();
        expect(firstName2.getValue()).andReturn("firstName2").once();
        expect(lastName2.getValue()).andReturn("lastName2").once();
        expect(hetu2.getValue()).andReturn("hetu2").once();

        replay(soapPersonMock, p1, hetu1, firstName1, lastName1, p2, hetu2, firstName2, lastName2);

        PersonParser parser = new PersonParser();
        Person person = new Person();
        parser.parsePrincipals(soapPersonMock, person);
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
        verify(soapPersonMock, p1, hetu1, firstName1, lastName1, p2, hetu2, firstName2, lastName2);
    }

    @Test
    public void parseEdunvalvontaValtuutetutEmptyTest() {
        fi.vm.kapa.rova.soap.vtj.model.Person soapPersonMock = createMockBuilder(
                fi.vm.kapa.rova.soap.vtj.model.Person.class).addMockedMethod("getEdunvalvontaValtuutus").createMock();
        expect(soapPersonMock.getEdunvalvontaValtuutus()).andReturn(null).once();
        replay(soapPersonMock);

        PersonParser parser = new PersonParser();
        Person person = new Person();
        parser.parseEdunvalvontaValtuutetut(soapPersonMock, person);
        assertEquals(0, person.getEdunvalvontaValtuutetut().size());
        verify(soapPersonMock);
    }

    @Test
    public void parseEdunvalvontaValtuutetutMultipleTest() {
        fi.vm.kapa.rova.soap.vtj.model.Person soapPersonMock = createMockBuilder(
                fi.vm.kapa.rova.soap.vtj.model.Person.class).addMockedMethod("getEdunvalvontaValtuutus").createMock();
        EdunvalvontaValtuutus edunvalvontaValtuutus = createMockBuilder(EdunvalvontaValtuutus.class).addMockedMethod(
                "getEdunvalvontaValtuutettuHenkilo").createMock();

        EdunvalvontaValtuutettuHenkilo e1 = createMockBuilder(EdunvalvontaValtuutettuHenkilo.class).addMockedMethod(
                "getHetu").addMockedMethod("getFirstName").addMockedMethod("getLastName").addMockedMethod(
                "getBirthday").createMock();
        StringNode hetu1 = createMockBuilder(StringNode.class).addMockedMethod("getValue").createMock();
        CurrentFirstNames firstNameMock1 = createMockBuilder(CurrentFirstNames.class).addMockedMethod(
                "getFirstName").createMock();
        StringNode firstNameStringMock1 = createMockBuilder(StringNode.class).addMockedMethod("getValue").createMock();
        StringNode lastName1 = createMockBuilder(StringNode.class).addMockedMethod("getValue").createMock();
        StringNode bDay1 = createMockBuilder(StringNode.class).addMockedMethod("getValue").createMock();

        EdunvalvontaValtuutettuHenkilo e2 = createMockBuilder(EdunvalvontaValtuutettuHenkilo.class).addMockedMethod(
                "getHetu").addMockedMethod("getFirstName").addMockedMethod("getLastName").addMockedMethod(
                "getBirthday").createMock();
        StringNode hetu2 = createMockBuilder(StringNode.class).addMockedMethod("getValue").createMock();
        CurrentFirstNames firstNameMock2 = createMockBuilder(CurrentFirstNames.class).addMockedMethod(
                "getFirstName").createMock();
        StringNode firstNameStringMock2 = createMockBuilder(StringNode.class).addMockedMethod("getValue").createMock();
        StringNode lastName2 = createMockBuilder(StringNode.class).addMockedMethod("getValue").createMock();
        StringNode bDay2 = createMockBuilder(StringNode.class).addMockedMethod("getValue").createMock();

        List<EdunvalvontaValtuutettuHenkilo> edunvalvojat = new ArrayList();
        edunvalvojat.add(e1);
        edunvalvojat.add(e2);

        expect(soapPersonMock.getEdunvalvontaValtuutus()).andReturn(edunvalvontaValtuutus).times(2);
        expect(edunvalvontaValtuutus.getEdunvalvontaValtuutettuHenkilo()).andReturn(edunvalvojat).once();

        expect(e1.getHetu()).andReturn(hetu1).times(2);
        expect(e1.getFirstName()).andReturn(firstNameMock1).once();
        expect(e1.getLastName()).andReturn(lastName1).once();
        expect(e1.getBirthday()).andReturn(bDay1).once();
        expect(hetu1.getValue()).andReturn("hetu1").times(2);
        expect(firstNameMock1.getFirstName()).andReturn(firstNameStringMock1).once();
        expect(firstNameStringMock1.getValue()).andReturn("firstName1").once();
        expect(lastName1.getValue()).andReturn("lastName1").once();
        expect(bDay1.getValue()).andReturn("bday1").once();

        expect(e2.getHetu()).andReturn(hetu2).times(2);
        expect(e2.getFirstName()).andReturn(firstNameMock2).once();
        expect(e2.getLastName()).andReturn(lastName2).once();
        expect(e2.getBirthday()).andReturn(bDay2).once();
        expect(hetu2.getValue()).andReturn("hetu2").times(2);
        expect(firstNameMock2.getFirstName()).andReturn(firstNameStringMock2).once();
        expect(firstNameStringMock2.getValue()).andReturn("firstName2").once();
        expect(lastName2.getValue()).andReturn("lastName2").once();
        expect(bDay2.getValue()).andReturn("bday2").once();

        replay(soapPersonMock, edunvalvontaValtuutus, e1, hetu1, firstNameMock1, firstNameStringMock1, lastName1, bDay1, e2,
                hetu2, firstNameMock2, firstNameStringMock2, lastName2, bDay2);

        PersonParser parser = new PersonParser();
        Person person = new Person();
        parser.parseEdunvalvontaValtuutetut(soapPersonMock, person);
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
        verify(soapPersonMock, edunvalvontaValtuutus, e1, hetu1, firstNameMock1, firstNameStringMock1, lastName1, bDay1, e2,
                hetu2, firstNameMock2, firstNameStringMock2, lastName2, bDay2);
    }

}