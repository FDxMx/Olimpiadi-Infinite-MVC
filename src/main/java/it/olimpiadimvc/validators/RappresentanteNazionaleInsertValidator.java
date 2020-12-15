package it.olimpiadimvc.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.olimpiadimvc.dto.RappresentanteNazionaleDto;
import it.olimpiadimvc.dto.messages.RappresentanteNazionaleInsertMessageDto;
import it.olimpiadimvc.model.Utente;
import it.olimpiadimvc.service.RappresentanteNazionaleService;
import it.olimpiadimvc.service.UtenteService;

@Component
public class RappresentanteNazionaleInsertValidator implements Validator{
	
	@Autowired
	private RappresentanteNazionaleService rappresentanteNazionaleService;
	
	@Autowired
	private UtenteService utenteService;

	@Override
	public boolean supports(Class<?> arg0) {
		return RappresentanteNazionaleInsertMessageDto.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object arg0, Errors error) {
		RappresentanteNazionaleInsertMessageDto rappresentanteNazionaleInsertMessageDto = (RappresentanteNazionaleInsertMessageDto) arg0;
		for (RappresentanteNazionaleDto rappresentante : rappresentanteNazionaleService.findAll()) {
			if(rappresentante.getNazioneDto().getId().equals(rappresentanteNazionaleInsertMessageDto.getNazioneDto())) {
				error.rejectValue("nazioneDto", "", "Esiste già un rappresentante per questa nazione!");
			}
		}
		for (Utente utente : utenteService.findAll()) {
			if(utente.getCodiceFiscale().equals(rappresentanteNazionaleInsertMessageDto.getCodiceFiscale())) {
				error.rejectValue("codiceFiscale", "", "Esiste già un utente con questo codice fiscale!");
			}
			if(utente.getUsername().equals(rappresentanteNazionaleInsertMessageDto.getUsername())) {
				error.rejectValue("username", "", "Username già utilizzato!");
			}
		}
	}

}
