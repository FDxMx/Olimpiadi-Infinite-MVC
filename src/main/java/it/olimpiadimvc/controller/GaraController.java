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

import it.olimpiadimvc.dto.GaraDto;
import it.olimpiadimvc.dto.messages.GaraInsertMessageDto;
import it.olimpiadimvc.dto.messages.GaraSearchMessageDto;
import it.olimpiadimvc.model.StatoGara;
import it.olimpiadimvc.service.DisciplinaService;
import it.olimpiadimvc.service.GaraService;
import it.olimpiadimvc.service.UtenteService;
import it.olimpiadimvc.validators.GaraInsertValidator;

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
	public String insert(@Valid @ModelAttribute("garaInsertModel") GaraInsertMessageDto garaInsertMessageDto, BindingResult bindingResult, Model model) {
		
		garaInsertValidator.validate(garaInsertMessageDto, bindingResult);
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("listaDiscipline", disciplinaService.findAll());
            return "/gara/insert";
        } else {
            garaService.insert(garaInsertMessageDto);
            return "redirect:/gara/list";
        }
	}

}
