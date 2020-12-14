package it.olimpiadimvc.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.olimpiadimvc.dto.AtletaDto;
import it.olimpiadimvc.model.Atleta;

@Component
public class AtletaMapper extends AbstractMapper<Atleta, AtletaDto>{
	
	@Autowired
	private RappresentanteNazionaleMapper rappresentanteNazionaleMapper;

	@Override
	public AtletaDto convertEntityToDto(Atleta entity) {
		if (entity == null) {
			return null;
		}
		AtletaDto atletaDto = new AtletaDto();
		atletaDto.setId(String.valueOf(entity.getId()));
		atletaDto.setCognome(entity.getCognome());
		atletaDto.setCodiceFiscale(entity.getCodiceFiscale());
		atletaDto.setPunti(String.valueOf(entity.getPunti()));
		atletaDto.setMedaglieBronzo(String.valueOf(entity.getMedaglieBronzo()));
		atletaDto.setMedaglieArgento(String.valueOf(entity.getMedaglieArgento()));
		atletaDto.setMedaglieOro(String.valueOf(entity.getMedaglieOro()));
		atletaDto.setStato(String.valueOf(entity.getStato()));
		atletaDto.setRappresentanteNazionaleDto(rappresentanteNazionaleMapper.convertEntityToDto(entity.getRappresentanteNazionale()));
		return atletaDto;
	}

	@Override
	public Atleta convertDtoToEntity(AtletaDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
