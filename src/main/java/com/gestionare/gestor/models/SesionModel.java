package com.gestionare.gestor.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.gestionare.gestor.services.SalaTratamientoService;

import lombok.Data;

@Data
@Document(value = "sesion")
public class SesionModel {
	@Id
	private String id;
	private Date fecha;
	private String motivo;
	private String diagnostico;
	private String tratamiento;
	private Float precio;
	private Float descuento;
	private Float finalPrice;

//	Relaciones
	@DBRef
	private PacienteModel paciente;
	@DBRef
	private ProfesionalModel profesional;
	@DBRef
	private SalaTratamientoModel salas;

	public SesionModel() {

	}

	public SesionModel(Date fecha, String motivo, String diagnostico, String tratamiento, Float precio, Float descuento,
			PacienteModel paciente, ProfesionalModel profesional, SalaTratamientoModel sala) {
		super();
		this.fecha = fecha;
		this.motivo = motivo;
		this.diagnostico = diagnostico;
		this.tratamiento = tratamiento;
		this.precio = precio;
		if (descuento > 50) {
			descuento = 50F;
		}
		this.descuento = descuento;
		this.paciente = paciente;
		this.profesional = profesional;
		this.salas=sala;
		this.finalPrice = precio * (100 - descuento) / 100;
	}

}
