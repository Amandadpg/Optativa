package com.daw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.persistence.entities.Pokemon;
import com.daw.persistence.repositories.PokeRepository;

@Service
public class PokeService {

	@Autowired
	private PokeRepository pokeRepository;
	
	//findAll() 
		public List<Pokemon> findAll() {
			return this.pokeRepository.findAll();
			
		}
}
