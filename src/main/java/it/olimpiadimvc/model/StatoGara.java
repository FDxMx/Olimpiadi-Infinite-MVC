package it.olimpiadimvc.model;

import java.util.EnumSet;
import java.util.Set;

public enum StatoGara {
	
	CREATA, IN_CALENDARIO, TERMINATA;
	
	public static Set<StatoGara> listaEnum(){
		Set<StatoGara> stati = EnumSet.of(CREATA, IN_CALENDARIO, TERMINATA);
		return stati;
	}

}
