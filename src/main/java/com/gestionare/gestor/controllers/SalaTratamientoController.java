package com.gestionare.gestor.controllers;

import java.util.HashMap;

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
import org.springframework.web.bind.annotation.RestController;

import com.gestionare.gestor.dto.SalaTratamientoDto;
import com.gestionare.gestor.models.SalaTratamientoModel;
import com.gestionare.gestor.services.SalaTratamientoService;

@CrossOrigin(origins = "*")
@Primary
@RestController
@RequestMapping("/api")
public class SalaTratamientoController {

	@Autowired
	private SalaTratamientoService salaService;

	@GetMapping("/salas-tratamiento")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.status(HttpStatus.OK).body(this.salaService.getAll());
	}

	@GetMapping("/salas-tratamiento/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") String id) {
		SalaTratamientoModel datos = this.salaService.findById(id);
		if (datos == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>() {
				{

					put("mensaje", "Recurso no encontrado");
				}
			});
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(this.salaService.findById(id));
		}

	}

	@PostMapping("/salas-tratamiento")
	public ResponseEntity<?> metodo_post(@RequestBody SalaTratamientoDto dto) {
		try {
			this.salaService.save(new SalaTratamientoModel(dto.getName(), dto.getDescription()));
			return ResponseEntity.status(HttpStatus.CREATED).body(new HashMap<String, String>() {
				{
					put("mensaje", "Creado con exito");
				}
			});
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>() {
				{
					put("mensaje", "Ocurrió un error");
				}
			});
		}
	}

	@PutMapping("/salas-tratamiento/{id}")
	public ResponseEntity<?> edit(@PathVariable("id") String id, @RequestBody SalaTratamientoDto dto) {
		SalaTratamientoModel datos = this.salaService.findById(id);
		if (datos != null) {
			datos.setName(dto.getName());
			datos.setDescription(dto.getDescription());
			this.salaService.save(datos);
			return ResponseEntity.status(HttpStatus.CREATED).body(new HashMap<String, String>() {
				{

					put("mensaje", "editado con exito");
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

	@DeleteMapping("/salas-tratamiento/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") String id) {
		SalaTratamientoModel datos = this.salaService.findById(id);
		if (datos != null) {
			this.salaService.delete(id);
			return ResponseEntity.status(HttpStatus.CREATED).body(new HashMap<String, String>() {
				{

					put("mensaje", "Borrado con exito");
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
