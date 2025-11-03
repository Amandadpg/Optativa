package com.daw.regidaw.web.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daw.regidaw.persistence.entities.Registro;
import com.daw.regidaw.service.RegiService;
import com.daw.regidaw.service.exceptions.RegistroException;
import com.daw.regidaw.service.exceptions.RegistroNotFoundException;

@RestController
@RequestMapping("/registros")
public class RegiController {

	@Autowired 
	private RegiService regiService;
	
	
	//http://localhost:8081/registros
	@GetMapping
    public ResponseEntity<List<Registro>> list() {
        return ResponseEntity.ok(this.regiService.findAll());
    }

	//http://localhost:8081/registros/4
	@PutMapping("/{idRegistro}")
    public ResponseEntity<?> updateRegistro( @PathVariable int idRegistro, @RequestBody Registro registro) {
        try {
            return ResponseEntity.ok(this.regiService.update(registro, idRegistro));
        } catch (RegistroException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); 
        } catch (RegistroNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()); 
        }
    }
	
	//http://localhost:8081/registros/1/precipitacion?anterior=0.0&nueva=10.2
	@PutMapping("/{idRegistro}/precipitacion")
	public ResponseEntity<?> updatePrecipitacion(@PathVariable int idRegistro, @RequestParam double anterior, @RequestParam double nueva) {
        try {
        	return ResponseEntity.ok(this.regiService.updatePrecipitacion(idRegistro, anterior, nueva));
        } catch (RegistroException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); 
        } catch (RegistroNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()); 
        }
    }
	
	//
	@GetMapping("/buscar")
    public ResponseEntity<List<Registro>> buscarPorUbicacionYFechas(
            @RequestParam String ubicacion,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin
    ) {
        List<Registro> resultados = regiService.buscarPorUbicacionYFechas(ubicacion, inicio, fin);
        return ResponseEntity.ok(resultados); 
    }




}
