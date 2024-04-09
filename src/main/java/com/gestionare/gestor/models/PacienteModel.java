package com.gestionare.gestor.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(value = "paciente")
public class PacienteModel {
	@Id
	private String id;
	private String name;
	private String profesion;
	private String email;
	private Date birthdate;
	private String city;
	private String number;
	private String img;

	public PacienteModel() {

	}

	public PacienteModel(String name, String profesion, String email, Date birthdate, String city, String number) {
		super();
		this.name = name;
		this.profesion = profesion;
		this.email = email;
		this.birthdate = birthdate;
		this.city = city;
		this.number = number;
	}

}
