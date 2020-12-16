package it.olimpiadimvc.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.olimpiadimvc.dto.RappresentanteNazionaleDto;
import it.olimpiadimvc.model.RappresentanteNazionale;

@Component
public class RappresentanteNazionaleMapper extends AbstractMapper<RappresentanteNazionale, RappresentanteNazionaleDto>{
	
	@Autowired
	private NazioneMapper nazioneMapper;

	@Override
	public RappresentanteNazionaleDto convertEntityToDto(RappresentanteNazionale entity) {
		if (entity == null) {
			return null;
		}
		RappresentanteNazionaleDto rappresentanteNazionaleDto = new RappresentanteNazionaleDto();
		rappresentanteNazionaleDto.setId(String.valueOf(entity.getId()));
		rappresentanteNazionaleDto.setNome(entity.getNome());
		rappresentanteNazionaleDto.setCognome(entity.getCognome());
		rappresentanteNazionaleDto.setCodiceFiscale(entity.getCodiceFiscale());
		rappresentanteNazionaleDto.setNazioneDto(nazioneMapper.convertEntityToDto(entity.getNazione()));
		return rappresentanteNazionaleDto;
	}

	@Override
	public RappresentanteNazionale convertDtoToEntity(RappresentanteNazionaleDto dto) {
		if (dto == null) {
			return null;
		}
		RappresentanteNazionale rappresentanteNazionale = new RappresentanteNazionale();
		rappresentanteNazionale.setId(Integer.parseInt(dto.getId()));
		rappresentanteNazionale.setNome(dto.getNome());
		rappresentanteNazionale.setCognome(dto.getCognome());
		rappresentanteNazionale.setCodiceFiscale(dto.getCodiceFiscale());
		rappresentanteNazionale.setNazione(nazioneMapper.convertDtoToEntity(dto.getNazioneDto()));
		return rappresentanteNazionale;
	}

}
