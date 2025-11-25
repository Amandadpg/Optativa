package com.daw.services.dto;


import java.time.LocalDate;
import java.util.List;

import com.daw.persistence.entities.enums.Metodo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PedidoDTO {
	private Integer id;
	private LocalDate fecha;
	private Double total;
	private Metodo metodo;
	private String cliente;
	private String telefono;
	private String direccion;
	private String notas;
	private Integer numeroPizzas;
	private List<PizzaPedidoOutputDTO> pizzas;
	
}
