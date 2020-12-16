package it.olimpiadimvc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.olimpiadimvc.model.Gara;

public interface GaraRepository extends JpaRepository<Gara, Integer> {
	
	@Query("from Gara g join fetch g.partecipanti a where a.id = ?1")
	List<Gara> findGareByAtleta(Integer id);
	
}
