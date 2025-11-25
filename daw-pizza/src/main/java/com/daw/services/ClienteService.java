package com.daw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.persistence.entities.Cliente;
import com.daw.persistence.repositories.ClienteRepository;
import com.daw.services.exception.ClienteException;
import com.daw.services.exception.ClienteNotFoundException;
import com.daw.services.exception.PizzaNotFoundException;


@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public List<Cliente> findAll() {
        return clienteRepository.findAll(); 
    }
	
	public Cliente findById(int idCliente) { 
		if(!this.clienteRepository.existsById(idCliente)) { 
			throw new ClienteNotFoundException("El cliente con id " + idCliente +" no existe");
		}
		return this.clienteRepository.findById(idCliente).get();
	}
	
	public Cliente create(Cliente cliente) {
		
		cliente.setId(0);
		
		
		return this.clienteRepository.save(cliente);
	}
	
	public Cliente update(Cliente cliente, int idCliente) {
		if (cliente.getId() != idCliente) {
			throw new ClienteException("Los ids no coinciden");
		}
		if(!this.clienteRepository.existsById(idCliente)) { 
			throw new PizzaNotFoundException("El cliente con id " + idCliente +" no existe");
		}
		
		Cliente clienteBD = this.findById(idCliente);
		clienteBD.setNombre(cliente.getNombre());
		clienteBD.setDireccion(cliente.getDireccion());
		clienteBD.setEmail(cliente.getEmail());
		clienteBD.setTelefono(cliente.getTelefono());
		
		
		return this.clienteRepository.save(clienteBD);
		
	}
	
	public void delete(int idCliente) {
		if(!this.clienteRepository.existsById(idCliente)) {
			throw new PizzaNotFoundException("El cliente no existe.");
		}
		this.clienteRepository.deleteById(idCliente);
	}
	
	public Cliente modificarDireccion(int idCliente, String direccionNueva) {
	    if (!clienteRepository.existsById(idCliente)) {
	        throw new ClienteNotFoundException("El cliente con id " + idCliente + " no existe");
	    }

	    Cliente cliente = clienteRepository.findById(idCliente).get();
	    cliente.setDireccion(direccionNueva);

	    return clienteRepository.save(cliente);
	}
	
	public Cliente buscarPorTelefono(String telefono) {
	    Cliente cliente = this.clienteRepository.findByTelefono(telefono);

	    if (cliente == null) {
	        throw new ClienteNotFoundException("No se encontró cliente con teléfono: " + telefono);
	    }

	    return cliente;
	}


}
