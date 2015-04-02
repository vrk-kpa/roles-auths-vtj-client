package fi.vm.kapa.rova.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

import fi.vm.kapa.rova.vtjclient.resources.VTJResource;

@ApplicationPath("/")
public class ServiceConfiguration extends ResourceConfig {
	public ServiceConfiguration() {
		register(VTJResource.class);
		//packages("fi.vm.kapa.rova.vtjclient.resources");
	}
}
