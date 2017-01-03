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
import fi.vm.kapa.rova.external.model.vtj.VTJResponse;
import fi.vm.kapa.rova.logging.Logger;
import fi.vm.kapa.rova.soap.vtj.VTJClient;
import fi.vm.kapa.rova.soap.vtj.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.List;

import static fi.vm.kapa.rova.logging.Logger.Field.*;

@Service
public class VTJService {

    private static Logger LOG = Logger.getLogger(VTJService.class);

    @Autowired
    private VTJClient client;

    @Autowired
    private PersonParser personParser;

    public VTJResponse getVTJResponse(String hetu, String schema) throws VTJServiceException {
        Person person = null;
        VTJResponse vtjResponse = new VTJResponse(); // person == null & success == false as default
        
        long startTime = System.currentTimeMillis();

        VTJResponseMessage response = null;
        try {
            response = client.getResponse(hetu, schema);
        } catch (JAXBException e) {
            throw new VTJServiceException("VTJ request failed: " + e.getMessage(), e);
        }

        logVTJRequest(schema, startTime, System.currentTimeMillis());

        fi.vm.kapa.rova.soap.vtj.model.Person sPerson = response.getPerson();
        person = new Person();
        personParser.parseHetu(sPerson, person);
        personParser.parseIdentity(sPerson, person);
        personParser.parseHuoltajat(sPerson, person);
        personParser.parsePrincipals(sPerson, person);
        personParser.parseEdunvalvontaTieto(sPerson, person);
        personParser.parseEdunvalvontaRajoitusKoodi(sPerson, person);
        personParser.parseEdunvalvojat(sPerson, person);
        personParser.parseEdunvalvontaValtuutetut(sPerson, person);
        personParser.parseTurvakielto(sPerson, person);
        personParser.parseHuostaanotto(sPerson, person);
        personParser.parseIsDeceased(sPerson, person);
        LOG.debug("Parsed fromSoapMessage: person=" + person);
        vtjResponse.setPerson(person);
        vtjResponse.setSuccess(true);

        return vtjResponse;
    }

    private void logVTJRequest(String schema, long startTime, long currentTimeMillis) {
        Logger.LogMap logmap = LOG.infoMap();
        logmap.set(OPERATION, schema);
        logmap.set(DURATION, currentTimeMillis - startTime);
        logmap.log();
    }

    public void setPersonParser(PersonParser personParser) {
        this.personParser = personParser;
    }

    void setClient(VTJClient client) {
        this.client = client;
    }

    static void setLOG(Logger LOG) {
        VTJService.LOG = LOG;
    }
}
