package com.daw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.daw.persistence.entities.Cliente;
import com.daw.persistence.entities.Pedido;
import com.daw.persistence.repositories.PedidoRepository;
import com.daw.services.exception.ClienteException;
import com.daw.services.exception.PedidoNotFoundException;
import com.daw.services.exception.PizzaNotFoundException;
import com.daw.persistence.entities.Pedido;
import com.daw.persistence.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	public List<Pedido> findAll() {
        return pedidoRepository.findAll(); 
    }
	
	public Pedido findById(int idPedido) { 
		if(!this.pedidoRepository.existsById(idPedido)) { 
			throw new PedidoNotFoundException("El pedido con id " + idPedido +" no existe");
		}
		return this.pedidoRepository.findById(idPedido).get();
	}
	
	public Pedido create(Pedido pedido) {
		
		pedido.setId(0);
		//mirar por si hay que poner algo mas
		//hacer update y delete 
		//y el pizza pedido entero
		
		
		return this.pedidoRepository.save(pedido);
	}
	
}
