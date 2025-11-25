package com.daw.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.persistence.entities.Pedido;
import com.daw.persistence.entities.Pizza;
import com.daw.persistence.entities.enums.Metodo;
import com.daw.persistence.repositories.PedidoRepository;
import com.daw.services.exception.PedidoException;
import com.daw.services.exception.PedidoNotFoundException;
import com.daw.services.exception.PizzaNotFoundException;

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
		pedido.setFecha(LocalDate.now());
		pedido.setTotal(0.0);
		pedido.setMetodo(Metodo.LOCAL);
		
		
		return this.pedidoRepository.save(pedido);
	}
	
	public Pedido update(Pedido pedido, int idPedido) {
		if (pedido.getId() != idPedido) {
			throw new PedidoException("Los ids no coinciden");
		}
		if(!this.pedidoRepository.existsById(idPedido)) { 
			throw new PedidoNotFoundException("EL pedido con id " + idPedido +" no existe");
		}
		
		Pedido pedidoBD = this.findById(idPedido);
		pedidoBD.setNotas(pedido.getNotas());
		
		
		return this.pedidoRepository.save(pedidoBD);
		
	}
	
	public void delete(int idPedido) {
		if(!this.pedidoRepository.existsById(idPedido)) {
			throw new PedidoNotFoundException("EL pedido no existe.");
		}
		this.pedidoRepository.deleteById(idPedido);
	}
	
	public Pedido añadirNotas(int idPedido, String notas) {

        if (!pedidoRepository.existsById(idPedido)) {
            throw new PedidoNotFoundException("El pedido con id " + idPedido + " no existe");
        }

        Pedido pedido = pedidoRepository.findById(idPedido).get();

        if (pedido.getNotas() == null) {
            pedido.setNotas(notas);
        } else {
            pedido.setNotas(pedido.getNotas() + " | " + notas);
        }

        return pedidoRepository.save(pedido);
    }

	
}
