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
import com.daw.persistence.entities.PizzaPedido;
import com.daw.services.PizzaPedidoService;
import com.daw.services.exception.ClienteNotFoundException;
import com.daw.services.exception.PizzaException;
import com.daw.services.exception.PizzaNotFoundException;
import com.daw.services.exception.PizzaPedidoException;
import com.daw.services.exception.PizzaPedidoNotFoundException;

@RestController 
@RequestMapping("/pizzaPedido")
public class PizzaPedidoController {
	
//	@GetMapping("/{idPizzaPedido}")
//	public ResponseEntity<?> findById(@PathVariable int idPizzaPedido) { 
//		try {
//			return ResponseEntity.ok(this.pizzaPedidoService.findById(idPizzaPedido));
//		}
//		catch (PizzaPedidoNotFoundException e) {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//		}
//	}
	
}
