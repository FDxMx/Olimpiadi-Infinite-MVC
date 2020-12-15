package it.olimpiadimvc.mapper;

import org.springframework.stereotype.Component;

import it.olimpiadimvc.dto.NazioneDto;
import it.olimpiadimvc.model.Nazione;

@Component
public class NazioneMapper extends AbstractMapper<Nazione, NazioneDto>{
	

	@Override
	public NazioneDto convertEntityToDto(Nazione entity) {
		if (entity == null) {
			return null;
		}
		NazioneDto nazioneDto = new NazioneDto();
		nazioneDto.setId(String.valueOf(entity.getId()));
		nazioneDto.setNome(entity.getNome());
		return nazioneDto;
	}

	@Override
	public Nazione convertDtoToEntity(NazioneDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
