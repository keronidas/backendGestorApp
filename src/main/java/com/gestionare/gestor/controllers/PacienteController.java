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

import com.gestionare.gestor.dto.PacienteDto;
import com.gestionare.gestor.models.PacienteModel;
import com.gestionare.gestor.services.PacienteService;

@CrossOrigin(origins = "*")
@RestController
@Primary
@RequestMapping("/api")
public class PacienteController {
	@Autowired
	private PacienteService pacienteService;

	@GetMapping("/pacientes")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.status(HttpStatus.OK).body(this.pacienteService.getAll());
	}
	
	@GetMapping("/pacientes/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") String id){
		PacienteModel datos= this.pacienteService.findById(id);
		if (datos==null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>(){
				{
					
					put("mensaje","Recurso no encontrado");
				}
			});
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(this.pacienteService.findById(id));
		}
		
	}

	@PostMapping("/pacientes")
	public ResponseEntity<?> metodo_post(@RequestBody PacienteDto dto) {
		try {
			this.pacienteService.save(new PacienteModel(dto.getName(), dto.getProfesion(), dto.getEmail(),
					dto.getBirthdate(), dto.getCity(), dto.getNumber()));
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