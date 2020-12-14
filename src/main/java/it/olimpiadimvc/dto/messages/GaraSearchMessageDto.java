package it.olimpiadimvc.dto.messages;

public class GaraSearchMessageDto {
	
	private String id;
	private String data;
	private String punteggio;
	private String numeroPartecipanti;
	private String stato;
	private String disciplinaDto;
	private String organizzatoreDto;
	
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
	public String getDisciplinaDto() {
		return disciplinaDto;
	}
	public void setDisciplinaDto(String disciplinaDto) {
		this.disciplinaDto = disciplinaDto;
	}
	public String getOrganizzatoreDto() {
		return organizzatoreDto;
	}
	public void setOrganizzatoreDto(String organizzatoreDto) {
		this.organizzatoreDto = organizzatoreDto;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNumeroPartecipanti() {
		return numeroPartecipanti;
	}
	public void setNumeroPartecipanti(String numeroPartecipanti) {
		this.numeroPartecipanti = numeroPartecipanti;
	}
	
}
