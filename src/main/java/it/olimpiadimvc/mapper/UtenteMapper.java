package it.olimpiadimvc.mapper;

import org.springframework.stereotype.Component;

import it.olimpiadimvc.dto.UtenteDto;
import it.olimpiadimvc.model.Utente;

@Component
public class UtenteMapper extends AbstractMapper<Utente, UtenteDto>{

	@Override
	public UtenteDto convertEntityToDto(Utente entity) {
		if (entity == null) {
			return null;
		}
		UtenteDto utenteDto = new UtenteDto();
		utenteDto.setId(String.valueOf(entity.getId()));
		utenteDto.setNome(entity.getNome());
		utenteDto.setCognome(entity.getCognome());
		utenteDto.setUsername(entity.getUsername());
		utenteDto.setNome(entity.getNome());
		utenteDto.setCodiceFiscale(entity.getCodiceFiscale());
		utenteDto.setRuolo(String.valueOf(entity.getRuolo()));
		return utenteDto;
	}

	@Override
	public Utente convertDtoToEntity(UtenteDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
