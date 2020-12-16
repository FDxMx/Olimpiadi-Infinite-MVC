package it.olimpiadimvc.model;

import java.util.EnumSet;
import java.util.Set;

public enum StatoAtleta {
	
	ATTIVO, RITIRATO;
	
	public static Set<StatoAtleta> listaEnum(){
		Set<StatoAtleta> stati = EnumSet.of(ATTIVO, RITIRATO);
		return stati;
	}

}
