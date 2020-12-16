package it.olimpiadimvc.dto.messages;

public class AtletaSearchMessageDto {
	
	private String nome;
	private String cognome;
	private String codiceFiscale;
	private String stato;
	private String rappresentanteNazionaleDto;
	
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
	public String getRappresentanteNazionaleDto() {
		return rappresentanteNazionaleDto;
	}
	public void setRappresentanteNazionaleDto(String rappresentanteNazionaleDto) {
		this.rappresentanteNazionaleDto = rappresentanteNazionaleDto;
	}
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	
}
