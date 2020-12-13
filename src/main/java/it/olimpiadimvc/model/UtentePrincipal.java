package it.olimpiadimvc.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UtentePrincipal implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4068114901288421080L;
	private final Utente utente;
	
	public UtentePrincipal(Utente utente) {
        this.utente = utente;
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		return utente.getPassword();
	}

	@Override
	public String getUsername() {
		return utente.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
