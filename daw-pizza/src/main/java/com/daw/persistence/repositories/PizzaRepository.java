package com.daw.persistence.repositories;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.daw.persistence.entities.Pizza;

@Repository
public interface PizzaRepository extends ListCrudRepository<Pizza, Integer>{

	
}
