package it.uniroma3.siw.service;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.controller.GlobalController;

import it.uniroma3.siw.model.Autore;
import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.AutoreRepository;



@Service
public class AutoreService {
	
	@Autowired
	private AutoreRepository autoreRepository;
	@Autowired
	private CredentialsService credentialsService;
	@Autowired
	GlobalController globalController;
	
	public Iterable<Autore>findAll(){
		return autoreRepository.findAll();
	}
	@Transactional
	public Autore findById(Long id) {
		return autoreRepository.findById(id).get();
	}
	
	public boolean existsByNomeAndCognome(String nome, String cognome) {
		return autoreRepository.existsByNomeAndCognome(nome, cognome);
	}
	
	@Transactional
	public Autore save(Autore autore) {
		return autoreRepository.save(autore);
		
	}
	
	@Transactional
	public void save2(Autore autore,MultipartFile file) throws IOException {
		autore.setImmagine(Base64.getEncoder().encodeToString(file.getBytes()));
		autoreRepository.save(autore);		
	}
	
	@Transactional
	public void modifica(@RequestParam("id") Long id,
			@RequestParam("nuovoNome") String nuovoNome, @RequestParam("nuovoCognome") String nuovoCognome,
			@RequestParam("nuovaNazionalita") String nuovaNazionalita,
			@RequestParam("nuovaDataDiNascita")  Integer nuovaDataNascita, @RequestParam("nuovaDataDiMorte") Integer nuovaDataMorte
			) {
		Autore a = this.findById(id);
		a.setNome(nuovoNome);
		a.setCognome(nuovoCognome);
		a.setNazionalita(nuovaNazionalita);
		a.setDataNascita(nuovaDataNascita);
		a.setDataMorte(nuovaDataMorte);
	
		this.save(a);	
	}
	
//	@Transactional
//    public Artista findByUser(User user) {
//        return artistaRepository.findByUser(user);
//    }
	
	 @Transactional
		public void creaAutore(@ModelAttribute("autore") Autore autore, BindingResult bindingResult,
				@RequestParam("imageFile") MultipartFile imageFile) throws IOException {
		 this.save(autore);
			Credentials credenziali = credentialsService.getCredentials(globalController.getUser());
			User user = credenziali.getUser();
//			autore.setUser(user);
			this.save2(autore, imageFile);	
		}


}
