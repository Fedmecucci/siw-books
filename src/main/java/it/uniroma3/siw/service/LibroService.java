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
import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Libro;

import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.LibroRepository;

@Service
public class LibroService {

	@Autowired
	private LibroRepository libroRepository;
	
	@Autowired
	private CredentialsService credentialsService;
	@Autowired
	GlobalController globalController;
	
	public Iterable<Libro>findAll(){
		return libroRepository.findAll();
	}
	
	public boolean existsByTitoloAndAnno(String titolo,Integer anno) {
		return libroRepository.existsByTitoloAndAnno(titolo, anno);
	}
	
	@Transactional
	public Libro findById(Long id) {
		return libroRepository.findById(id).get();
	}
	
	@Transactional
	public Libro save(Libro libro) {
		return libroRepository.save(libro);
		
	}
	
	@Transactional
	public void save2(Libro libro,MultipartFile file) throws IOException {
		libro.setImmagine(Base64.getEncoder().encodeToString(file.getBytes()));
		libroRepository.save(libro);		
	}
	
	 @Transactional
		public void creaLibro(@ModelAttribute("libro") Libro libro, BindingResult bindingResult,
				@RequestParam("imageFile") MultipartFile imageFile) throws IOException {

			this.save2(libro, imageFile);	
		}

		@Transactional
		public void modifica(@RequestParam("id") Long id,
				@RequestParam("nuovoTitolo") String nuovoTitolo, @RequestParam("nuovoAnno") Integer nuovoAnno
				) {
			Libro l = this.findById(id);
			l.setTitolo(nuovoTitolo);
			l.setAnno(nuovoAnno);
			
			this.save(l);	
		}

		@Transactional
		public void delete(Long id) {
			Libro libro = this.findById(id);
			libroRepository.delete(libro);
			
		}
}
