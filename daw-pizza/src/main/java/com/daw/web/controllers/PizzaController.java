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
import org.springframework.web.bind.annotation.RestController;

import com.daw.persistence.entities.Pizza;
import com.daw.services.PizzaService;
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
	
	
}
