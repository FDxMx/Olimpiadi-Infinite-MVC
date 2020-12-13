package it.olimpiadimvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import it.olimpiadimvc.dto.UtenteDto;
import it.olimpiadimvc.dto.messages.UtenteRegistrazioneMessageDto;
import it.olimpiadimvc.mapper.UtenteMapper;
import it.olimpiadimvc.model.Ruolo;
import it.olimpiadimvc.model.Utente;
import it.olimpiadimvc.model.UtentePrincipal;
import it.olimpiadimvc.repository.UtenteRepository;

@Service
public class UtenteService implements UserDetailsService{
	
	@Autowired
	private UtenteRepository utenteRepository;
	
	@Autowired
	private UtenteMapper utenteMapper;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	public void registra(UtenteRegistrazioneMessageDto utenteRegistrazioneMessageDto) {
		Utente utente = new Utente();
		utente.setNome(utenteRegistrazioneMessageDto.getNome());
		utente.setCognome(utenteRegistrazioneMessageDto.getCognome());
		utente.setCodiceFiscale(utenteRegistrazioneMessageDto.getCodiceFiscale());
		utente.setUsername(utenteRegistrazioneMessageDto.getUsername());
		utente.setPassword(passwordEncoder.encode(utenteRegistrazioneMessageDto.getPassword()));
		utente.setRuolo(Ruolo.valueOf(utenteRegistrazioneMessageDto.getRuolo()));
		utenteRepository.save(utente);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Utente utente = utenteRepository.findByUsername(username);
		
		if(utente == null) {
			throw new UsernameNotFoundException("Utente non trovato");
		}
		return new UtentePrincipal(utente);
	}
	
	public List<UtenteDto> findAllOrganizzatori(){
		List<Utente> organizzatori = utenteRepository.findAllOrganizzatori();
		return utenteMapper.convertEntityToDto(organizzatori);
	}

}
