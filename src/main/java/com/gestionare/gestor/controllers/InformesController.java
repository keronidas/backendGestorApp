package com.gestionare.gestor.controllers;

import java.util.HashMap;
import java.util.List;

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

import com.gestionare.gestor.dto.FacturasDto;
import com.gestionare.gestor.dto.InformesDto;
import com.gestionare.gestor.models.InformesModel;
import com.gestionare.gestor.models.OficioModel;
import com.gestionare.gestor.models.SesionModel;
import com.gestionare.gestor.services.InformesService;
import com.gestionare.gestor.services.SesionService;

@CrossOrigin(origins = "*")
@Primary
@RestController
@RequestMapping("/api")
public class InformesController {

	@Autowired
	private InformesService informesService;
	@Autowired
	private SesionService sesionService;

	@GetMapping("/informes")
	public ResponseEntity<?> getAll() {
		List<InformesModel> datos = this.informesService.getAll();
		return ResponseEntity.status(HttpStatus.OK).body(datos);
	}

	@GetMapping("/informes/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") String id) {
		InformesModel datos = this.informesService.findById(id);
		if (datos == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>() {
				{

					put("mensaje", "Recurso no encontrado");
				}
			});
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(this.informesService.findById(id));
		}

	}

	@PostMapping("/informes")
	public ResponseEntity<?> createSesion(@RequestBody InformesDto dto) {
		SesionModel datosSesion = this.sesionService.findById(dto.getSesion().getId());
		try {
			this.informesService.save(new InformesModel(dto.getTitle(), dto.getDescription(), datosSesion));
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

	@PutMapping("/informes/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") String id, @RequestBody InformesDto dto) {
		InformesModel datos = this.informesService.findById(id);
		if (datos == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>() {
				{

					put("mensaje", "Ocurrio error");
				}
			});
		} else {
			this.informesService.save(new InformesModel(dto.getTitle(), dto.getDescription(), dto.getSesion()));
			return ResponseEntity.status(HttpStatus.CREATED).body(new HashMap<String, String>() {
				{

					put("mensaje", "Editado con exito");
				}
			});
		}
	}
	@DeleteMapping("/informes/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") String id) {
		InformesModel datos = this.informesService.findById(id);
		if (datos != null) {
			this.informesService.delete(id);
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
