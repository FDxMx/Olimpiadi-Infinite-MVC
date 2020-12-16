package it.olimpiadimvc.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.olimpiadimvc.dto.AtletaDto;
import it.olimpiadimvc.dto.GaraDto;
import it.olimpiadimvc.dto.UtenteDto;
import it.olimpiadimvc.dto.messages.AtletaInsertMessageDto;
import it.olimpiadimvc.dto.messages.AtletaSearchMessageDto;
import it.olimpiadimvc.dto.messages.AtletaUpdateMessageDto;
import it.olimpiadimvc.model.StatoAtleta;
import it.olimpiadimvc.service.AtletaService;
import it.olimpiadimvc.service.DisciplinaService;
import it.olimpiadimvc.service.GaraService;
import it.olimpiadimvc.service.RappresentanteNazionaleService;
import it.olimpiadimvc.service.UtenteService;
import it.olimpiadimvc.validators.AtletaInsertValidator;
import it.olimpiadimvc.validators.AtletaUpdateValidator;

@Controller
@RequestMapping("atleta")
public class AtletaController {
	
	@Autowired
	private AtletaService atletaService;
	
	@Autowired
	private RappresentanteNazionaleService rappresentanteNazionaleService;
	
	@Autowired
	private AtletaInsertValidator atletaInsertValidator;
	
	@Autowired
	private UtenteService utenteService;
	
	@Autowired
	private GaraService garaService;
	
	@Autowired
	private DisciplinaService disciplinaService;
	
	@Autowired
	private AtletaUpdateValidator atletaUpdateValidator;
	
	@GetMapping("list")
    public String list(AtletaSearchMessageDto atletaSearchMessageDto, Model model) {
		UtenteDto utenteSession = utenteService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		atletaSearchMessageDto.setRappresentanteNazionaleDto(rappresentanteNazionaleService.findRappresentanteByUtente(Integer.parseInt(utenteSession.getId())).getId());
		List<AtletaDto> atleti = atletaService.findByExample(atletaSearchMessageDto);
		model.addAttribute("atletaSearchModel", atletaSearchMessageDto);
		model.addAttribute("listaAtleti", atleti);
        model.addAttribute("listaStati", StatoAtleta.listaEnum());
        model.addAttribute("listaRappresentanti", rappresentanteNazionaleService.findAll());
        return "/atleta/lista";
	}
	
	@GetMapping("insert")
	public String insert(Model model) {
		model.addAttribute("atletaInsertModel", new AtletaInsertMessageDto());
		model.addAttribute("listaDiscipline", disciplinaService.findAll());
		return "/atleta/insert";
	}
	
	@PostMapping("insert")
	public String insert(@Valid @ModelAttribute("atletaInsertModel") AtletaInsertMessageDto atletaInsertMessageDto, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
		
		atletaInsertValidator.validate(atletaInsertMessageDto, bindingResult);
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("listaDiscipline", disciplinaService.findAll());
            return "/atleta/insert";
        } else {
            atletaService.insert(atletaInsertMessageDto);
            redirectAttributes.addFlashAttribute("effettuato", "Inserimento effettuato con successo!");
            return "redirect:/atleta/list";
        }
	}
	
	@GetMapping("show/{id}")
	public String show(@PathVariable Integer id, Model model) {
		model.addAttribute("atletaShowModel", atletaService.findById(id));
		model.addAttribute("listaGare", garaService.findGareByAtleta(id));
    	return "/atleta/show";
	}
	
	@GetMapping("update/{id}")
	public String update(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
		if(garaService.findGareByAtleta(id).size() > 0) {
			redirectAttributes.addFlashAttribute("errore", "Non puoi aggiornare un atleta se è iscritto a delle gare!");
			return "redirect:/atleta/list";
		}else {
			model.addAttribute("atletaUpdateModel", atletaService.findById(id));
			model.addAttribute("listaDiscipline", disciplinaService.findAll());
		    return "/atleta/update";
		}
	}
	
	@PostMapping("update/{id}")
	public String update(@Valid @ModelAttribute("atletaUpdateModel") AtletaUpdateMessageDto atletaUpdateMessageDto, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
		
		atletaUpdateValidator.validate(atletaUpdateMessageDto, bindingResult);
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("listaDiscipline", disciplinaService.findAll());
            return "/atleta/update";
        } else {
            atletaService.update(atletaUpdateMessageDto);
            redirectAttributes.addFlashAttribute("effettuato", "Aggiornamento effettuato con successo!");
            return "redirect:/atleta/list";
        }
	}
	
	@GetMapping("ritira/{id}")
	public String delete(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
		List<GaraDto> gare =  garaService.findGareByAtleta(id);
		if(gare.size() < 1) {
			model.addAttribute("atletaRitiraId", id);
	    	return "/atleta/ritira";
		}else {
			redirectAttributes.addFlashAttribute("errore", "Non puoi ritirare un atleta se è iscritto a delle gare!");
			return "redirect:/atleta/list";
		}
	}
	
	@GetMapping("confermaRitira/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
    	atletaService.ritira(id);
    	redirectAttributes.addFlashAttribute("effettuato", "Ritirazione effettuata con successo!");
    	return "redirect:/atleta/list";
    }

}
