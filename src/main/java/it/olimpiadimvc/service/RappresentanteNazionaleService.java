package it.olimpiadimvc.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import it.olimpiadimvc.dto.RappresentanteNazionaleDto;
import it.olimpiadimvc.dto.messages.RappresentanteNazionaleInsertMessageDto;
import it.olimpiadimvc.dto.messages.RappresentanteNazionaleSearchMessageDto;
import it.olimpiadimvc.mapper.RappresentanteNazionaleMapper;
import it.olimpiadimvc.model.RappresentanteNazionale;
import it.olimpiadimvc.model.Ruolo;
import it.olimpiadimvc.model.Utente;
import it.olimpiadimvc.repository.RappresentanteNazionaleRepository;
import it.olimpiadimvc.repository.UtenteRepository;

@Service
public class RappresentanteNazionaleService {
	
	@Autowired
	private RappresentanteNazionaleRepository rappresentanteNazionaleRepository;
	
	@Autowired
	private RappresentanteNazionaleMapper rappresentanteNazionaleMapper;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private NazioneService nazioneService;
	
	@Autowired
	private UtenteRepository utenteRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	public List<RappresentanteNazionaleDto> findByExample(RappresentanteNazionaleSearchMessageDto rappresentanteNazionaleSearchMessageDto){
		
		 CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	        CriteriaQuery<RappresentanteNazionale> cq = cb.createQuery(RappresentanteNazionale.class);

	        Root<RappresentanteNazionale> rappresentanteNazionale = cq.from(RappresentanteNazionale.class);
	        List<Predicate> predicates = new ArrayList<>();

	        if (rappresentanteNazionaleSearchMessageDto.getNome() != null && !rappresentanteNazionaleSearchMessageDto.getNome().equals("")) {
	            predicates.add(cb.like(rappresentanteNazionale.get("nome"), "%" + rappresentanteNazionaleSearchMessageDto.getNome() + "%"));
	        }

	        if (rappresentanteNazionaleSearchMessageDto.getCognome() != null && !rappresentanteNazionaleSearchMessageDto.getCognome().equals("")) {
	        	predicates.add(cb.like(rappresentanteNazionale.get("cognome"), "%" + rappresentanteNazionaleSearchMessageDto.getCognome() + "%"));
	        }
	        
	        if (rappresentanteNazionaleSearchMessageDto.getCodiceFiscale() != null && !rappresentanteNazionaleSearchMessageDto.getCodiceFiscale().equals("")) {
	        	predicates.add(cb.like(rappresentanteNazionale.get("codiceFiscale"), "%" + rappresentanteNazionaleSearchMessageDto.getCodiceFiscale() + "%"));
	        }
	        
	        if (rappresentanteNazionaleSearchMessageDto.getNazioneDto() != null && !rappresentanteNazionaleSearchMessageDto.getNazioneDto().equals("")) {
	            predicates.add(cb.equal(rappresentanteNazionale.get("nazione").get("id"), rappresentanteNazionaleSearchMessageDto.getNazioneDto()));
	        }

	        cq.where(predicates.toArray(new Predicate[0]));
	        return rappresentanteNazionaleMapper.convertEntityToDto(entityManager.createQuery(cq).getResultList());
	}
	
	public List<RappresentanteNazionaleDto> findAll(){
		List<RappresentanteNazionale> rappresentanti = rappresentanteNazionaleRepository.findAll();
		return rappresentanteNazionaleMapper.convertEntityToDto(rappresentanti);
	}
	
	public void insert(RappresentanteNazionaleInsertMessageDto rappresentanteNazionaleInsertMessageDto) {
		RappresentanteNazionale rappresentanteNazionale = new RappresentanteNazionale();
		rappresentanteNazionale.setNome(rappresentanteNazionaleInsertMessageDto.getNome());
		rappresentanteNazionale.setCognome(rappresentanteNazionaleInsertMessageDto.getCognome());
		rappresentanteNazionale.setCodiceFiscale(rappresentanteNazionaleInsertMessageDto.getCodiceFiscale());
		rappresentanteNazionale.setNazione(nazioneService.findById(Integer.parseInt(rappresentanteNazionaleInsertMessageDto.getNazioneDto())));
		
		Utente utente = new Utente();
		utente.setNome(rappresentanteNazionale.getNome());
		utente.setCognome(rappresentanteNazionale.getCognome());
		utente.setCodiceFiscale(rappresentanteNazionale.getCodiceFiscale());
		utente.setUsername(rappresentanteNazionaleInsertMessageDto.getUsername());
		utente.setPassword(passwordEncoder.encode(rappresentanteNazionaleInsertMessageDto.getPassword()));
		utente.setRuolo(Ruolo.RAPPRESENTANTE_NAZIONALE);
		
		rappresentanteNazionaleRepository.save(rappresentanteNazionale);
		utenteRepository.save(utente);
	}

}
