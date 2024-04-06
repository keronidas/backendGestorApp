package com.gestionare.gestor.controllers;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class MongoController {

	@GetMapping("")
	public ResponseEntity<?> home() {
		return ResponseEntity.status(HttpStatus.OK).body(new HashMap<String, String>(){
			{
				put("mensaje","Bienvenido a la app");
			}
		});
		
	}
	

}
