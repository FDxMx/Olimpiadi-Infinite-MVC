package it.olimpiadimvc.mapper;

import org.springframework.stereotype.Component;

import it.olimpiadimvc.dto.DisciplinaDto;
import it.olimpiadimvc.model.Disciplina;

@Component
public class DisciplinaMapper extends AbstractMapper<Disciplina, DisciplinaDto>{

	@Override
	public DisciplinaDto convertEntityToDto(Disciplina entity) {
		if (entity == null) {
			return null;
		}
		DisciplinaDto disciplinaDto = new DisciplinaDto();
		disciplinaDto.setId(String.valueOf(entity.getId()));
		disciplinaDto.setNome(entity.getNome());
		return disciplinaDto;
	}

	@Override
	public Disciplina convertDtoToEntity(DisciplinaDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
