package com.daw.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daw.persistence.entities.Tarea;
import com.daw.services.TareaService;
import com.daw.services.exception.TareaException;
import com.daw.services.exception.TareaNotFoundException;

@RestController // 7
@RequestMapping("/tareas") // 8
public class TareaController {
	
	@Autowired // 9
	private TareaService tareaService;
	
	// con poner el GET ya vale, no hace falta poner el nombre
	@GetMapping
	public List<Tarea> list() {
		return this.tareaService.findAll();
	}
	
	@GetMapping("/{idTarea}") // 10    13(lo ha cambiado entero)
	public ResponseEntity<?> findById(@PathVariable int idTarea) { 
		try {
			return ResponseEntity.ok(this.tareaService.findById(idTarea));
		}
		catch (TareaNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	//17
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Tarea tarea) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(this.tareaService.create(tarea));
		}
		catch(TareaException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
			
		}
	}
	
	//18
	@PutMapping("/{idTarea}")
	public ResponseEntity<?> update(@PathVariable int idTarea, @RequestBody Tarea tarea){
		try {
			return ResponseEntity.ok(this.tareaService.update(tarea, idTarea));
		}
		catch(TareaNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
		catch (TareaException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
		
	}
	
	
}







