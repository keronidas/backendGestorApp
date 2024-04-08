package com.gestionare.gestor.controllers;

import java.util.ArrayList;
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
import com.gestionare.gestor.models.FacturasModel;
import com.gestionare.gestor.models.SesionModel;
import com.gestionare.gestor.services.FacturasService;
import com.gestionare.gestor.services.SesionService;

//private String id;
//private String code;
//private Date date;
//private Boolean paid;
//private Float discount;
//private Float amount;
//private List<SesionModel> sesiones;

@CrossOrigin(origins = "*")
@Primary
@RestController
@RequestMapping("/api")
public class FacturasController {

	@Autowired
	private FacturasService facturasService;
	@Autowired
	private SesionService sesionService;

	@GetMapping("/facturas")
	public ResponseEntity<?> getAll() {
		List<FacturasModel> datos = this.facturasService.getAll();
		for (FacturasModel factura : datos) {
			factura.calcularImporteTotal();
			this.facturasService.save(factura);
		}
		return ResponseEntity.status(HttpStatus.OK).body(datos);
	}

	@GetMapping("/facturas/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") String id) {
		FacturasModel datos = this.facturasService.findById(id);
		if (datos == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>() {
				{

					put("mensaje", "Recurso no encontrado");
				}
			});
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(this.facturasService.findById(id));
		}

	}

	@PostMapping("/facturas")
	public ResponseEntity<?> createSesion(@RequestBody FacturasDto dto) {
		List<SesionModel> datosSesion = new ArrayList<SesionModel>();
		for (SesionModel sesion : dto.getSesiones()) {
			datosSesion.add(sesion);
		}
		if (datosSesion.size() == 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>() {
				{
					put("mensaje", "Sesiones no encontradas");
				}
			});
		} else {
			try {
				this.facturasService.save(new FacturasModel(dto.getDiscount(), dto.getPaid(), datosSesion));
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

	@PutMapping("/facturas/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") String id, @RequestBody FacturasDto dto) {
		FacturasModel datos = this.facturasService.findById(id);
		List<SesionModel> datosSesion = new ArrayList<SesionModel>();
		for (SesionModel sesion : dto.getSesiones()) {
			datosSesion.add(sesion);
		}
		if (datosSesion.size() != 0) {
			datos.setDiscount(dto.getDiscount());
			datos.setPaid(dto.getPaid());
			datos.setSesiones(datosSesion);
			this.facturasService.save(datos);
			return ResponseEntity.status(HttpStatus.CREATED).body(new HashMap<String, String>() {
				{

					put("mensaje", "Editado con exito");
				}
			});
			
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>() {
				{

					put("mensaje", "No se han incluido sesiones en la factura");
				}
			});
		}
	}

	@DeleteMapping("/facturas/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") String id) {
		FacturasModel datos = this.facturasService.findById(id);
		if (datos != null) {
			this.facturasService.delete(id);
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
