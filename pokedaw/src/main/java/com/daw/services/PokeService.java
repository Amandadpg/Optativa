package com.daw.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.persistence.entities.Pokeball;
import com.daw.persistence.entities.Pokemon;
import com.daw.persistence.repositories.PokeRepository;
import com.daw.services.exception.PokemonException;
import com.daw.services.exception.PokemonNotFoundException;

@Service
public class PokeService {

	@Autowired
	private PokeRepository pokeRepository;
	
	//findAll() 
		public List<Pokemon> findAll() {
			return this.pokeRepository.findAll();
			
		}
		
		public Pokemon findById(int idPokemon) { 
			if(!this.pokeRepository.existsById(idPokemon)) { //11
				throw new PokemonNotFoundException("El pokemon con id " + idPokemon +" no existe");
			}
			return this.pokeRepository.findById(idPokemon).get();
		}
		
		public Pokemon create(Pokemon pokemon) {
			if(pokemon.getTipo1().equals(pokemon.getTipo2())) {
				throw new PokemonException("El pokemon no puede tener dos tipos");
				//Me falta que tipo2, si son iguales sea ninguno
			}
			
			pokemon.setId(0);
			pokemon.setCapturado(Pokeball.POKEBALL);
			pokemon.setFechaCaptura(LocalDate.now());
			
			return this.pokeRepository.save(pokemon);
		}
		
		public Pokemon update(Pokemon pokemon, int idPokemon) {
			if (pokemon.getId() != idPokemon) {
				throw new PokemonException("Los ids no coinciden");
			}
			if(!this.pokeRepository.existsById(idPokemon)) { 
				throw new PokemonNotFoundException("La tarea con id " + idPokemon +" no existe");
			}
			if (pokemon.getCapturado() != null) {
				throw new PokemonException("No se puede modificar si esta capturado o no.");
			}
			if (pokemon.getFechaCaptura() != null) {
				throw new PokemonException("No se puede modificar la fecha de captura.");
			}
			
			Pokemon pokemonBD = this.findById(idPokemon);
			pokemonBD.setNumeroPokedex(pokemon.getNumeroPokedex());
			pokemonBD.setTitulo(pokemon.getTitulo());
			
			
			return this.pokeRepository.save(pokemonBD);
			
		}
		
		public void delete(int idPokemon) {
			if(!this.pokeRepository.existsById(idPokemon)) {
				throw new PokemonNotFoundException("El pokemon no existe.");
			}
			this.pokeRepository.deleteById(idPokemon);
		}
		
		
		
		
//		public List<Pokemon> buscarPorTipo(String Tipo) {
//			try {
//				
//			}catch {
//				
//			}
//		}
}
