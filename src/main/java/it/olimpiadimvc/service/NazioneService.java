package it.olimpiadimvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.olimpiadimvc.dto.NazioneDto;
import it.olimpiadimvc.mapper.NazioneMapper;
import it.olimpiadimvc.model.Nazione;
import it.olimpiadimvc.repository.NazioneRepository;

@Service
public class NazioneService {
	
	@Autowired
	private NazioneRepository nazioneRepository;
	
	@Autowired
	private NazioneMapper nazioneMapper;
	
	public List<NazioneDto> findAll(){
		List<Nazione> nazioni = nazioneRepository.findAll();
		return nazioneMapper.convertEntityToDto(nazioni);
	}
	
	public Nazione findById(Integer id) {
		return nazioneRepository.findById(id).get();
	}

}
