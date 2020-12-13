package it.olimpiadimvc.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.olimpiadimvc.dto.GaraDto;
import it.olimpiadimvc.dto.messages.GaraSearchMessageDto;
import it.olimpiadimvc.mapper.GaraMapper;
import it.olimpiadimvc.model.Gara;
import it.olimpiadimvc.model.StatoGara;

@Service
public class GaraService {
	
	@Autowired
	private GaraMapper garaMapper;
	
	@Autowired
    private EntityManager entityManager;
	
	public List<GaraDto> findByExample(GaraSearchMessageDto garaSearchMessageDto){
		
		 CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	        CriteriaQuery<Gara> cq = cb.createQuery(Gara.class);

	        Root<Gara> gara = cq.from(Gara.class);
	        List<Predicate> predicates = new ArrayList<>();

	        if (garaSearchMessageDto.getData() != null && !garaSearchMessageDto.getData().equals("")) {
	            predicates.add(cb.equal(gara.get("data"), LocalDate.parse(garaSearchMessageDto.getData())));
	        }

	        if (garaSearchMessageDto.getPunteggio() != null && !garaSearchMessageDto.getPunteggio().equals("")) {
	            predicates.add(cb.like(gara.get("punteggio"), "%" + Integer.parseInt(garaSearchMessageDto.getPunteggio()) + "%"));
	        }

	        if (garaSearchMessageDto.getStato() != null && !garaSearchMessageDto.getStato().equals("")) {
	            predicates.add(cb.equal(gara.get("stato"), StatoGara.valueOf(garaSearchMessageDto.getStato())));
	        }

	        if (garaSearchMessageDto.getDisciplinaDto() != null && !garaSearchMessageDto.getDisciplinaDto().equals("")) {
	            predicates.add(cb.equal(gara.get("disciplina").get("id"), garaSearchMessageDto.getDisciplinaDto()));
	        }
	        
	        if (garaSearchMessageDto.getOrganizzatoreDto() != null && !garaSearchMessageDto.getOrganizzatoreDto().equals("")) {
	            predicates.add(cb.equal(gara.get("organizzatore").get("id"), garaSearchMessageDto.getOrganizzatoreDto()));
	        }

	        cq.where(predicates.toArray(new Predicate[0]));
	        return garaMapper.convertEntityToDto(entityManager.createQuery(cq).getResultList());
	}

}
