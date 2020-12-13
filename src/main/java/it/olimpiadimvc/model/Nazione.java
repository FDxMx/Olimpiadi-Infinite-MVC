package it.olimpiadimvc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Nazione {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	@OneToOne
	@JoinColumn(name = "rappresentante_fk", nullable = false)
	private RappresentanteNazionale rappresentanteNazionale;
	
	public Nazione () {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public RappresentanteNazionale getRappresentanteNazionale() {
		return rappresentanteNazionale;
	}

	public void setRappresentanteNazionale(RappresentanteNazionale rappresentanteNazionale) {
		this.rappresentanteNazionale = rappresentanteNazionale;
	}
	
	

}
