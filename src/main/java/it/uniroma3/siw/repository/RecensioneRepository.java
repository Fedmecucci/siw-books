package it.uniroma3.siw.repository;

import java.util.List;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.model.Libro;

import it.uniroma3.siw.model.Recensione;
import it.uniroma3.siw.model.User;


@Repository
public interface RecensioneRepository extends CrudRepository<Recensione,Long>{

	
	// Trova tutte le recensioni per un determinato libro
    public List<Recensione> findByLibro(Libro libro);
    
    public boolean existsByTesto(String testo);
    
    public Recensione findByUser(User utente);
}
