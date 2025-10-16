package com.daw.persistence.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;

import com.daw.persistence.entities.Estado;
import com.daw.persistence.entities.Tarea;


public interface TareaRepository extends ListCrudRepository<Tarea, Integer>{ //2

	//22
	//SELECT * FROM TAREA WHERE ESTADO = ?
	//ESTE LO RESUME TODA LAS TAREAS
	List<Tarea> findByEstado(Estado estado);

	
	//OBTENER LAS TAREAS PENDIENTES
	//SELECT * FROM TAREA WHERE ESTADO = 'PENDIENDTE'
	
	
	//OBTENER LAS TAREAS EN PROGRESO
	//SELECT * FROM TAREA WHERE ESTADO = 'EN PROGRESO'
	
	//OBTENER LAS TAREAS COMPLETADAS
	//SELECT * FROM TAREA WHERE ESTADO = 'COMPLETADAS'
	
	Tarea save(Optional<Tarea> tareaOptional);

	List<Tarea> findByFechaVencimientoBefore(LocalDate fecha);

	List<Tarea> findByFechaVencimientoAfter(LocalDate fecha);

	List<Tarea> findByTitulo(String titulo);
}
