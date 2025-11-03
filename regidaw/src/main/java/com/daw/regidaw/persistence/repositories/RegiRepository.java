package com.daw.regidaw.persistence.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.daw.regidaw.persistence.entities.Registro;

@Repository
public interface RegiRepository extends ListCrudRepository<Registro, Integer>{

	 List<Registro> findByUbicacionAndFechaLecturaBetween(String ubicacion, LocalDate inicio, LocalDate fin);
}
