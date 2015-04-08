package fi.vm.kapa.rova.config;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import fi.vm.kapa.rova.rest.validation.ValidationContainerRequestFilter;
import fi.vm.kapa.rova.vtjclient.resources.VTJResource;

@ApplicationPath("/")
public class ServiceConfiguration extends ResourceConfig {
	
	@Value("${api_key}")
	String apiKey;
	
	public ServiceConfiguration() {
		register(VTJResource.class);
		//register(new ValidationContainerRequestFilter("salainenApiKey"));
	}
	
	@PostConstruct
	public void init() {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		register(new ValidationContainerRequestFilter(apiKey));
	}
}
