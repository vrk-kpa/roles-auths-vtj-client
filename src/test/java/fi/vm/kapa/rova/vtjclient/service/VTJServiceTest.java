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
import fi.vm.kapa.rova.soap.vtj.model.VTJResponseMessage;
import org.easymock.EasyMock;
import org.junit.Test;

import static org.easymock.EasyMock.createMockBuilder;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.easymock.EasyMock.expect;


/**
 * Created by Juha Korkalainen on 10/7/16.
 */
public class VTJServiceTest {

    @Test
    public void getVTJResponse() throws Exception {
        VTJResponseMessage responseMock = createMockBuilder(VTJResponseMessage.class).createNiceMock();
        VTJClient clientMock = createMockBuilder(VTJClient.class).addMockedMethod("getResponse").createMock();
        PersonParser parserMock = createMockBuilder(PersonParser.class).addMockedMethod("parseHetu").addMockedMethod(
                "parseIdentity").addMockedMethod("parseHuoltajat").addMockedMethod("parsePrincipals").addMockedMethod(
                "parseEdunvalvontaTieto").addMockedMethod("parseEdunvalvontaRajoitusKoodi").addMockedMethod(
                "parseEdunvalvojat").addMockedMethod("parseEdunvalvontaValtuutetut").addMockedMethod(
                "parseTurvakielto").addMockedMethod("parseHuostaanotto").addMockedMethod(
                "parseIsDeceased").createMock();

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

        replay(clientMock, parserMock);

        VTJService service = new VTJService();
        service.setClient(clientMock);
        service.setPersonParser(parserMock);
        service.getVTJResponse(EasyMock.anyString(), EasyMock.anyString());

        verify(clientMock, parserMock);
    }
}
