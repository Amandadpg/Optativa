package com.daw.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daw.persistence.entities.Pokemon;
import com.daw.services.PokeService;
import com.daw.services.exception.PokemonException;
import com.daw.services.exception.PokemonNotFoundException;

@RestController 
@RequestMapping("/pokemon")
public class PokeController {

	@Autowired // 9
	private PokeService pokeService;
	
	@GetMapping
	public ResponseEntity<List<Pokemon>> list() {
		return ResponseEntity.ok(this.pokeService.findAll());
	}
	
	@GetMapping("/{idPokemon}")
	public ResponseEntity<?> findById(@PathVariable int idPokemon) { 
		try {
			return ResponseEntity.ok(this.pokeService.findById(idPokemon));
		}
		catch (PokemonNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Pokemon pokemon) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(this.pokeService.create(pokemon));
		}
		catch(PokemonException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
			
		}
	}
}
