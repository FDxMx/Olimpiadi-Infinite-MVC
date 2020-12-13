package it.olimpiadimvc.dto;

public class GaraDto {
	
	private String id;
	private String data;
	private String punteggio;
	private String stato;
	private DisciplinaDto disciplinaDto;
	private UtenteDto organizzatoreDto;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getPunteggio() {
		return punteggio;
	}
	public void setPunteggio(String punteggio) {
		this.punteggio = punteggio;
	}
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	public DisciplinaDto getDisciplinaDto() {
		return disciplinaDto;
	}
	public void setDisciplinaDto(DisciplinaDto disciplinaDto) {
		this.disciplinaDto = disciplinaDto;
	}
	public UtenteDto getOrganizzatoreDto() {
		return organizzatoreDto;
	}
	public void setOrganizzatoreDto(UtenteDto organizzatoreDto) {
		this.organizzatoreDto = organizzatoreDto;
	}
	
	
	
}
