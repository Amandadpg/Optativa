package com.daw.services.dto;

import com.daw.persistence.entities.Pizza;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PizzaPedidoOutputDTO {

	private Integer id;
	private Double cantidad;
	private Double precio;
	private Integer idPizza;
	private String pizza;
}
