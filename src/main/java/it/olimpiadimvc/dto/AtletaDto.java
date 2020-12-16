package it.olimpiadimvc.dto;

import java.util.List;

public class AtletaDto {
	
	private String id;
	private String nome;
	private String cognome;
	private String codiceFiscale;
	private String punti;
	private String medaglieBronzo;
	private String medaglieArgento;
	private String medaglieOro;
	private RappresentanteNazionaleDto rappresentanteNazionaleDto;
	private String stato;
	private List<DisciplinaDto> disciplineDto;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	public String getPunti() {
		return punti;
	}
	public void setPunti(String punti) {
		this.punti = punti;
	}
	public String getMedaglieBronzo() {
		return medaglieBronzo;
	}
	public void setMedaglieBronzo(String medaglieBronzo) {
		this.medaglieBronzo = medaglieBronzo;
	}
	public String getMedaglieArgento() {
		return medaglieArgento;
	}
	public void setMedaglieArgento(String medaglieArgento) {
		this.medaglieArgento = medaglieArgento;
	}
	public String getMedaglieOro() {
		return medaglieOro;
	}
	public void setMedaglieOro(String medaglieOro) {
		this.medaglieOro = medaglieOro;
	}
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	public RappresentanteNazionaleDto getRappresentanteNazionaleDto() {
		return rappresentanteNazionaleDto;
	}
	public void setRappresentanteNazionaleDto(RappresentanteNazionaleDto rappresentanteNazionaleDto) {
		this.rappresentanteNazionaleDto = rappresentanteNazionaleDto;
	}
	public List<DisciplinaDto> getDisciplineDto() {
		return disciplineDto;
	}
	public void setDisciplineDto(List<DisciplinaDto> disciplineDto) {
		this.disciplineDto = disciplineDto;
	}
	
}
