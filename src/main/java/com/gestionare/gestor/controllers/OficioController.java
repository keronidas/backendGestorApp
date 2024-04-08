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

import com.gestionare.gestor.dto.OficioDto;
import com.gestionare.gestor.dto.PacienteDto;
import com.gestionare.gestor.models.OficioModel;
import com.gestionare.gestor.models.PacienteModel;
import com.gestionare.gestor.services.OficioService;

@CrossOrigin(origins = "*")
@RestController
@Primary
@RequestMapping("/api")
public class OficioController {
	
	@Autowired
	private OficioService oficioService;
	
	@GetMapping("/oficios")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.status(HttpStatus.OK).body(this.oficioService.getAll());
	}
	
	@GetMapping("/oficios/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") String id) {
		OficioModel datos = this.oficioService.findById(id);
		if (datos == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>() {
				{

					put("mensaje", "Recurso no encontrado");
				}
			});
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(this.oficioService.findById(id));
		}

	}

	@PostMapping("/oficios")
	public ResponseEntity<?> metodo_post(@RequestBody OficioDto dto) {
		try {
			this.oficioService.save(new OficioModel(dto.getName()));
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

	@PutMapping("/oficios/{id}")
	public ResponseEntity<?> edit(@PathVariable("id") String id, @RequestBody OficioDto dto) {
		OficioModel datos = this.oficioService.findById(id);
		if (datos != null) {
			datos.setName(dto.getName());
			this.oficioService.save(datos);
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
	
	@DeleteMapping("/oficios/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") String id) {
		OficioModel datos = this.oficioService.findById(id);
		if (datos != null) {
			this.oficioService.delete(id);
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
