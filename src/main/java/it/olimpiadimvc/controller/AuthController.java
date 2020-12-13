package it.olimpiadimvc.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.olimpiadimvc.dto.messages.UtenteLoginMessageDto;
import it.olimpiadimvc.dto.messages.UtenteRegistrazioneMessageDto;
import it.olimpiadimvc.model.Ruolo;
import it.olimpiadimvc.service.UtenteService;


@Controller
@RequestMapping("auth")
public class AuthController {
	
	@Autowired
	private UtenteService utenteService;
	
	@GetMapping("login")
    public String login(Model model) {
        model.addAttribute("utenteLoginModel", new UtenteLoginMessageDto());
        return "/auth/login";
    }
	
	@GetMapping("home")
    public String home(Model model) {
        return "home";
    }
	
	@GetMapping("registrazione")
    public String registrazione(Model model) {
        model.addAttribute("utenteRegistratoModel", new UtenteRegistrazioneMessageDto());
        model.addAttribute("listaRuoli", Ruolo.listaEnum());
        return "/auth/registrazione";
    }
	
	@PostMapping("registrazione")
    public String registrazione(@Valid @ModelAttribute("utenteRegistratoModel") UtenteRegistrazioneMessageDto utenteRegistrazioneMessageDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
            return "/auth/registrazione";
        } else {
            utenteService.registra(utenteRegistrazioneMessageDto);
            return "redirect:/auth/login";
        }
    }

}
