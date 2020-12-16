package it.olimpiadimvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.olimpiadimvc.model.RappresentanteNazionale;

public interface RappresentanteNazionaleRepository extends JpaRepository<RappresentanteNazionale, Integer>{
	
	@Query("from RappresentanteNazionale r join r.utente u where u.id = ?1")
	RappresentanteNazionale findRappresentanteByUtente(Integer id);
}
