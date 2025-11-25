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

import com.daw.persistence.entities.Cliente;
import com.daw.persistence.entities.Pedido;
import com.daw.services.PedidoService;
import com.daw.services.exception.ClienteException;
import com.daw.services.exception.ClienteNotFoundException;
import com.daw.services.exception.PedidoException;
import com.daw.services.exception.PedidoNotFoundException;

@RestController 
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired 
	private PedidoService pedidoService;
	
	@GetMapping
	public ResponseEntity<List<Pedido>> list() {
		return ResponseEntity.ok(this.pedidoService.findAll());
	}
	
	@GetMapping("/{idPedido}")
	public ResponseEntity<?> findById(@PathVariable int idPedido) { 
		try {
			return ResponseEntity.ok(this.pedidoService.findById(idPedido));
		}
		catch (PedidoNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	//http://localhost:8081/pokemon
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Pedido pedido) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(this.pedidoService.create(pedido));
		}
		catch(PedidoException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
			
		}
	}
	
	//http://localhost:8081/pokemon/5
	@PutMapping("/{idCliente}")
	public ResponseEntity<?> update(@PathVariable int idPedido, @RequestBody Pedido pedido){
		try {
			return ResponseEntity.ok(this.pedidoService.update(pedido, idPedido));
		}
		catch(PedidoNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
		catch (PedidoException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
		
	}
	
	//http://localhost:8081/pokemon/6
	@DeleteMapping("/{idPedido}")
	public ResponseEntity<?> delete(@PathVariable int idPedido) {

		try {
			this.pedidoService.delete(idPedido);
			return ResponseEntity.ok().build();
		} catch (ClienteNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
	}
	
	@PutMapping("/{idPedido}/notas")
    public ResponseEntity<?> añadirNotas(@PathVariable int idPedido,
                                         @RequestBody Pedido pedido) {
		try {
	        String nuevasNotas = pedido.getNotas(); 
	        Pedido actualizado = pedidoService.añadirNotas(idPedido, nuevasNotas);
	        return ResponseEntity.ok(actualizado);
	    } catch (PedidoNotFoundException ex) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	    }
    }
}
