package it.olimpiadimvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.olimpiadimvc.dto.DisciplinaDto;
import it.olimpiadimvc.mapper.DisciplinaMapper;
import it.olimpiadimvc.model.Disciplina;
import it.olimpiadimvc.repository.DisciplinaRepository;

@Service
public class DisciplinaService {
	
	@Autowired
	private DisciplinaRepository disciplinaRepository;
	
	@Autowired
	private DisciplinaMapper disciplinaMapper;
	
	public List<DisciplinaDto> findAll(){
		List<Disciplina> discipline = disciplinaRepository.findAll();
		return disciplinaMapper.convertEntityToDto(discipline);
	}
	
	public DisciplinaDto findById(Integer id) {
		return disciplinaMapper.convertEntityToDto(disciplinaRepository.findById(id).get());
	}

}
