package it.olimpiadimvc.model;

import java.util.EnumSet;
import java.util.Set;

public enum Ruolo {
	
	ORGANIZZATORE, ATLETA, RAPPRESENTANTE_NAZIONALE;
	
	public static Set<Ruolo> listaEnum(){
		Set<Ruolo> ruoli = EnumSet.of(ORGANIZZATORE, ATLETA, RAPPRESENTANTE_NAZIONALE);
		return ruoli;
	}

}
