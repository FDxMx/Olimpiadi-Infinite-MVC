package it.olimpiadimvc.dto.messages;

import javax.validation.constraints.NotEmpty;

public class RappresentanteNazionaleUpdateMessageDto {
	
	private String id;
	@NotEmpty(message = "NOME obbligatorio")
	private String nome;
	@NotEmpty(message = "COGNOME obbligatorio")
	private String cognome;
	@NotEmpty(message = "CODICE FISCALE obbligatorio")
	private String codiceFiscale;
	
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
