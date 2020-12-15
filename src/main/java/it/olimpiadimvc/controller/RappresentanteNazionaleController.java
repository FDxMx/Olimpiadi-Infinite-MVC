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

import it.olimpiadimvc.dto.AtletaDto;
import it.olimpiadimvc.dto.RappresentanteNazionaleDto;
import it.olimpiadimvc.dto.messages.RappresentanteNazionaleInsertMessageDto;
import it.olimpiadimvc.dto.messages.RappresentanteNazionaleSearchMessageDto;
import it.olimpiadimvc.dto.messages.RappresentanteNazionaleUpdateMessageDto;
import it.olimpiadimvc.service.AtletaService;
import it.olimpiadimvc.service.NazioneService;
import it.olimpiadimvc.service.RappresentanteNazionaleService;
import it.olimpiadimvc.validators.RappresentanteNazionaleInsertValidator;
import it.olimpiadimvc.validators.RappresentanteNazionaleUpdateValidator;

@Controller
@RequestMapping("rappresentante")
public class RappresentanteNazionaleController {
	
	@Autowired
	private RappresentanteNazionaleService rappresentanteNazionaleService;
	
	@Autowired
	private NazioneService nazioneService;
	
	@Autowired
	private RappresentanteNazionaleInsertValidator rappresentanteNazionaleInsertValidator;
	
	@Autowired
	private RappresentanteNazionaleUpdateValidator rappresentanteNazionaleUpdateValidator;
	
	@Autowired
	private AtletaService atletaService;
	
	@GetMapping("list")
    public String list(RappresentanteNazionaleSearchMessageDto rappresentanteNazionaleSearchMessageDto, Model model) {
		List<RappresentanteNazionaleDto> rappresentanti = rappresentanteNazionaleService.findByExample(rappresentanteNazionaleSearchMessageDto);
		model.addAttribute("rappresentanteSearchModel", rappresentanteNazionaleSearchMessageDto);
		model.addAttribute("listaRappresentanti", rappresentanti);
        model.addAttribute("listaNazioni", nazioneService.findAll());
        return "/rappresentante/lista";
	}
	
	@GetMapping("insert")
	public String insert(Model model) {
		model.addAttribute("rappresentanteInsertModel", new RappresentanteNazionaleInsertMessageDto());
		model.addAttribute("listaNazioni", nazioneService.findAll());
		return "/rappresentante/insert";
	}
	
	@PostMapping("insert")
	public String insert(@Valid @ModelAttribute("rappresentanteInsertModel") RappresentanteNazionaleInsertMessageDto rappresentanteNazionaleInsertMessageDto, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
		
		rappresentanteNazionaleInsertValidator.validate(rappresentanteNazionaleInsertMessageDto, bindingResult);
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("listaNazioni", nazioneService.findAll());
            return "/rappresentante/insert";
        } else {
            rappresentanteNazionaleService.insert(rappresentanteNazionaleInsertMessageDto);
            redirectAttributes.addFlashAttribute("effettuato", "Inserimento effettuato con successo!");
            return "redirect:/rappresentante/list";
        }
	}
	
	@GetMapping("show/{id}")
	public String show(@PathVariable Integer id, Model model) {
		model.addAttribute("rappresentanteShowModel", rappresentanteNazionaleService.findById(id));
		model.addAttribute("listaAtleti", atletaService.findAtletiByRappresentante(id));
    	return "/rappresentante/show";
	}
	
	@GetMapping("update/{id}")
	public String update(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
		model.addAttribute("rappresentanteUpdateModel", rappresentanteNazionaleService.findById(id));
	    return "/rappresentante/update";
	}
	
	@PostMapping("update/{id}")
	public String update(@Valid @ModelAttribute("rappresentanteUpdateModel") RappresentanteNazionaleUpdateMessageDto rappresentanteNazionaleUpdateMessageDto, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
		
		rappresentanteNazionaleUpdateValidator.validate(rappresentanteNazionaleUpdateMessageDto, bindingResult);
		
		if (bindingResult.hasErrors()) {
            return "/rappresentante/update";
        } else {
            rappresentanteNazionaleService.update(rappresentanteNazionaleUpdateMessageDto);
            redirectAttributes.addFlashAttribute("effettuato", "Aggiornamento effettuato con successo!");
            return "redirect:/rappresentante/list";
        }
	}
	
	@GetMapping("delete/{id}")
	public String delete(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
		List<AtletaDto> atleti =  atletaService.findAtletiByRappresentante(id);
		if(atleti.size() < 1) {
			model.addAttribute("rappresentanteDeleteId", id);
	    	return "/rappresentante/delete";
		}else {
			redirectAttributes.addFlashAttribute("errore", "Il rappresentante ha degli atleti iscritti!");
			return "redirect:/rappresentante/list";
		}
	}
	
	@GetMapping("confermaDelete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
    	rappresentanteNazionaleService.delete(id);
    	redirectAttributes.addFlashAttribute("effettuato", "Eliminazione effettuata con successo!");
    	return "redirect:/rappresentante/list";
    }

}
