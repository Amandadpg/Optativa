package com.daw.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.daw.persistence.entities.Pizza;
import com.daw.services.PizzaService;
import com.daw.services.exception.ClienteNotFoundException;
import com.daw.services.exception.PizzaException;
import com.daw.services.exception.PizzaNotFoundException;

@RestController 
@RequestMapping("/pizza")
public class PizzaController {

	@Autowired 
	private PizzaService pizzaService;
	
	@GetMapping
	public ResponseEntity<List<Pizza>> list() {
		return ResponseEntity.ok(this.pizzaService.findAll());
	}
	
	@GetMapping("/{idPizza}")
	public ResponseEntity<?> findById(@PathVariable int idPizza) { 
		try {
			return ResponseEntity.ok(this.pizzaService.findById(idPizza));
		}
		catch (PizzaNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	//http://localhost:8081/pokemon
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Pizza pizza) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(this.pizzaService.create(pizza));
		}
		catch(PizzaException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
			
		}
	}
	
	//http://localhost:8081/pokemon/5
	@PutMapping("/{idPizza}")
	public ResponseEntity<?> update(@PathVariable int idPizza, @RequestBody Pizza pizza){
		try {
			return ResponseEntity.ok(this.pizzaService.update(pizza, idPizza));
		}
		catch(PizzaNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
		catch (PizzaException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
		
	}
	
	//http://localhost:8081/pokemon/6
	@DeleteMapping("/{idPizza}")
	public ResponseEntity<?> delete(@PathVariable int idPizza) {

		try {
			this.pizzaService.delete(idPizza);
			return ResponseEntity.ok().build();
		} catch (ClienteNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
	}
	
	
	@GetMapping("/disponibles")
	public ResponseEntity<List<Pizza>> getDisponiblesOrdenadasPorPrecio() {
	    return ResponseEntity.ok(this.pizzaService.findDisponiblesOrderByPrecio());
	}

	@GetMapping("/buscarNombre")
	public ResponseEntity<List<Pizza>> buscarPorNombre(@RequestParam String nombre) {
	    List<Pizza> pizzas = this.pizzaService.findByNombreDisponible(nombre);
	    return ResponseEntity.ok(pizzas);
	}
	
	@GetMapping("/con-ingrediente")
	public ResponseEntity<List<Pizza>> buscarPorIngrediente(@RequestParam String ingrediente) {
	    List<Pizza> pizzas = this.pizzaService.findByIngrediente(ingrediente);
	    return ResponseEntity.ok(pizzas);
	}
	
	@GetMapping("/sin-ingrediente")
	public ResponseEntity<List<Pizza>> buscarSinIngrediente(@RequestParam String ingrediente) {
	    List<Pizza> pizzas = this.pizzaService.findByIngredienteExcluido(ingrediente);
	    return ResponseEntity.ok(pizzas);
	}
	
	@PutMapping("/{idPizza}/precio")
	public ResponseEntity<?> actualizarPrecio(@PathVariable int idPizza, @RequestParam double precio) {
	    try {
	        Pizza pizzaActualizada = pizzaService.actualizarPrecio(idPizza, precio);
	        return ResponseEntity.ok(pizzaActualizada);
	    } catch (PizzaNotFoundException ex) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	    } catch (PizzaException ex) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	    }
	}
	
	
	@PutMapping("/{idPizza}/disponibilidad")
	public ResponseEntity<?> actualizarDisponibilidad(@PathVariable int idPizza,
	                                                 @RequestParam boolean disponible) {
	    try {
	        Pizza pizzaActualizada = pizzaService.actualizarDisponibilidad(idPizza, disponible);
	        return ResponseEntity.ok(pizzaActualizada);
	    } catch (PizzaNotFoundException ex) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	    }
	}

	
}
