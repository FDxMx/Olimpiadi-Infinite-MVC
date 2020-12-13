package it.olimpiadimvc.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Gara {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private LocalDate data;
	private Integer punteggio;
	@Enumerated(EnumType.STRING)
	private StatoGara stato;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "disciplina_fk", nullable = true)
	private Disciplina disciplina;
	@ManyToMany(mappedBy = "gare")
	private List<Atleta> partecipanti;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organizzatore_fk", nullable = true)
	private Utente organizzatore;
	
	public Gara () {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Integer getPunteggio() {
		return punteggio;
	}

	public void setPunteggio(Integer punteggio) {
		this.punteggio = punteggio;
	}

	public StatoGara getStato() {
		return stato;
	}

	public void setStato(StatoGara stato) {
		this.stato = stato;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public List<Atleta> getPartecipanti() {
		return partecipanti;
	}

	public void setPartecipanti(List<Atleta> partecipanti) {
		this.partecipanti = partecipanti;
	}

	public Utente getOrganizzatore() {
		return organizzatore;
	}

	public void setOrganizzatore(Utente organizzatore) {
		this.organizzatore = organizzatore;
	}
	
	

}
