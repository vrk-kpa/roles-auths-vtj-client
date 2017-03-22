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
package fi.vm.kapa.rova.vtjclient.resources;

import fi.vm.kapa.rova.external.model.vtj.VTJResponse;
import fi.vm.kapa.rova.logging.Logger;
import fi.vm.kapa.rova.rest.exception.WebApplicationException;
import fi.vm.kapa.rova.vtj.VTJ;
import fi.vm.kapa.rova.vtjclient.service.VTJService;
import fi.vm.kapa.rova.vtjclient.service.VTJServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.MediaType;

@RestController
public class VTJResource implements VTJ {

    private static Logger log = Logger.getLogger(VTJResource.class);

    @Autowired
    private VTJService service;

    @GetMapping(
            value = VTJ_PERSON_PATH,
            produces = MediaType.APPLICATION_JSON
    )
    public VTJResponse getPerson(@PathVariable("hetu") String hetu,
            @PathVariable("schema") String schema) {
        log.debug("Person request received.");
        VTJResponse vtjResponse;
        try {
            vtjResponse = service.getVTJResponse(hetu, schema);
        } catch (VTJServiceException e) {
            throw new WebApplicationException(e, 204);
        }
        return vtjResponse;
    }
}
