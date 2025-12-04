package com.daw.persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daw.persistence.entities.PizzaPedido;

@Repository
public interface PizzaPedidoRepository extends JpaRepository<PizzaPedido, Integer>{

	List<PizzaPedido> findByIdPedido(int idPedido);
}
