package com.gestionare.gestor.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
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
	public ResponseEntity<?> getById(@PathVariable("id") String id) {
		PacienteModel datos = this.pacienteService.findById(id);
		if (datos == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>() {
				{

					put("mensaje", "Recurso no encontrado");
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
					put("mensaje", "Ocurrió un error");
				}
			});
		}
	}

	@PutMapping("/pacientes/{id}")
	public ResponseEntity<?> edit(@PathVariable("id") String id, @RequestBody PacienteDto dto) {
		PacienteModel datos = this.pacienteService.findById(id);
		if (datos != null) {
			datos.setBirthdate(dto.getBirthdate());
			datos.setCity(dto.getCity());
			datos.setEmail(dto.getEmail());
			datos.setName(dto.getName());
			datos.setNumber(dto.getNumber());
			datos.setProfesion(dto.getProfesion());
			this.pacienteService.save(datos);
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

	@DeleteMapping("/pacientes/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") String id) {
		PacienteModel datos = this.pacienteService.findById(id);

		if (datos != null) {
			if (datos.getImg() != null && datos.getImg().length() > 0) {
				if (Paths.get("uploads").resolve(datos.getImg()).toAbsolutePath().toFile().exists()
						&& Paths.get("uploads").resolve(datos.getImg()).toAbsolutePath().toFile().canRead()) {
					Paths.get("uploads").resolve(datos.getImg()).toAbsolutePath().toFile().delete();
				}

			}
			this.pacienteService.delete(id);
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

	@PostMapping("/pacientes/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") String id) {
		PacienteModel paciente = pacienteService.findById(id);
		if (!archivo.isEmpty()) {
			String nombreArchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename().replace(" ", "");
			Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();
			try {
				Files.copy(archivo.getInputStream(), rutaArchivo);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (paciente.getImg() != null && paciente.getImg().length() > 0) {
				if (Paths.get("uploads").resolve(paciente.getImg()).toAbsolutePath().toFile().exists()
						&& Paths.get("uploads").resolve(paciente.getImg()).toAbsolutePath().toFile().canRead()) {
					Paths.get("uploads").resolve(paciente.getImg()).toAbsolutePath().toFile().delete();
				}

			}
			paciente.setImg(nombreArchivo);
			this.pacienteService.save(paciente);

			return ResponseEntity.status(HttpStatus.CREATED).body(new HashMap<String, String>() {
				{

					put("mensaje", "Imagen subida con exito");
				}
			});
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>() {
				{

					put("mensaje", "Error");
				}
			});
		}

	}
}
