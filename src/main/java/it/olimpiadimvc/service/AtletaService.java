package it.olimpiadimvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.olimpiadimvc.dto.AtletaDto;
import it.olimpiadimvc.mapper.AtletaMapper;
import it.olimpiadimvc.model.Atleta;
import it.olimpiadimvc.repository.AtletaRepository;

@Service
public class AtletaService {
	
	@Autowired
	private AtletaRepository atletaRepository;
	
	@Autowired
	private AtletaMapper atletaMapper;
	
	public List<AtletaDto> findAtletiByGara(Integer idGara){
		List<Atleta> atleti = atletaRepository.findAtletiByGara(idGara);
		return atletaMapper.convertEntityToDto(atleti);
	}
	
	public List<AtletaDto> findAtletiByRappresentante(Integer id){
		return atletaRepository.findAtletiByRappresentante(id);
	}

}
