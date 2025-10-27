package com.daw.web.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daw.persistence.entities.Pokemon;
import com.daw.persistence.entities.Tipo;
import com.daw.services.PokeService;
import com.daw.services.exception.PokemonException;
import com.daw.services.exception.PokemonNotFoundException;

@RestController 
@RequestMapping("/pokemon")
public class PokeController {

	@Autowired 
	private PokeService pokeService;
	
	
	//http://localhost:8081/pokemon
	@GetMapping
	public ResponseEntity<List<Pokemon>> list() {
		return ResponseEntity.ok(this.pokeService.findAll());
	}
	
	//http://localhost:8081/pokemon/5
	@GetMapping("/{idPokemon}")
	public ResponseEntity<?> findById(@PathVariable int idPokemon) { 
		try {
			return ResponseEntity.ok(this.pokeService.findById(idPokemon));
		}
		catch (PokemonNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	//http://localhost:8081/pokemon
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Pokemon pokemon) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(this.pokeService.create(pokemon));
		}
		catch(PokemonException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
			
		}
	}
	
	//http://localhost:8081/pokemon/5
	@PutMapping("/{idPokemon}")
	public ResponseEntity<?> update(@PathVariable int idPokemon, @RequestBody Pokemon pokemon){
		try {
			return ResponseEntity.ok(this.pokeService.update(pokemon, idPokemon));
		}
		catch(PokemonNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
		catch (PokemonException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
		
	}
	
	//http://localhost:8081/pokemon/6
	@DeleteMapping("/{idPokemon}")
	public ResponseEntity<?> delete(@PathVariable int idPokemon) {

		try {
			this.pokeService.delete(idPokemon);
			return ResponseEntity.ok().build();
		} catch (PokemonNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
	}
	
	
	//http://localhost:8081/pokemon/numero/9
	@GetMapping("/numero/{numeroPokedex}")
	public ResponseEntity<?> findByNumero(@PathVariable int numeroPokedex) {
	    try {
	        return ResponseEntity.ok(this.pokeService.buscarPorNumeroPokedex(numeroPokedex));
	    } catch (PokemonNotFoundException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    }
	}
	
	//
	@GetMapping("/capturados/{inicio}/{fin}")
	public ResponseEntity<List<Pokemon>> buscarPorFechas(@PathVariable String inicio, @PathVariable String fin) {

	    LocalDate fechaInicio = LocalDate.parse(inicio);
	    LocalDate fechaFin = LocalDate.parse(fin);

	    List<Pokemon> lista = pokeService.buscarPorRangoFechas(fechaInicio, fechaFin);
	    return ResponseEntity.ok(lista);
	}

	
	//http://localhost:8081/pokemon/tipo/FUEGO
	@GetMapping("/tipo/{tipo}")
	public ResponseEntity<List<Pokemon>> findByTipo(@PathVariable String tipo) {
	    return ResponseEntity.ok(this.pokeService.BuscarPorTipo(Tipo.valueOf(tipo.toUpperCase())));
	}
	
	//http://localhost:8081/pokemon/tipo/8
	@PutMapping("/tipo/{idPokemon}")
	public ResponseEntity<?> cambiarTipo(@PathVariable int idPokemon,@RequestBody Pokemon pokemon) {

	    try {
	        Pokemon actualizado = pokeService.cambiarTipo(idPokemon, pokemon.getTipo1(), pokemon.getTipo2());
	        return ResponseEntity.ok(actualizado);
	    } catch (PokemonNotFoundException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    } catch (PokemonException e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	    }
	}
	
	
	
}
