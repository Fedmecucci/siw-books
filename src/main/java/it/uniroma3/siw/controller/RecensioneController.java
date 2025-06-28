package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.RecensioneService;

@Controller
public class RecensioneController {

	@Autowired
	private RecensioneService recensioneService;
	@Autowired
	private CredentialsService credentialsService;
	@Autowired
	private GlobalController globalController;
	
	 @GetMapping("/paginaRecensioni")
	    public String paginaRecensioni(Model model) {
		  model.addAttribute("recensioni", this.recensioneService.findAll());
		  
	       return "paginaRecensioni.html";
	    }
	
}
