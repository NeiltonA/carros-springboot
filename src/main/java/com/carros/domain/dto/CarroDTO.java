package com.carros.domain.dto;

import org.modelmapper.ModelMapper;

import com.carros.domain.Carro;

import lombok.Data;

@Data
public class CarroDTO {

	private long id;
	private String nome;
	private String tipo;
	
	//assume o gat/set da class carroDTO
	//metodo static retorno o carroDTO
	public static CarroDTO create(Carro c) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(c, CarroDTO.class);
	}


	
	
}
