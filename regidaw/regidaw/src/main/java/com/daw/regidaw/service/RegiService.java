package com.daw.regidaw.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.regidaw.persistence.entities.Registro;
import com.daw.regidaw.persistence.repositories.RegiRepository;
import com.daw.regidaw.service.exceptions.RegistroException;
import com.daw.regidaw.service.exceptions.RegistroNotFoundException;

@Service
public class RegiService {

	@Autowired
	private RegiRepository regiRepository;
	
	
	//Ejercicio 1:
	public List<Registro> findAll() {
        return regiRepository.findAll(); 
    }
	
	

	public Registro findById(int idRegistro) { 
		if(!this.regiRepository.existsById(idRegistro)) { 
			throw new RegistroNotFoundException("El registro con id " + idRegistro +" no existe");
		}
		return this.regiRepository.findById(idRegistro).get();
	}
	
	//Ejercicio 2:
	public Registro update(Registro registro, int idRegistro) {
		if (registro.getId() != idRegistro) {
			throw new RegistroException("Los ids no coinciden");
				}
		if(!this.regiRepository.existsById(idRegistro)) { 
			throw new RegistroNotFoundException("El registro con id " + idRegistro +" no existe");
				}
		Registro registroBD = findById(idRegistro);

	        if (!registroBD.getFechaLectura().equals(registro.getFechaLectura())) {
	            throw new RegistroException("No se puede modificar la fecha de lectura");
	        }

	        if (!(registroBD.getPrecipitacion() == (registro.getPrecipitacion()))) {
	            throw new RegistroException("No se puede modificar la precipitación");
	        }

	        registroBD.setUbicacion(registro.getUbicacion());
	        registroBD.setTemperatura(registro.getTemperatura());

	        return regiRepository.save(registroBD);

				
	}
	
	//Ejercicio 3:
	public Registro updatePrecipitacion(int idRegistro, double anterior, double nueva) {
		if (!this.regiRepository.existsById(idRegistro)) {
	        throw new RegistroNotFoundException("El registro con id " + idRegistro + " no existe");
	    }
		
		Registro registroBD = findById(idRegistro);

        if (anterior == nueva) {
            throw new RegistroException("Las precipitaciones son idénticas");
        }

        if (!(registroBD.getPrecipitacion() == (anterior))) {
            throw new RegistroException("La precipitación anterior no coincide con la base de datos");
        }

        registroBD.setPrecipitacion(nueva);
        return regiRepository.save(registroBD);
    }
	
	//Ejercicio 4:
	public List<Registro> buscarPorUbicacionYFechas(String ubicacion, LocalDate inicio, LocalDate fin) {
        return regiRepository.findByUbicacionAndFechaLecturaBetween(ubicacion, inicio, fin);
    }




}
