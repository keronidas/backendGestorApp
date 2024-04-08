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

import com.gestionare.gestor.dto.ProfesionalDto;
import com.gestionare.gestor.dto.SesionDto;
import com.gestionare.gestor.models.FacturasModel;
import com.gestionare.gestor.models.PacienteModel;
import com.gestionare.gestor.models.ProfesionalModel;
import com.gestionare.gestor.models.SesionModel;
import com.gestionare.gestor.services.FacturasService;
import com.gestionare.gestor.services.PacienteService;
import com.gestionare.gestor.services.ProfesionalService;
import com.gestionare.gestor.services.SesionService;

@CrossOrigin(origins = "*")
@Primary
@RestController
@RequestMapping("/api")
public class SesionController {

	@Autowired
	private ProfesionalService profesionalService;
	@Autowired
	private PacienteService pacienteService;
	@Autowired
	private SesionService sesionService;
	@Autowired
	private FacturasService facturasService;

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

	@PostMapping("/sesiones")
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

	@PutMapping("/sesiones/{id}")
	public ResponseEntity<?> update(@PathVariable("id") String id, @RequestBody SesionDto dto) {
		SesionModel datos = this.sesionService.findById(id);
		ProfesionalModel datosProfesional = this.profesionalService.findById(dto.getProfesional().getId());
		PacienteModel datosPaciente = this.pacienteService.findById(dto.getPaciente().getId());
		if (datos != null || datosProfesional != null || datosPaciente != null) {
			datos.setPaciente(this.pacienteService.findById(dto.getPaciente().getId()));
			datos.setProfesional(this.profesionalService.findById(dto.getPaciente().getId()));
			datos.setFecha(dto.getFecha());
			datos.setMotivo(dto.getMotivo());
			datos.setDiagnostico(dto.getDiagnostico());
			datos.setTratamiento(dto.getTratamiento());
			datos.setPrecio(dto.getPrecio());
			datos.setDescuento(dto.getDescuento());
			datos.setFinalPrice(dto.getPrecio() * (100 - dto.getDescuento()) / 100);
			this.sesionService.save(datos);

			// Actualizamos facturas
			List<FacturasModel> datosFactura = this.facturasService.getAll();
			for (FacturasModel factura : datosFactura) {
				factura.calcularImporteTotal();
				this.facturasService.save(factura);
			}

			return ResponseEntity.status(HttpStatus.OK).body(new HashMap<String, String>() {
				{

					put("mensaje", "Editado con exito");
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

	@DeleteMapping("/sesiones/{id}")
	public ResponseEntity<?> deleteSesion(@PathVariable("id") String id) {
		SesionModel datos = this.sesionService.findById(id);

		if (datos != null) {
			this.sesionService.delete(id);
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
