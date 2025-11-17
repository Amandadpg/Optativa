package com.daw.persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daw.persistence.entities.Pizza;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Integer>{

	List<Pizza> findByDisponibleTrueOrderByPrecioAsc();
	
	List<Pizza> findByNombreContainingIgnoreCaseAndDisponibleTrue(String nombre);

	List<Pizza> findByDescripcionContainingIgnoreCase(String ingrediente);

	List<Pizza> findByDescripcionNotContainingIgnoreCase(String ingrediente);
	
}
