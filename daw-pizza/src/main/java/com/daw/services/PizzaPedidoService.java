package com.daw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.persistence.entities.PizzaPedido;
import com.daw.persistence.repositories.PizzaPedidoRepository;
import com.daw.services.exception.PizzaPedidoException;
import com.daw.services.exception.PizzaPedidoNotFoundException;

@Service
public class PizzaPedidoService {

	@Autowired
	private PizzaPedidoRepository pizzaPedidoRepository;
	
	public List<PizzaPedido> findAll() {
        return pizzaPedidoRepository.findAll(); 
    }
	
	public PizzaPedido findById(int idPizzaPedido) { 
		if(!this.pizzaPedidoRepository.existsById(idPizzaPedido)) { 
			throw new PizzaPedidoNotFoundException("La pizza del pedido con id " + idPizzaPedido +" no existe");
		}
		return this.pizzaPedidoRepository.findById(idPizzaPedido).get();
	}
	
	public PizzaPedido create(PizzaPedido pizzaPedido) {
		
		pizzaPedido.setId(0);
		pizzaPedido.setCantidad(0.0);
		pizzaPedido.setPrecio(0.0);

		
		return this.pizzaPedidoRepository.save(pizzaPedido);
	}
	
	public PizzaPedido update(PizzaPedido pizzaPedido, int idPizzaPedido) {
		if (pizzaPedido.getId() != idPizzaPedido) {
			throw new PizzaPedidoException("Los ids no coinciden");
		}
		if(!this.pizzaPedidoRepository.existsById(idPizzaPedido)) { 
			throw new PizzaPedidoNotFoundException("La pizza del pedido con id " + idPizzaPedido +" no existe");
		}
		
		PizzaPedido pizzaPedidoBD = this.findById(idPizzaPedido);
		
		return this.pizzaPedidoRepository.save(pizzaPedidoBD);
		
	}
	
	public void delete(int idPizzaPedido) {
		if(!this.pizzaPedidoRepository.existsById(idPizzaPedido)) {
			throw new PizzaPedidoNotFoundException("La pizza del pedido no existe.");
		}
		this.pizzaPedidoRepository.deleteById(idPizzaPedido);
	}


}
