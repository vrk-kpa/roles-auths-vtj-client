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

import fi.vm.kapa.rova.soap.vtj.VTJClient;
import fi.vm.kapa.rova.soap.vtj.model.Person;
import fi.vm.kapa.rova.soap.vtj.model.VTJResponseMessage;
import org.easymock.EasyMock;
import org.junit.Test;

import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.createMock;;

/**
 * Created by Juha Korkalainen on 10/7/16.
 */
public class VTJServiceTest {
    VTJResponseMessage responseMock = createMock(VTJResponseMessage.class);
    VTJClient clientMock = createMock(VTJClient.class);
    PersonParser parserMock = createMock(PersonParser.class);

    @Test
    public void getVTJResponse() throws Exception {
        Person sPerson = new Person();
        expect(responseMock.getPerson()).andReturn(sPerson);
        expect(clientMock.getResponse(EasyMock.anyString(), EasyMock.anyString())).andReturn(responseMock).once();

        parserMock.parseHetu(EasyMock.anyObject(), EasyMock.anyObject());
        expectLastCall();
        parserMock.parseIdentity(EasyMock.anyObject(), EasyMock.anyObject());
        expectLastCall();
        parserMock.parseHuoltajat(EasyMock.anyObject(), EasyMock.anyObject());
        expectLastCall();
        parserMock.parsePrincipals(EasyMock.anyObject(), EasyMock.anyObject());
        expectLastCall();
        parserMock.parseEdunvalvontaTieto(EasyMock.anyObject(), EasyMock.anyObject());
        expectLastCall();
        parserMock.parseEdunvalvontaRajoitusKoodi(EasyMock.anyObject(), EasyMock.anyObject());
        expectLastCall();
        parserMock.parseEdunvalvojat(EasyMock.anyObject(), EasyMock.anyObject());
        expectLastCall();
        parserMock.parseEdunvalvontaValtuutetut(EasyMock.anyObject(), EasyMock.anyObject());
        expectLastCall();
        parserMock.parseTurvakielto(EasyMock.anyObject(), EasyMock.anyObject());
        expectLastCall();
        parserMock.parseHuostaanotto(EasyMock.anyObject(), EasyMock.anyObject());
        expectLastCall();
        parserMock.parseIsDeceased(EasyMock.anyObject(), EasyMock.anyObject());
        expectLastCall();

        replay(clientMock, parserMock, responseMock);

        VTJService service = new VTJService();
        service.setClient(clientMock);
        service.setPersonParser(parserMock);
        service.getVTJResponse("HETU", "SCHEMA");

        verify(clientMock, parserMock, responseMock);
    }

    @Test(expected = VTJServiceException.class)
    public void getVTJFaultFound() throws Exception {
        VTJResponseMessage faultResponseMock = createMock(VTJResponseMessage.class);
        expect(faultResponseMock.getPerson()).andReturn(null).once();
        expect(faultResponseMock.getFaultCode()).andReturn("1234").times(2);
        expect(faultResponseMock.getFaultString()).andReturn("VTJ-kutsu hylätty").once();
        expect(clientMock.getResponse(EasyMock.anyString(), EasyMock.anyString())).andReturn(faultResponseMock).once();

        replay(clientMock, faultResponseMock);

        VTJService service = new VTJService();
        service.setClient(clientMock);
        service.getVTJResponse("HETU", "SCHEMA");
    }

    @Test(expected = VTJServiceException.class)
    public void getVTJFaultNotFound() throws Exception {
        VTJResponseMessage faultResponseMock = createMock(VTJResponseMessage.class);
        expect(faultResponseMock.getPerson()).andReturn(null).once();
        expect(faultResponseMock.getFaultCode()).andReturn(null).once();
        expect(clientMock.getResponse(EasyMock.anyString(), EasyMock.anyString())).andReturn(faultResponseMock).once();

        replay(clientMock, faultResponseMock);

        VTJService service = new VTJService();
        service.setClient(clientMock);
        service.getVTJResponse("HETU", "SCHEMA");
    }
}
