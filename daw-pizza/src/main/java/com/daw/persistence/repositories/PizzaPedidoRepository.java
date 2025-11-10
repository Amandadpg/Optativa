package com.daw.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daw.persistence.entities.PizzaPedido;

@Repository
public interface PizzaPedidoRepository extends JpaRepository<PizzaPedido, Integer>{

	
}
