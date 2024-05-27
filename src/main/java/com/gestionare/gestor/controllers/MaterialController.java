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
import com.gestionare.gestor.dto.MaterialDto;
import com.gestionare.gestor.models.FacturasModel;
import com.gestionare.gestor.models.MaterialModel;
import com.gestionare.gestor.models.SesionModel;
import com.gestionare.gestor.services.MaterialService;

@CrossOrigin(origins = "*")
@Primary
@RestController
@RequestMapping("/api")
public class MaterialController {
	@Autowired
	private MaterialService materialService;

	@GetMapping("/material")
	public ResponseEntity<?> getAll() {
		List<MaterialModel> datos = this.materialService.getAll();
		return ResponseEntity.status(HttpStatus.OK).body(datos);
	}

	@GetMapping("/material/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") String id) {
		MaterialModel datos = this.materialService.findById(id);
		if (datos == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>() {
				{

					put("mensaje", "Recurso no encontrado");
				}
			});
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(this.materialService.findById(id));
		}

	}

	@PostMapping("/crear-material")
	public ResponseEntity<?> createMaterial(@RequestBody MaterialDto dto) {
		try {
			this.materialService.save(new MaterialModel(dto.getName(), dto.getSaleCost(), dto.getSupplierName()));
			return ResponseEntity.status(HttpStatus.CREATED).body(new HashMap<String, String>() {
				{
					put("mensaje", "Creado con exito");
				}
			});
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>() {
				{
					put("mensaje", "Ocurrió un error, compruebe que el producto no este creado");
				}
			});
		}

	}

	@PutMapping("/stock-material")
	public ResponseEntity<?> updateStockMaterial(@RequestBody MaterialDto dto) {
		MaterialModel dato = this.materialService.findById(dto.getId());
		dato.moveMaterial(dto.getQuantity());
		this.materialService.save(dato);
		return ResponseEntity.status(HttpStatus.CREATED).body(new HashMap<String, String>() {
			{
				put("mensaje", "Stock agregado con exito");
			}
		});

	}

	@PutMapping("/material/{id}")
	public ResponseEntity<?> updateMaterial(@RequestBody MaterialDto dto) {
		MaterialModel dato = this.materialService.findById(dto.getId());
		dato.setName(dto.getName());
		dato.setCost(dto.getSaleCost());
		dato.setSupplierName(dto.getSupplierName());
		this.materialService.save(dato);
		return ResponseEntity.status(HttpStatus.CREATED).body(new HashMap<String, String>() {
			{
				put("mensaje", "Editado con exito");
			}
		});
	}

	@DeleteMapping("/material/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") String id) {
		MaterialModel datos = this.materialService.findById(id);
		if (datos != null) {
			this.materialService.delete(id);
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
