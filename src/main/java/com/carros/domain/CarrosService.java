package com.carros.domain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.carros.domain.dto.CarroDTO;
import com.carros.domain.exception.ObjectNotFoundException;

@Service
public class CarrosService {
	
	@Autowired
	private CarroRepository rep;
	
	//metodo que busca os carros
	public List<CarroDTO> getCarros(){
   //"findAll" = retorna uma lista de carros
   //"stream().map" = para mapear a lista
	//"CarroTDO:: new" = percorrendo carro por carro e criando um carroDTO "::"sitaxe resumida
	//"collect(Collectors.toList()"= gera uma nova lista de carroDTO	
	return rep.findAll().stream().map(CarroDTO::create).collect(Collectors.toList());
		
	}
	
	
	public CarroDTO getCarroById(Long id) {
		Optional<Carro> carro = rep.findById(id);
		
		//validação conceito de lamida
		//caso existe retorna um carro DTO caso contrario uma exceção ObjectNotFoundException é lancada
		return carro.map(CarroDTO::create).orElseThrow(() ->new ObjectNotFoundException("Carro não encontrado"));
	}
	
	//metodo que busca os carros
	public List<CarroDTO> getCarrosByTipo(String tipo) {
		// TODO Auto-generated method stub
		return rep.findByTipo(tipo).stream().map(CarroDTO::create).collect(Collectors.toList());
	}



	public CarroDTO insert(Carro carro) {
		Assert.isNull(carro.getId(), "Não foi possivel atualizar o registro");
		return CarroDTO.create(rep.save(carro));
		
	}

	public CarroDTO update(Carro carro, Long id) {
		Assert.notNull(id,  "Não foi possivel atualizar o registro");
		
		// Busca o carro no banco de dados
		Optional<Carro>optional = rep.findById(id);
		if (optional.isPresent()) {
			Carro db = optional.get();
			
			//Copia as propriedades
			db.setNome(carro.getNome());
			db.setTipo(carro.getTipo());
			
			//Atualiza o carro
			rep.save(db);
			return CarroDTO.create(db);
		}else {
			throw new RuntimeException("Não foi possivel atualizar o registro");
		}
		
/*		getCarroById(id).map(db->{
			//Copia as propriedades
			db.setNome(carro.getNome());
			db.setTipo(carro.getTipo());
			
			//Atualiza o carro
			rep.save(db);
			return db;
		}).orElseThrow(()-> new RuntimeException("Não foi possivel atualizar o registro"));
*/
	}

	public void delete(Long id) {
	rep.deleteById(id);
		
	}


	



}
