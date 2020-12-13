package it.olimpiadimvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.olimpiadimvc.model.Utente;

public interface UtenteRepository extends JpaRepository<Utente, Integer>{
	
	Utente findByUsername(String username);
	
}
