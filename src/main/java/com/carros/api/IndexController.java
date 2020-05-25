package com.carros.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //transforma a class em webservice rest
@RequestMapping("/") // faz o mapeamento para o '/'
public class IndexController {
	
	@GetMapping
	public String get() {
		 return "APi dos carros";
	}
	


}
