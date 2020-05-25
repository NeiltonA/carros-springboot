package com.carros;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.carros.domain.Carro;
import com.carros.domain.CarrosService;
import com.carros.domain.dto.CarroDTO;

@SpringBootTest
class CarrosApplicationTests {

	@Autowired
	private CarrosService service;
	
	@Test
	void teste1() {
		Carro c = new Carro();
		
		c.setNome("Fusca");
		c.setTipo("VW");
		
		CarroDTO carro = service.insert(c);
		
		assertNotNull(carro);
		
		Long id = carro.getId();
		assertNotNull(id);
		//Buscar o objeto
		/*
		 * //Optional<CarroDTO>op = service.getCarroById(id);
		 * assertTrue(op.isPresent());
		 * 
		 * carro = op.get(); assertEquals("Fusca", carro.getNome()); assertEquals( "VW",
		 * carro.getTipo());
		 * 
		 * //Deletar o objeto service.delete(id);
		 * 
		 * //Verificar se deletou assertFalse(service.getCarroById(id).isPresent());
		 */
	}

}
