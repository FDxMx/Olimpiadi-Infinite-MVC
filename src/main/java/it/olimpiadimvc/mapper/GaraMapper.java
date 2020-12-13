package it.olimpiadimvc.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.olimpiadimvc.dto.GaraDto;
import it.olimpiadimvc.model.Gara;

@Component
public class GaraMapper extends AbstractMapper<Gara, GaraDto>{
	
	@Autowired
	private DisciplinaMapper disciplinaMapper;
	
	@Autowired UtenteMapper utenteMapper;

	@Override
	public GaraDto convertEntityToDto(Gara entity) {
		if (entity == null) {
			return null;
		}
		GaraDto garaDto = new GaraDto();
		garaDto.setId(String.valueOf(entity.getId()));
		garaDto.setData(String.valueOf(entity.getData()));
		garaDto.setPunteggio(String.valueOf(entity.getPunteggio()));
		garaDto.setStato(String.valueOf(entity.getStato()));
		garaDto.setDisciplinaDto(disciplinaMapper.convertEntityToDto(entity.getDisciplina()));
		garaDto.setOrganizzatoreDto(utenteMapper.convertEntityToDto(entity.getOrganizzatore()));
		return garaDto;
	}

	@Override
	public Gara convertDtoToEntity(GaraDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
