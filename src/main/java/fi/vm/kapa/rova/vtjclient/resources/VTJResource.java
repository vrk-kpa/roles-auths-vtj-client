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

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Service;

import fi.vm.kapa.rova.external.model.vtj.VTJResponse;
import fi.vm.kapa.rova.logging.Logger;
import fi.vm.kapa.rova.vtjclient.service.VTJService;
import fi.vm.kapa.rova.vtjclient.service.VTJServiceException;

@Service
@Path("/vtj")
public class VTJResource {

    private static Logger log = Logger.getLogger(VTJResource.class);

    @Inject
    private VTJService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/person/{schema}/{hetu}")
    public Response getPerson(@PathParam("hetu") String hetu,
            @PathParam("schema") String schema) throws VTJServiceException {
        log.debug("Person request received.");
        VTJResponse vtjResponse = service.getVTJResponse(hetu, schema);
        return Response.ok().entity(vtjResponse).build();
    }
}
