package me.citasmedicas.restservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hola")
public class RESTejemplo {
	
	@GetMapping
	public String hello() {
		return "hello world";
	}

}
