package it.olimpiadimvc.validators;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.olimpiadimvc.dto.messages.GaraUpdateMessageDto;

@Component
public class GaraUpdateValidator implements Validator{

	@Override
	public boolean supports(Class<?> arg0) {
		return GaraUpdateMessageDto.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object arg0, Errors error) {
		GaraUpdateMessageDto garaUpdateMessageDto = (GaraUpdateMessageDto) arg0;
		if(garaUpdateMessageDto.getData() != null && !garaUpdateMessageDto.getData().equals("")) {
			try {
				LocalDate.parse(garaUpdateMessageDto.getData());
			} catch (DateTimeParseException e) {
				error.rejectValue("data", "", "Non hai selezionato una data");
			}
		}
		if(!StringUtils.isNumeric(garaUpdateMessageDto.getNumeroPartecipanti()) || Integer.parseInt(garaUpdateMessageDto.getNumeroPartecipanti()) < 4 || Integer.parseInt(garaUpdateMessageDto.getNumeroPartecipanti()) > 8) {
			error.rejectValue("numeroPartecipanti", "", "Deve essere un numero compreso tra 4 e 8");
		}
		if(!StringUtils.isNumeric(garaUpdateMessageDto.getPunteggio())) {
			error.rejectValue("punteggio", "", "Deve essere un numero");
		}
		
	}

}
