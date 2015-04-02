package fi.vm.kapa.rova.soap.vtj;

import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;

public class CustomValidationEventHandler implements ValidationEventHandler {

	@Override
	public boolean handleEvent(ValidationEvent event) {
		System.out.println("Event Info: " + event);
		if (event.getMessage().contains("unexpected element")){
			System.out.println("true");
			return true;
		} else {
			return false;
		}
	}

}
