package com.daw.services;

import java.time.LocalDate;
import java.util.List; //Esta libreria CUIDADO

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.persistence.entities.Estado;
import com.daw.persistence.entities.Tarea;
import com.daw.persistence.repositories.TareaRepository;
import com.daw.services.exception.TareaException;
import com.daw.services.exception.TareaNotFoundException;

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
		if(!this.tareaRepository.existsById(idTarea)) { //11
			throw new TareaNotFoundException("La tarea con id " + idTarea +" no existe");
		}
		return this.tareaRepository.findById(idTarea).get();
	}
	
	//save (crear y actualizar) //14
	public Tarea create(Tarea tarea) {
		if(tarea.getFechaVencimiento().isBefore(LocalDate.now())) {
			//16
			throw new TareaException("La fecha de vencimineto debe ser posterior");
		}
		
		tarea.setId(0);
		tarea.setEstado(Estado.PENDIENTE);
		tarea.setFechaCreacion(LocalDate.now());
		
		return this.tareaRepository.save(tarea);
	}
	
	//19
	public Tarea update(Tarea tarea, int idTarea) {
		if (tarea.getId() != idTarea) {
			throw new TareaException("El id del body y el id del path no coinciden");
		}
		if(!this.tareaRepository.existsById(idTarea)) { 
			throw new TareaNotFoundException("La tarea con id " + idTarea +" no existe");
		}
		if (tarea.getEstado() != null) {
			throw new TareaException("No se puede modificar el estado.");
		}
		if (tarea.getFechaCreacion() != null) {
			throw new TareaException("No se puede modificar la fecha de creacion.");
		}
		
		Tarea tareaBD = this.findById(idTarea);
		tareaBD.setDescripcion(tarea.getDescripcion());
		tareaBD.setTitulo(tarea.getTitulo());
		tareaBD.setFechaVencimiento(tarea.getFechaVencimiento());
		
		
		return this.tareaRepository.save(tarea);
		
	}
	
	//deleteById
}






