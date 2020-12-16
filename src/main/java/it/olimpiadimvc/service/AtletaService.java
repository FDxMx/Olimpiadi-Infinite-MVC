package it.olimpiadimvc.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import it.olimpiadimvc.dto.AtletaDto;
import it.olimpiadimvc.dto.messages.AtletaInsertMessageDto;
import it.olimpiadimvc.dto.messages.AtletaSearchMessageDto;
import it.olimpiadimvc.mapper.AtletaMapper;
import it.olimpiadimvc.mapper.RappresentanteNazionaleMapper;
import it.olimpiadimvc.model.Atleta;
import it.olimpiadimvc.model.Ruolo;
import it.olimpiadimvc.model.StatoAtleta;
import it.olimpiadimvc.model.Utente;
import it.olimpiadimvc.repository.AtletaRepository;
import it.olimpiadimvc.repository.UtenteRepository;

@Service
public class AtletaService {
	
	@Autowired
	private AtletaRepository atletaRepository;
	
	@Autowired
	private AtletaMapper atletaMapper;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UtenteRepository utenteRepository;
	
	@Autowired
	private RappresentanteNazionaleService rappresentanteNazionaleService;
	
	@Autowired
	private RappresentanteNazionaleMapper rappresentanteNazionaleMapper;
	
	public List<AtletaDto> findAtletiByGara(Integer idGara){
		List<Atleta> atleti = atletaRepository.findAtletiByGara(idGara);
		return atletaMapper.convertEntityToDto(atleti);
	}
	
	public List<AtletaDto> findAtletiByRappresentante(Integer id){
		List<Atleta> atleti = atletaRepository.findAtletiByRappresentante(id);
		return atletaMapper.convertEntityToDto(atleti);
	}
	
	public AtletaDto findById(Integer id) {
		return atletaMapper.convertEntityToDto(atletaRepository.findById(id).get());
	}
	
	public List<AtletaDto> findByExample(AtletaSearchMessageDto atletaSearchMessageDto){
		
		 CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	        CriteriaQuery<Atleta> cq = cb.createQuery(Atleta.class);

	        Root<Atleta> atleta = cq.from(Atleta.class);
	        List<Predicate> predicates = new ArrayList<>();

	        if (atletaSearchMessageDto.getNome() != null && !atletaSearchMessageDto.getNome().equals("")) {
	            predicates.add(cb.like(atleta.get("nome"), "%" + atletaSearchMessageDto.getNome() + "%"));
	        }

	        if (atletaSearchMessageDto.getCognome() != null && !atletaSearchMessageDto.getCognome().equals("")) {
	        	predicates.add(cb.like(atleta.get("cognome"), "%" + atletaSearchMessageDto.getCognome() + "%"));
	        }
	        
	        if (atletaSearchMessageDto.getCodiceFiscale() != null && !atletaSearchMessageDto.getCodiceFiscale().equals("")) {
	        	predicates.add(cb.like(atleta.get("codiceFiscale"), "%" + atletaSearchMessageDto.getCodiceFiscale() + "%"));
	        }
	        
	        if (atletaSearchMessageDto.getStato() != null && !atletaSearchMessageDto.getStato().equals("")) {
	        	predicates.add(cb.equal(atleta.get("stato"), StatoAtleta.valueOf(atletaSearchMessageDto.getStato())));	        }
	        
	        if (atletaSearchMessageDto.getRappresentanteNazionaleDto() != null && !atletaSearchMessageDto.getRappresentanteNazionaleDto().equals("")) {
	            predicates.add(cb.equal(atleta.get("rappresentanteNazionale").get("id"), atletaSearchMessageDto.getRappresentanteNazionaleDto()));
	        }

	        cq.where(predicates.toArray(new Predicate[0]));
	        return atletaMapper.convertEntityToDto(entityManager.createQuery(cq).getResultList());
	}
	
	public void insert(AtletaInsertMessageDto atletaInsertMessageDto) {
		Atleta atleta = new Atleta();
		atleta.setNome(atletaInsertMessageDto.getNome());
		atleta.setCognome(atletaInsertMessageDto.getCognome());
		atleta.setCodiceFiscale(atletaInsertMessageDto.getCodiceFiscale());
		
		Utente utente = new Utente();
		utente.setNome(atletaInsertMessageDto.getNome());
		utente.setCognome(atletaInsertMessageDto.getCognome());
		utente.setCodiceFiscale(atletaInsertMessageDto.getCodiceFiscale());
		utente.setUsername(atletaInsertMessageDto.getUsername());
		utente.setPassword(passwordEncoder.encode(atletaInsertMessageDto.getPassword()));
		utente.setRuolo(Ruolo.ATLETA);
		
		utenteRepository.save(utente);
		atleta.setUtente(utente);
		Utente utenteSession = utenteRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		atleta.setRappresentanteNazionale(rappresentanteNazionaleMapper.convertDtoToEntity(rappresentanteNazionaleService.findRappresentanteByUtente(utenteSession.getId())));
		atletaRepository.save(atleta);
	}

}
