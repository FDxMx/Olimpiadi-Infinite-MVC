package it.olimpiadimvc.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Disciplina {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "disciplina", orphanRemoval = true)
	private List<Gara> gare;
	@ManyToMany
	@JoinTable(name = "atleta_disciplina", joinColumns = @JoinColumn(name = "disciplina_fk", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "atleta_fk", referencedColumnName = "id"))
	private List<Atleta> atleti;
	
	public Disciplina () {}

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

	
	public List<Gara> getGare() {
		return gare;
	}

	public void setGare(List<Gara> gare) {
		this.gare = gare;
	}

	public List<Atleta> getAtleti() {
		return atleti;
	}

	public void setAtleti(List<Atleta> atleti) {
		this.atleti = atleti;
	}
	
	

}
