package com.gestionare.gestor.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gestionare.gestor.dto.ProfesionalDto;
import com.gestionare.gestor.models.PacienteModel;
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
	public ResponseEntity<?> createProfesional(@RequestBody ProfesionalDto dto) {
	
			try {
				this.profesionalService.save(new ProfesionalModel(dto.getName(), dto.getProfesion(), dto.getEmail(),
						dto.getBirthdate(), dto.getCity(), dto.getAddress(), dto.getNumber(), dto.getSalary()));
				return ResponseEntity.status(HttpStatus.CREATED).body(new HashMap<String, String>() {
					{

						put("mensaje", "Creado con exito");
					}
				});
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>() {
					{

						put("mensaje", "Ocurrio error" + e.getMessage());
					}
				});
			}
		
	}

	@SuppressWarnings("all")
	@PutMapping("/profesionales/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") String id, @RequestBody ProfesionalDto dto) {
		ProfesionalModel datosProfesional = this.profesionalService.findById(id);
		
		if (datosProfesional != null) {
			datosProfesional.setAdress(dto.getAddress());
			datosProfesional.setBirthdate(dto.getBirthdate());
			datosProfesional.setCity(dto.getCity());
			datosProfesional.setEmail(dto.getEmail());
			datosProfesional.setName(dto.getName());
			datosProfesional.setNumber(dto.getNumber());
			datosProfesional.setSalary(dto.getSalary());
			this.profesionalService.save(datosProfesional);
			return ResponseEntity.status(HttpStatus.CREATED).body(new HashMap<String, String>() {
				{

					put("mensaje", "Editado con exito");
				}
			});

		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>() {
				{

					put("mensaje", "Un profesional tiene que tener al menos una profesion");
				}
			});
		}
	}

	@DeleteMapping("/profesionales/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") String id) {
		ProfesionalModel datos = this.profesionalService.findById(id);
		if (datos != null) {
			
			this.profesionalService.delete(id);
			return ResponseEntity.status(HttpStatus.CREATED).body(new HashMap<String, String>() {
				{

					put("mensaje", "Eliminado con exito");
				}
			});
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>() {
				{

					put("mensaje", "Ocurrio error");
				}
			});
		}
	}


}
