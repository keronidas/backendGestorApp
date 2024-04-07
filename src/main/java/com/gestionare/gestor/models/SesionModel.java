package com.gestionare.gestor.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

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
	private PacienteModel pacienteId;
	@DBRef
	private ProfesionalModel profesionalId;

	public SesionModel(Date fecha, String motivo, String diagnostico, String tratamiento, Float precio, Float descuento,
			PacienteModel pacienteId, ProfesionalModel profesionalId) {
		super();
		this.fecha = fecha;
		this.motivo = motivo;
		this.diagnostico = diagnostico;
		this.tratamiento = tratamiento;
		this.precio = precio;
		this.descuento = descuento;
		this.pacienteId = pacienteId;
		this.profesionalId = profesionalId;
		this.finalPrice = precio * (100 - descuento) / 100;
	}

}
