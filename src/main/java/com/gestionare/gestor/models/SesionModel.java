package com.gestionare.gestor.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(value="sesion")
public class SesionModel {
	@Id
	private String id;
	private Date fecha;
	private String motivo;
	private String diagnostico;
	private String tratamiento;
	private Float precio;
	private Float descuento;
	
//	Relaciones
	@DBRef
	private PacienteModel pacienteId;

}
