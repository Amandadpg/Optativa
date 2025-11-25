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

import com.daw.persistence.entities.Cliente;
import com.daw.services.ClienteService;
import com.daw.services.exception.ClienteException;
import com.daw.services.exception.ClienteNotFoundException;

@RestController 
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired 
	private ClienteService clienteService;
	
	@GetMapping
	public ResponseEntity<List<Cliente>> list() {
		return ResponseEntity.ok(this.clienteService.findAll());
	}
	
	@GetMapping("/{idCliente}")
	public ResponseEntity<?> findById(@PathVariable int idCliente) { 
		try {
			return ResponseEntity.ok(this.clienteService.findById(idCliente));
		}
		catch (ClienteNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	//http://localhost:8081/pokemon
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Cliente cliente) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(this.clienteService.create(cliente));
		}
		catch(ClienteException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
			
		}
	}
	
	//http://localhost:8081/pokemon/5
	@PutMapping("/{idCliente}")
	public ResponseEntity<?> update(@PathVariable int idCliente, @RequestBody Cliente cliente){
		try {
			return ResponseEntity.ok(this.clienteService.update(cliente, idCliente));
		}
		catch(ClienteNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
		catch (ClienteException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
		
	}
	
	//http://localhost:8081/pokemon/6
	@DeleteMapping("/{idCliente}")
	public ResponseEntity<?> delete(@PathVariable int idCliente) {

		try {
			this.clienteService.delete(idCliente);
			return ResponseEntity.ok().build();
		} catch (ClienteNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
	}
	
	@PutMapping("/{idCliente}/direccion")
	public ResponseEntity<?> modificarDireccion(@PathVariable int idCliente,
	                                            @RequestBody Cliente cliente) {
	    try {
	        // Tomamos solo la dirección del JSON
	        String nuevaDireccion = cliente.getDireccion();

	        Cliente actualizado = clienteService.modificarDireccion(idCliente, nuevaDireccion);
	        return ResponseEntity.ok(actualizado);
	    } catch (ClienteNotFoundException ex) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	    }
	}
	
	@GetMapping("/telefono")
	public ResponseEntity<?> buscarPorTelefono(@RequestParam String telefono) {
	    try {
	        Cliente cliente = clienteService.buscarPorTelefono(telefono);
	        return ResponseEntity.ok(cliente);
	    } catch (ClienteNotFoundException ex) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	    }
	}

	


	

	
	

}
