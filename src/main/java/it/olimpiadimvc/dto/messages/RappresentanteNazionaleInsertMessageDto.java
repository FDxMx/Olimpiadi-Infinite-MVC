package it.olimpiadimvc.dto.messages;

import javax.validation.constraints.NotEmpty;

public class RappresentanteNazionaleInsertMessageDto {
	
	@NotEmpty(message = "NOME obbligatorio")
	private String nome;
	@NotEmpty(message = "COGNOME obbligatorio")
	private String cognome;
	@NotEmpty(message = "CODICE FISCALE obbligatorio")
	private String codiceFiscale;
	@NotEmpty(message = "NAZIONE obbligatoria")
	private String nazioneDto;
	@NotEmpty(message = "USERNAME obbligatorio")
	private String username;
	@NotEmpty(message = "PASSWORD obbligatoria")
	private String password;

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
	public String getNazioneDto() {
		return nazioneDto;
	}
	public void setNazioneDto(String nazioneDto) {
		this.nazioneDto = nazioneDto;
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
	
}
