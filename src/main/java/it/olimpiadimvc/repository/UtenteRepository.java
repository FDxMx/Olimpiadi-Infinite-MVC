package it.olimpiadimvc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.olimpiadimvc.model.Utente;

public interface UtenteRepository extends JpaRepository<Utente, Integer>{
	
	Utente findByUsername(String username);
	
	@Query("from Utente u where u.ruolo = 'ORGANIZZATORE'")
	List<Utente> findAllOrganizzatori();
	
}
