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
import com.gestionare.gestor.dto.SesionDto;
import com.gestionare.gestor.models.PacienteModel;
import com.gestionare.gestor.models.ProfesionalModel;
import com.gestionare.gestor.models.SesionModel;
import com.gestionare.gestor.services.PacienteService;
import com.gestionare.gestor.services.ProfesionalService;
import com.gestionare.gestor.services.SesionService;

@CrossOrigin(origins = "*")
@Primary
@RestController
@RequestMapping("/api")
public class SesionController {

	@Autowired
	ProfesionalService profesionalService;
	@Autowired
	PacienteService pacienteService;
	@Autowired
	SesionService sesionService;

	@GetMapping("/sesiones")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.status(HttpStatus.OK).body(this.sesionService.getAll());
	}

	@GetMapping("/sesiones/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") String id) {
		SesionModel datos = this.sesionService.findById(id);
		if (datos == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>() {
				{

					put("mensaje", "Recurso no encontrado");
				}
			});
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(this.sesionService.findById(id));
		}

	}

	@PostMapping("sesiones")
	public ResponseEntity<?> createSesion(@RequestBody SesionDto dto) {

		ProfesionalModel datosProfesional = this.profesionalService.findById(dto.getProfesional().getId());
		PacienteModel datosPaciente = this.pacienteService.findById(dto.getPaciente().getId());
		if (datosProfesional == null || datosPaciente == null) {
			if (datosProfesional == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>() {
					{
						put("mensaje", "Profesional no encontrado");
					}
				});
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>() {
					{
						put("mensaje", "Paciente no encontrado");
					}
				});

			}

		} else {
			try {
				this.sesionService.save(new SesionModel(dto.getFecha(), dto.getMotivo(), dto.getDiagnostico(),
						dto.getTratamiento(), dto.getPrecio(), dto.getDescuento(), datosPaciente, datosProfesional));
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

}
