package it.olimpiadimvc.dto.messages;

import javax.validation.constraints.NotEmpty;

public class UtenteRegistrazioneMessageDto {
	
	@NotEmpty(message = "Required field")
	private String nome;
	@NotEmpty(message = "Required field")
	private String cognome;
	@NotEmpty(message = "Required field")
	private String codiceFiscale;
	@NotEmpty(message = "Required field")
	private String username;
	@NotEmpty(message = "Required field")
	private String password;
	@NotEmpty(message = "Required field")
	private String ruolo;
	
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRuolo() {
		return ruolo;
	}
	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}
	
	

}
