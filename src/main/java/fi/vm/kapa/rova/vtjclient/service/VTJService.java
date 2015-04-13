package fi.vm.kapa.rova.vtjclient.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.xml.bind.JAXBException;

import org.eclipse.jetty.util.log.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fi.vm.kapa.rova.soap.vtj.VTJClient;
import fi.vm.kapa.rova.soap.vtj.model.Custodian;
import fi.vm.kapa.rova.soap.vtj.model.Principal;
import fi.vm.kapa.rova.soap.vtj.model.VTJResponseMessage;
import fi.vm.kapa.rova.vtj.model.Person;

@Service
public class VTJService {
	private static Logger LOG = Logger.getLogger(VTJService.class.toString());
	
	@Autowired
	private VTJClient client;

	public Person getPerson(String hetu, String schema) {
		Person person = null;
		try {
			VTJResponseMessage response = client.getResponse(hetu, schema);
			person = fromSoapMessage(response);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return person;
	}

	private Person fromSoapMessage(VTJResponseMessage message) {
		fi.vm.kapa.rova.soap.vtj.model.Person sPerson = message.getPerson();
		Person person = new Person();
		person.setSsn(sPerson.getHetu().getHetu());
		if (sPerson.getHetu().getValidityCode().equals("1")) { // "1" = hetu voimassa
			person.setSsnValid(true);
		} else {
			person.setSsnValid(false);
		}
					
		person.setFirstNames(sPerson.getFirstName().getFirstName().getValue());
		person.setLastName(sPerson.getLastName().getLastName().getValue());
		
		if (sPerson.getDeceased() != null && sPerson.getDeceased().getDeceased() != null 
				&& sPerson.getDeceased().getDeceased().getValue() != null) {
			person.setDeceased(sPerson.getDeceased().getDeceased().getValue()
					.equals("1")); // "1" = Kuollut 
		} else {
			person.setDeceased(false);
		}

		person.setPrincipals(getPrincipals(sPerson));
		person.setCustodians(getCustodians(sPerson));
		
		
		if (sPerson.getHuostaanotto() != null && sPerson.getHuostaanotto().getHuostaanottoTieto() != null
				&& sPerson.getHuostaanotto().getHuostaanottoTieto().getValue() != null) {
			person.setHuostaanotettu(sPerson.getHuostaanotto().getHuostaanottoTieto()
					.getValue().equals("1")); // "1" = huostaanotettu
		} else {
			person.setHuostaanotettu(false);
		}
			
		
		if (sPerson.getGuardianship() != null && sPerson.getGuardianship().getGuardianship() != null 
				&& sPerson.getGuardianship().getGuardianship().getValue() != null) {
			person.setGuardianship(sPerson.getGuardianship().getGuardianship().getValue().equals("1")); // "1" = Edunvalvonnassa
			
			if (person.isGuardianship() && sPerson.getGuardianship().getRajoituskoodi().getValue().equals("1")) { // "1" = ei rajoitettu
				person.setGuardianshipLimited(false);
			} else {
				person.setGuardianshipLimited(true); //TODO: Values 2 & 3 have to be checked
			}
		} else {
			person.setGuardianship(false);
		}
		
		if (sPerson.getProtectionorder() != null && sPerson.getProtectionorder().getProtectionorder() != null 
				&& sPerson.getProtectionorder().getProtectionorder().getValue() != null) {
			person.setProtectionOrder(sPerson.getProtectionorder().getProtectionorder()
					.getValue().equals("1"));
		} else {
			person.setProtectionOrder(false);
		}
		
		LOG.info("fromSoapMessage: person="+ person);
		
		return person;
	}

	private List<Person> getCustodians(
			fi.vm.kapa.rova.soap.vtj.model.Person sPerson) {
		List<Person> result = new ArrayList<Person>();
		List<Custodian> huoltajat = sPerson.getCustodian();
		
		if (huoltajat != null) {
			for (Custodian g : huoltajat) {
				Person huoltaja = new Person();
				huoltaja.setSsn(g.getId().getValue());
				huoltaja.setFirstNames(g.getFirstNames().getValue());
				huoltaja.setLastName(g.getLastName().getValue());
				//huoltaja.setHuollonjakoSopimus(g.getCustodyInformation().getCustodyDivisionCode().getValue().equalsIgnoreCase("K"));
				result.add(huoltaja);
			}
		}
		
		return result;
	}

	private List<Person> getPrincipals(
			fi.vm.kapa.rova.soap.vtj.model.Person sPerson) {
		List<Person> result = new ArrayList<Person>();
		List<Principal> principals = sPerson.getPrincipal();
		if (principals != null) {
			for (Principal p : principals) {
				Person principal = new Person();
				principal.setSsn(p.getId().getValue());
				principal.setFirstNames(p.getFirstNames().getValue());
				principal.setLastName(p.getLastName().getValue());
				result.add(principal);
			}
		}
		return result;
	}
}
