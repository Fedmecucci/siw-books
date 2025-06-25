package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.LibroService;
import it.uniroma3.siw.validator.LibroValidator;

@Controller
public class LibroController {
	
	@Autowired
	private LibroService libroService;
	@Autowired
	private CredentialsService credentialsService;
	@Autowired
	private LibroValidator libroValidator;
	@Autowired
	private GlobalController globalController;
	
	 @GetMapping("/paginaLibri")
	    public String paginaLibri(Model model) {
		  model.addAttribute("libri", this.libroService.findAll());
	       return "paginaLibri.html";
	    }

	 @GetMapping("/libro/{id}")
		public String getLibro(@PathVariable("id") Long id, Model model) {
			model.addAttribute("libro", this.libroService.findById(id));
			return "libro.html";
		}

	 
}
