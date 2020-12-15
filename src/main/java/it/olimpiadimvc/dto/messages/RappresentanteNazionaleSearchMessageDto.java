package it.olimpiadimvc.dto.messages;

public class RappresentanteNazionaleSearchMessageDto {
	
	private String id;
	private String nome;
	private String cognome;
	private String codiceFiscale;
	private String nazioneDto;
	
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
