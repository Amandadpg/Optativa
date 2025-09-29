package com.daw.services;

import java.util.List; //Esta libreria CUIDADO

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.persistence.entities.Tarea;
import com.daw.persistence.repositories.TareaRepository;

@Service // 3
public class TareaService {

	@Autowired // 4
	private TareaRepository tareaRepository;
	
	// 5
	//findAll() 
	public List<Tarea> findAll() {
		return this.tareaRepository.findAll();
		
	}
	
	// 6
	//findbyId 
	public Tarea findById(int idTarea) { //devuelve una tarea
		return this.tareaRepository.findById(idTarea).get();
	}
	
	//save (crear y actualizar)
	
	//deleteById
}






