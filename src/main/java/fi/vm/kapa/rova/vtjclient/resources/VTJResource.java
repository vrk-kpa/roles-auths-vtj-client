package fi.vm.kapa.rova.vtjclient.resources;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

import fi.vm.kapa.rova.vtj.model.Person;
import fi.vm.kapa.rova.vtjclient.service.VTJService;

@Service
@Path("/vtj")
public class VTJResource {

	@Inject
	private VTJService service;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/person/{schema}/{hetu}")
	public Person getPerson(@PathParam("hetu") String hetu,
			@PathParam("schema") String schema) {
		return service.getPerson(hetu, schema);
	}
}
