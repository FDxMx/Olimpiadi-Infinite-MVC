package it.olimpiadimvc.validators;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.olimpiadimvc.dto.messages.GaraInsertMessageDto;
import org.apache.commons.lang3.StringUtils;

@Component
public class GaraInsertValidator implements Validator{

	@Override
	public boolean supports(Class<?> arg0) {
		return GaraInsertMessageDto.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object arg0, Errors error) {
		GaraInsertMessageDto garaInsertMessageDto = (GaraInsertMessageDto) arg0;
		if(garaInsertMessageDto.getData() != null && !garaInsertMessageDto.getData().equals("")) {
			try {
				LocalDate.parse(garaInsertMessageDto.getData());
			} catch (DateTimeParseException e) {
				error.rejectValue("data", "", "Non hai selezionato una data");
			}
		}
		if(!StringUtils.isNumeric(garaInsertMessageDto.getNumeroPartecipanti()) || Integer.parseInt(garaInsertMessageDto.getNumeroPartecipanti()) < 4 || Integer.parseInt(garaInsertMessageDto.getNumeroPartecipanti()) > 8) {
			error.rejectValue("numeroPartecipanti", "", "Campo non vaido");
		}
		if(!StringUtils.isNumeric(garaInsertMessageDto.getPunteggio())) {
			error.rejectValue("punteggio", "", "Non è un numero");
		}
	}

}
