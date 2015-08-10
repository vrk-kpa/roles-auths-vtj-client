package fi.vm.kapa.rova.soap.vtj;

import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;

import fi.vm.kapa.rova.logging.Logger;

public class CustomValidationEventHandler implements ValidationEventHandler {

    private static Logger LOG = Logger.getLogger(CustomValidationEventHandler.class, Logger.VTJ_CLIENT);

    @Override
    public boolean handleEvent(ValidationEvent event) {
        LOG.debug("Event Info: " + event);
        if (event.getMessage().contains("unexpected element")) {
            LOG.debug("Unexpected element found: " + event);
        } else {
            LOG.debug("Validation event: " + event + " " + event.getMessage());
//			System.out.println("Validation event: " + event + " " + event.getMessage());
        }
        return true;
    }
}
