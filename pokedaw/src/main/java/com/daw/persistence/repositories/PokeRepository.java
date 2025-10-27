package com.daw.persistence.repositories;

import java.util.List;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;

import com.daw.persistence.entities.Pokemon;
import com.daw.persistence.entities.Tipo;

public interface PokeRepository extends ListCrudRepository<Pokemon, Integer>{

	
	Optional<Pokemon> findByNumeroPokedex(int numeroPokedex);
    
	List<Pokemon> findByFechaCapturaBetween(LocalDate inicio, LocalDate fin);
    
    List<Pokemon> findByTipo1OrTipo2(Tipo tipo1, Tipo tipo2);
}
