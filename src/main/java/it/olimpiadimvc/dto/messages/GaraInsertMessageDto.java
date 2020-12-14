package it.olimpiadimvc.dto.messages;

import javax.validation.constraints.NotEmpty;

public class GaraInsertMessageDto {
	
	private String data;
	@NotEmpty(message = "PUNTEGGIO obbligatorio")
	private String punteggio;
	@NotEmpty(message = "NUMERO PARTECIPANTI obbligatorio")
	private String numeroPartecipanti;
	@NotEmpty(message = "DISCIPLINA obbligatoria")
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
	public String getNumeroPartecipanti() {
		return numeroPartecipanti;
	}
	public void setNumeroPartecipanti(String numeroPartecipanti) {
		this.numeroPartecipanti = numeroPartecipanti;
	}
	
}
