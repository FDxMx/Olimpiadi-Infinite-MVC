package it.olimpiadimvc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.olimpiadimvc.model.Atleta;

public interface AtletaRepository extends JpaRepository<Atleta, Integer>{
	
	@Query("from Atleta a join fetch a.gare g where g.id = ?1")
	List<Atleta> findAtletiByGara(Integer idGara);
	
	@Query("from Atleta a join a.rappresentanteNazionale r where r.id = ?1")
	List<Atleta> findAtletiByRappresentante(Integer id);

}
