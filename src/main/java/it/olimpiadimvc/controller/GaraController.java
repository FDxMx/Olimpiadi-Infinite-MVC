package it.olimpiadimvc.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.olimpiadimvc.dto.GaraDto;
import it.olimpiadimvc.dto.messages.GaraInsertMessageDto;
import it.olimpiadimvc.dto.messages.GaraSearchMessageDto;
import it.olimpiadimvc.dto.messages.GaraUpdateMessageDto;
import it.olimpiadimvc.model.StatoGara;
import it.olimpiadimvc.service.AtletaService;
import it.olimpiadimvc.service.DisciplinaService;
import it.olimpiadimvc.service.GaraService;
import it.olimpiadimvc.service.UtenteService;
import it.olimpiadimvc.validators.GaraInsertValidator;
import it.olimpiadimvc.validators.GaraUpdateValidator;

@Controller
@RequestMapping("gara")
public class GaraController {
	
	@Autowired
	private GaraService garaService;
	
	@Autowired
	private DisciplinaService disciplinaService;
	
	@Autowired
	private UtenteService utenteService;
	
	@Autowired
	private GaraInsertValidator garaInsertValidator;
	
	@Autowired
	private GaraUpdateValidator garaUpdateValidator;
	
	@Autowired
	private AtletaService atletaService;
	
	@GetMapping("list")
    public String list(GaraSearchMessageDto garaSearchMessageDto, Model model) {
        List<GaraDto> gare = garaService.findByExample(garaSearchMessageDto);
    	model.addAttribute("garaSearchModel", garaSearchMessageDto);
        model.addAttribute("listaGare", gare);
        model.addAttribute("listaStati", StatoGara.listaEnum());
        model.addAttribute("listaDiscipline", disciplinaService.findAll());
        model.addAttribute("listaOrganizzatori", utenteService.findAllOrganizzatori());
        return "/gara/lista";
	}
	
	@GetMapping("insert")
	public String insert(Model model) {
		model.addAttribute("garaInsertModel", new GaraInsertMessageDto());
		model.addAttribute("listaDiscipline", disciplinaService.findAll());
		return "/gara/insert";
	}
	
	@PostMapping("insert")
	public String insert(@Valid @ModelAttribute("garaInsertModel") GaraInsertMessageDto garaInsertMessageDto, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
		
		garaInsertValidator.validate(garaInsertMessageDto, bindingResult);
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("listaDiscipline", disciplinaService.findAll());
            return "/gara/insert";
        } else {
            garaService.insert(garaInsertMessageDto);
            redirectAttributes.addFlashAttribute("effettuato", "Inserimento effettuato con successo!");
            return "redirect:/gara/list";
        }
	}
	
	@GetMapping("show/{id}")
	public String show(@PathVariable Integer id, Model model) {
		model.addAttribute("garaShowModel", garaService.findById(id));
		model.addAttribute("listaPartecipanti", atletaService.findAtletiByGara(id));
    	return "/gara/show";
	}
	
	@GetMapping("update/{id}")
	public String update(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
		GaraDto garaDto =  garaService.findById(id);
		if(!garaDto.getStato().equals("TERMINATA")) {
			model.addAttribute("garaUpdateModel", garaDto);
	    	return "/gara/update";
		} else {
			redirectAttributes.addFlashAttribute("errore", "La gara è terminata!");
			return "redirect:/gara/list";
		}
		
	}
	
	@PostMapping("update/{id}")
	public String update(@Valid @ModelAttribute("garaUpdateModel") GaraUpdateMessageDto garaUpdateMessageDto, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
		
		garaUpdateValidator.validate(garaUpdateMessageDto, bindingResult);
		
		if (bindingResult.hasErrors()) {
            return "/gara/update";
        } else {
            garaService.update(garaUpdateMessageDto);
            redirectAttributes.addFlashAttribute("effettuato", "Aggiornamento effettuato con successo!");
            return "redirect:/gara/list";
        }
	}
	
	@GetMapping("delete/{id}")
	public String delete(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
		GaraDto garaDto =  garaService.findById(id);
		if(!garaDto.getStato().equals("TERMINATA")) {
			model.addAttribute("garaDeleteModelId", id);
	    	return "/gara/delete";
		}else {
			redirectAttributes.addFlashAttribute("errore", "La gara è terminata!");
			return "redirect:/gara/list";
		}
	}
	
	@GetMapping("confermaDelete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
    	garaService.delete(id);
    	redirectAttributes.addFlashAttribute("effettuato", "Eliminazione effettuata con successo!");
    	return "redirect:/gara/list";
    }

}
