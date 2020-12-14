package it.olimpiadimvc.dto;

public class NazioneDto {
	
	private String id;
	private String nome;
	private RappresentanteNazionaleDto rappresentanteNazionaleDto;
	
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
	public RappresentanteNazionaleDto getRappresentanteNazionaleDto() {
		return rappresentanteNazionaleDto;
	}
	public void setRappresentanteNazionaleDto(RappresentanteNazionaleDto rappresentanteNazionaleDto) {
		this.rappresentanteNazionaleDto = rappresentanteNazionaleDto;
	}
	
}
