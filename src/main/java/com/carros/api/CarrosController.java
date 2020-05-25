package com.carros.api;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.carros.domain.Carro;
import com.carros.domain.CarrosService;
import com.carros.domain.dto.CarroDTO;

@RestController //transforma a class em webservice rest
@RequestMapping("/api/v1/carros") // faz o mapeamento para o '/'
public class CarrosController {
	
	@Autowired //injeta dependencia
	private CarrosService service;
	
	@GetMapping()
	public ResponseEntity<List<CarroDTO>> get() {
		 return ResponseEntity.ok(service.getCarros());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity get(@PathVariable("id") Long id) {
		CarroDTO carro = service.getCarroById(id);
		
		return ResponseEntity.ok(carro);
		//validação if ternario
		/*
		 * return carro.isPresent() ? ResponseEntity.ok(carro.get()) :
		 * ResponseEntity.notFound().build();
		 */
		
		//validação if normal
		/*
		 * if (carro.isPresent()) { return ResponseEntity.ok(carro.get()); }else {
		 * return ResponseEntity.notFound().build(); }
		 */
		
	}
	
	@GetMapping("/tipo/{tipo}")
	public ResponseEntity getCarrosByTipo(@PathVariable("tipo") String tipo) {
		List<CarroDTO> carros =  service.getCarrosByTipo(tipo);
		return carros.isEmpty() ?
				ResponseEntity.noContent().build():
					ResponseEntity.ok(carros);
	}
	
	@PostMapping
	@Secured("ROLE_ADMIN")
	public ResponseEntity post(@RequestBody Carro carro) {
			CarroDTO c = service.insert(carro);
			URI location = getUri(c.getId());
			return ResponseEntity.created(location).build();	
		
	
	}
	
	private URI getUri(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(id).toUri();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity put(@PathVariable("id") Long id, @RequestBody Carro carro) {
		carro.setId(id);
		CarroDTO c = service.update(carro, id);
		// se o carro existe retorna 200
		return c != null ?
				ResponseEntity.ok(c)
				// se não existe retorna um not found
				 : ResponseEntity.notFound().build();
				
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable("id") Long id) {
		service.delete(id);
		// se o carro existe retorna 200
		return ResponseEntity.ok().build();
			
		
	}
	
}
