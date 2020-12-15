package it.olimpiadimvc.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.olimpiadimvc.dto.RappresentanteNazionaleDto;
import it.olimpiadimvc.dto.messages.RappresentanteNazionaleUpdateMessageDto;
import it.olimpiadimvc.model.Utente;
import it.olimpiadimvc.service.RappresentanteNazionaleService;
import it.olimpiadimvc.service.UtenteService;

@Component
public class RappresentanteNazionaleUpdateValidator implements Validator{
	
	@Autowired
	private UtenteService utenteService;
	
	@Autowired
	private RappresentanteNazionaleService rappresentanteNazionaleService;

	@Override
	public boolean supports(Class<?> arg0) {
		return RappresentanteNazionaleUpdateMessageDto.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object arg0, Errors error) {
		RappresentanteNazionaleUpdateMessageDto rappresentanteNazionaleUpdateMessageDto = (RappresentanteNazionaleUpdateMessageDto) arg0;
		for (Utente utente : utenteService.findAll()) {
			RappresentanteNazionaleDto rappresentante = rappresentanteNazionaleService.findById(Integer.parseInt(rappresentanteNazionaleUpdateMessageDto.getId()));
			if(utente.getCodiceFiscale().equals(rappresentanteNazionaleUpdateMessageDto.getCodiceFiscale()) && !rappresentanteNazionaleUpdateMessageDto.getCodiceFiscale().equals(rappresentante.getCodiceFiscale())) {
				error.rejectValue("codiceFiscale", "", "Esiste già un utente con questo codice fiscale!");
			}
		}
	}

}
