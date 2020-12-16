package it.olimpiadimvc.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.olimpiadimvc.dto.UtenteDto;
import it.olimpiadimvc.dto.messages.AtletaInsertMessageDto;
import it.olimpiadimvc.service.UtenteService;

@Component
public class AtletaInsertValidator implements Validator{
	
	@Autowired
	private UtenteService utenteService;

	@Override
	public boolean supports(Class<?> arg0) {
		return AtletaInsertMessageDto.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object arg0, Errors error) {
		AtletaInsertMessageDto atletaInsertMessageDto = (AtletaInsertMessageDto) arg0;
		for (UtenteDto utente : utenteService.findAll()) {
			if(utente.getCodiceFiscale().equals(atletaInsertMessageDto.getCodiceFiscale())) {
				error.rejectValue("codiceFiscale", "", "Esiste già un utente con questo codice fiscale!");
			}
			if(utente.getUsername().equals(atletaInsertMessageDto.getUsername())) {
				error.rejectValue("username", "", "Username già utilizzato!");
			}
		}
	}
}
