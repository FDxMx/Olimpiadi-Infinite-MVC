package it.olimpiadimvc.dto.messages;

import javax.validation.constraints.NotEmpty;

public class GaraUpdateMessageDto {
	
	private String id;
	private String data;
	@NotEmpty(message = "PUNTEGGIO obbligatorio")
	private String punteggio;
	@NotEmpty(message = "NUMERO PARTECIPANTI obbligatorio")
	private String numeroPartecipanti;
	
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
	public String getNumeroPartecipanti() {
		return numeroPartecipanti;
	}
	public void setNumeroPartecipanti(String numeroPartecipanti) {
		this.numeroPartecipanti = numeroPartecipanti;
	}
	
}
