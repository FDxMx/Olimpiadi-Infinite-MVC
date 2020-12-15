package it.olimpiadimvc.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Atleta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String cognome;
	private String codiceFiscale;
	private Integer punti;
	private Integer medaglieBronzo;
	private Integer medaglieArgento;
	private Integer medaglieOro;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rappresentanteNazionale_fk", nullable = true)
	private RappresentanteNazionale rappresentanteNazionale;
	@Enumerated(EnumType.STRING)
	private StatoAtleta stato;
	@ManyToMany
	@JoinTable(name = "atleta_gara", joinColumns = @JoinColumn(name = "atleta_fk", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "gara_fk", referencedColumnName = "id"))
	private List<Gara> gare;
	@ManyToMany
	@JoinTable(name = "atleta_disciplina", joinColumns = @JoinColumn(name = "atleta_fk", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "disciplina_fk", referencedColumnName = "id"))
	private List<Disciplina> discipline;
	
	public Atleta () {}

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

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public Integer getPunti() {
		return punti;
	}

	public void setPunti(Integer punti) {
		this.punti = punti;
	}

	public Integer getMedaglieBronzo() {
		return medaglieBronzo;
	}

	public void setMedaglieBronzo(Integer medaglieBronzo) {
		this.medaglieBronzo = medaglieBronzo;
	}

	public Integer getMedaglieArgento() {
		return medaglieArgento;
	}

	public void setMedaglieArgento(Integer medaglieArgento) {
		this.medaglieArgento = medaglieArgento;
	}

	public Integer getMedaglieOro() {
		return medaglieOro;
	}

	public void setMedaglieOro(Integer medaglieOro) {
		this.medaglieOro = medaglieOro;
	}

	public RappresentanteNazionale getRappresentanteNazionale() {
		return rappresentanteNazionale;
	}

	public void setRappresentanteNazionale(RappresentanteNazionale rappresentanteNazionale) {
		this.rappresentanteNazionale = rappresentanteNazionale;
	}

	public StatoAtleta getStato() {
		return stato;
	}

	public void setStato(StatoAtleta stato) {
		this.stato = stato;
	}

	public List<Gara> getGare() {
		return gare;
	}

	public void setGare(List<Gara> gare) {
		this.gare = gare;
	}

	public List<Disciplina> getDiscipline() {
		return discipline;
	}

	public void setDiscipline(List<Disciplina> discipline) {
		this.discipline = discipline;
	}
	
	

}
