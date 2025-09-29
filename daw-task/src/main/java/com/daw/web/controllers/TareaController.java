package com.daw.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daw.persistence.entities.Tarea;
import com.daw.services.TareaService;

@RestController // 7
@RequestMapping("/tareas") // 8
public class TareaController {
	
	@Autowired // 9
	private TareaService tareaService;
	
	// con poner el GET ya vale, no hace falta poner el nombre
	public List<Tarea> list() {
		return this.tareaService.findAll();
	}
	
	public Tarea findById(int idTarea) {
		return this.tareaService.findById(idTarea);
	}
	
}
