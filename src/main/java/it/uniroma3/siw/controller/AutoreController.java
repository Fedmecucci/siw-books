package it.uniroma3.siw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.model.Autore;
import it.uniroma3.siw.service.AutoreService;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.validator.AutoreValidator;


@Controller
public class AutoreController {
	
	@Autowired
	private AutoreService autoreService;
	@Autowired
	private CredentialsService credentialsService;
	@Autowired
	private AutoreValidator autoreValidator;
	@Autowired
	private GlobalController globalController;
	
	@GetMapping("/paginaAutori")
    public String paginaAutori(Model model) {
        model.addAttribute("autori", this.autoreService.findAll());
        return "paginaAutori.html"; 
    }

}
