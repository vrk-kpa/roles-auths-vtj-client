package fi.vm.kapa.rova.soap.vtj;

import java.util.logging.Logger;

import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;

public class CustomValidationEventHandler implements ValidationEventHandler {
	private static Logger LOG = Logger.getLogger(CustomValidationEventHandler.class.toString());
	
	@Override
	public boolean handleEvent(ValidationEvent event) {
		LOG.finest("Event Info: " + event);
		if (event.getMessage().contains("unexpected element")){
			LOG.finest("Unexpected element found: " + event);
			return true;
		} else {
			return false;
		}
	}

}
