package com.daw.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daw.persistence.entities.Pizza;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Integer>{

	
}
