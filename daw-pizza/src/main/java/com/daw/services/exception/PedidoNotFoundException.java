package com.daw.services.exception;

public class PedidoNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PedidoNotFoundException(String message) {
		super(message);
	}
}
