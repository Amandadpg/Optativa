package com.daw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.persistence.entities.Pizza;
import com.daw.persistence.repositories.PizzaRepository;
import com.daw.services.exception.PizzaNotFoundException;

@Service
public class PizzaService {
	
	@Autowired
	private PizzaRepository pizzaRepository;
	
	public List<Pizza> findAll() {
        return pizzaRepository.findAll(); 
    }
	
	public Pizza findById(int idPizza) { 
		if(!this.pizzaRepository.existsById(idPizza)) { 
			throw new PizzaNotFoundException("La pizza con id " + idPizza +" no existe");
		}
		return this.pizzaRepository.findById(idPizza).get();
	}
	
	public Pizza create(Pizza pizza) {
		
		pizza.setId(0);
		
		return this.pizzaRepository.save(pizza);
	}
	
	public Pizza update(Pizza pizza, int idPizza) {
		
		Pizza pizzaBD = this.findById(idPizza);
		pizzaBD.setDescripcion(pizza.getDescripcion());
		pizzaBD.setDisponible(pizza.isDisponible());
		pizzaBD.setNombre(pizza.getNombre());
		pizzaBD.setPrecio(pizza.getPrecio());
		pizzaBD.setVegana(pizza.isVegana());
		pizzaBD.setVegetariana(pizza.isVegetariana());
		
		return this.pizzaRepository.save(pizzaBD);
		
	}
	
	public void delete(int idPizza) {
		if(!this.pizzaRepository.existsById(idPizza)) {
			throw new PizzaNotFoundException("La pizza no existe.");
		}
		this.pizzaRepository.deleteById(idPizza);
	}
}
