package fi.vm.kapa.rova.vtjclient.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fi.vm.kapa.rova.soap.vtj.VTJClient;
import fi.vm.kapa.rova.soap.vtj.model.Custodian;
import fi.vm.kapa.rova.soap.vtj.model.GuardianshipAuthorizedPerson;
import fi.vm.kapa.rova.soap.vtj.model.GuardianshipPerson;
import fi.vm.kapa.rova.soap.vtj.model.Principal;
import fi.vm.kapa.rova.soap.vtj.model.VTJResponseMessage;
import fi.vm.kapa.rova.vtj.model.Person;

@Service
public class VTJService {
	private static Logger LOG = Logger.getLogger(VTJService.class.toString());
	
	@Autowired
	private VTJClient client;

	final int HETU_LENGTH = 11;
	
	public Person getPerson(String hetu, String schema, String origUserId, String origRequestId) {
		Person person = null;
		try {
			VTJResponseMessage response = client.getResponse(hetu, schema, origUserId, origRequestId);
			person = fromSoapMessage(response);
		} catch (Throwable e) {
			LOG.severe("Person parsing failed reason:"+ e);
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
		
		if (sPerson.getFirstName()!=null) {
			person.setFirstNames(sPerson.getFirstName().getFirstName().getValue());
		}
		if (sPerson.getLastName()!=null) {
			person.setLastName(sPerson.getLastName().getLastName().getValue());
		}
		if (sPerson.getDeceased() != null && sPerson.getDeceased().getDeceased() != null 
				&& sPerson.getDeceased().getDeceased().getValue() != null) {
			person.setDeceased(sPerson.getDeceased().getDeceased().getValue()
					.equals("1")); // "1" = Kuollut 
		} else {
			person.setDeceased(false);
		}

		person.setPrincipals(getPrincipals(sPerson));
		person.setCustodians(getCustodians(sPerson));
		person.setGuardians(getGuardians(sPerson));
		person.setGuardianshipAuthorizedPersons(getGuardianshipAuthorizedPersons(sPerson));
		
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
			
			person.setGuardianshipUnlimited(false);
			person.setGuardianshipLimited(false);
			person.setGuardianshipAnnounced(false);
			
			if (sPerson.getGuardianship().getRajoituskoodi() != null && sPerson.getGuardianship().getRajoituskoodi().getValue() != null) {
				if (person.isGuardianship() && sPerson.getGuardianship().getRajoituskoodi().getValue().equals("1")) { // "1" = ei rajoitettu
					person.setGuardianshipUnlimited(true);
				} else if (person.isGuardianship() && sPerson.getGuardianship().getRajoituskoodi().getValue().equals("2")) { // "2" = rajoitettu
					person.setGuardianshipLimited(true);
				} else if (person.isGuardianship() && sPerson.getGuardianship().getRajoituskoodi().getValue().equals("3")) { // "3" = julistettu
					person.setGuardianshipAnnounced(true);
				}
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
		
		//check if custodian is valid
		if (sPerson.getCustodian() != null && sPerson.getCustodian().get(0).getId().getValue().length() == HETU_LENGTH) {
			List<Custodian> huoltajat = sPerson.getCustodian();
				
			for (Custodian g : huoltajat) {
				Person huoltaja = new Person();
				huoltaja.setSsn(g.getId().getValue());
				huoltaja.setFirstNames(g.getFirstNames().getValue());
				huoltaja.setLastName(g.getLastName().getValue());
				if (g.getCustodyInformation() != null && g.getCustodyInformation().getCustodyDivisionCode() != null && 
						g.getCustodyInformation().getCustodyDivisionCode().getValue() != null) {
					huoltaja.setHuollonjakoSopimus(g.getCustodyInformation().getCustodyDivisionCode().getValue().equalsIgnoreCase("2"));
					huoltaja.setHuollonjakoMaarays(g.getCustodyInformation().getCustodyDivisionCode().getValue().equalsIgnoreCase("1"));
				}
				result.add(huoltaja);
			}
		}
		return result;
	}

	private List<Person> getPrincipals(
			fi.vm.kapa.rova.soap.vtj.model.Person sPerson) {
		
		List<Person> result = new ArrayList<Person>();
		List<Principal> principals = sPerson.getPrincipal();
		
		if (principals != null && principals.get(0).getId().getValue().length() == HETU_LENGTH) {
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
	
	private List<Person> getGuardians(
			fi.vm.kapa.rova.soap.vtj.model.Person sPerson) {
		List<Person> result = new ArrayList<Person>();
		
		if (sPerson.getGuardianship() != null) {
			List<GuardianshipPerson> henkiloedunvalvojat = sPerson.getGuardianship().getGuardianshipPerson();
			if (henkiloedunvalvojat != null) {
				for (GuardianshipPerson p : henkiloedunvalvojat) {
					if (p.getHetu().getValue() != null && p.getHetu().getValue().length() == HETU_LENGTH) {
						Person edunvalvoja = new Person();
						edunvalvoja.setSsn(p.getHetu().getValue());
						edunvalvoja.setBirthdate(p.getBirthday().getValue());
						edunvalvoja.setFirstNames(p.getFirstName().getFirstName().getValue());
						edunvalvoja.setLastName(p.getLastName().getValue());
						result.add(edunvalvoja);
					}
				}
			}
		}
		return result;
	}
	
	private List<Person> getGuardianshipAuthorizedPersons(
			fi.vm.kapa.rova.soap.vtj.model.Person sPerson) {
		List<Person> result = new ArrayList<Person>();
		
		if (sPerson.getGuardianshipAuthorization() != null) {
			List<GuardianshipAuthorizedPerson> henkiloEdunvalvojaValtuutetut = sPerson.getGuardianshipAuthorization().getGuardianshipAuthorizedPerson();
			if (henkiloEdunvalvojaValtuutetut != null) {
				for (GuardianshipAuthorizedPerson p : henkiloEdunvalvojaValtuutetut) {
					if (p.getHetu().getValue() != null && p.getHetu().getValue().length() == HETU_LENGTH) {
						Person edunvalvoja = new Person();
						edunvalvoja.setSsn(p.getHetu().getValue());
						edunvalvoja.setBirthdate(p.getBirthday().getValue());
						edunvalvoja.setFirstNames(p.getFirstName().getFirstName().getValue());
						edunvalvoja.setLastName(p.getLastName().getValue());
						result.add(edunvalvoja);
					}
				}
			}
		}
		return result;
	}
}
