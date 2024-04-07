package com.gestionare.gestor.controllers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestionare.gestor.dto.ProfesionalDto;
import com.gestionare.gestor.models.ProfesionalModel;
import com.gestionare.gestor.services.ProfesionalService;

@CrossOrigin(origins = "*")
@Primary
@RestController
@RequestMapping("/api")
public class ProfesionalController {
	@Autowired
	private ProfesionalService profesionalService;

	@GetMapping("/profesionales")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.status(HttpStatus.OK).body(this.profesionalService.getAll());
	}

	@GetMapping("/profesionales/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") String id) {
		ProfesionalModel datos = this.profesionalService.findById(id);
		if (datos == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>() {
				{

					put("mensaje", "Recurso no encontrado");
				}
			});
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(this.profesionalService.findById(id));
		}

	}

	@PostMapping("/profesionales")
	public ResponseEntity<?> metodo_post(@RequestBody ProfesionalDto dto) {
		try {
			this.profesionalService.save(new ProfesionalModel(dto.getName(),dto.getProfesion(),dto.getEmail(),dto.getBirthdate(),dto.getCity(),dto.getAdress(),dto.getNumber(),dto.getSalary()));
			return ResponseEntity.status(HttpStatus.CREATED).body(new HashMap<String, String>() {
				{

					put("mensaje", "Creado con exito");
				}
			});
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>() {
				{ 

					put("mensaje", "Ocurrio error");
				}
			});
		}
	}
}
