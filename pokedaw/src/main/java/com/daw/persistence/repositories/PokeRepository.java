package com.daw.persistence.repositories;

import org.springframework.data.repository.ListCrudRepository;

import com.daw.persistence.entities.Pokemon;

public interface PokeRepository extends ListCrudRepository<Pokemon, Integer>{

}
