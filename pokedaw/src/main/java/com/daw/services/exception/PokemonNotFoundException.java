package com.daw.services.exception;

public class PokemonNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8850963696421323937L;
	
	public PokemonNotFoundException(String message) {
		super(message);
	}
}
