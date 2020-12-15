package it.olimpiadimvc.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.olimpiadimvc.dto.RappresentanteNazionaleDto;
import it.olimpiadimvc.dto.messages.RappresentanteNazionaleInsertMessageDto;
import it.olimpiadimvc.dto.messages.RappresentanteNazionaleSearchMessageDto;
import it.olimpiadimvc.service.NazioneService;
import it.olimpiadimvc.service.RappresentanteNazionaleService;
import it.olimpiadimvc.validators.RappresentanteNazionaleInsertValidator;

@Controller
@RequestMapping("rappresentante")
public class RappresentanteNazionaleController {
	
	@Autowired
	private RappresentanteNazionaleService rappresentanteNazionaleService;
	
	@Autowired
	private NazioneService nazioneService;
	
	@Autowired
	private RappresentanteNazionaleInsertValidator rappresentanteNazionaleInsertValidator;
	
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

}
