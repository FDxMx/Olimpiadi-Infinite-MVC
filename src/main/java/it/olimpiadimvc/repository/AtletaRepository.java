package it.olimpiadimvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.olimpiadimvc.model.Atleta;

public interface AtletaRepository extends JpaRepository<Atleta, Integer>{

}
