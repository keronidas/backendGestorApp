package com.gestionare.gestor.models;

import java.time.Instant;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(value="informes")
public class InformesModel {
	
	@Id
	private String id;
	private String title;
	private String description;
	private Date date;
	
	@DBRef
	private PacienteModel paciente;
	@DBRef
	private SesionModel sesion;
	public InformesModel(String title, String description, PacienteModel paciente, SesionModel sesion) {
		super();
		this.title = title;
		this.description = description;
		this.paciente = paciente;
		this.sesion = sesion;
		this.date=Date.from(Instant.now())
;	}
	
	
	

}
