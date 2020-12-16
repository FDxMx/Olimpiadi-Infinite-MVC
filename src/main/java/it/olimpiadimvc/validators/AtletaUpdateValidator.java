package it.olimpiadimvc.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.olimpiadimvc.dto.AtletaDto;
import it.olimpiadimvc.dto.UtenteDto;
import it.olimpiadimvc.dto.messages.AtletaUpdateMessageDto;
import it.olimpiadimvc.service.AtletaService;
import it.olimpiadimvc.service.UtenteService;

@Component
public class AtletaUpdateValidator implements Validator{
	
	@Autowired
	private UtenteService utenteService;
	
	@Autowired
	private AtletaService atletaService;

	@Override
	public boolean supports(Class<?> arg0) {
		return AtletaUpdateMessageDto.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object arg0, Errors error) {
		AtletaUpdateMessageDto atletaUpdateMessageDto = (AtletaUpdateMessageDto) arg0;
		for (UtenteDto utente : utenteService.findAll()) {
			AtletaDto atletaDto = atletaService.findById(Integer.parseInt(atletaUpdateMessageDto.getId()));
			if(utente.getCodiceFiscale().equals(atletaUpdateMessageDto.getCodiceFiscale()) && !atletaUpdateMessageDto.getCodiceFiscale().equals(atletaDto.getCodiceFiscale())) {
				error.rejectValue("codiceFiscale", "", "Esiste già un utente con questo codice fiscale!");
			}
		}
		if(atletaUpdateMessageDto.getDisciplineDto().size() > 3) {
			error.rejectValue("disciplineDto", "", "Non puoi selezionare più di 3 discipline!");
		}
	}

}
