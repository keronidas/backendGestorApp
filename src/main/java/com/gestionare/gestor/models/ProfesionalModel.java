package com.gestionare.gestor.models;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(value = "profesional")
@Data
public class ProfesionalModel {

	@Id
	private String id;
	private String name;
	private List<String> profesion;
	private String email;
	private Date birthdate;
	private String city;
	private String adress;
	private String number;
	private Float salary;
	private String fotografia;
	private Date creation_date;

	public ProfesionalModel() {
		this.creation_date = Date.from(Instant.now());
	}
	
	public ProfesionalModel(String name, List<String> profesion, String email, Date birthdate, String city,
			String adress, String number, Float salary) {
		super();
		this.name = name;
		this.profesion = profesion;
		this.email = email;
		this.birthdate = birthdate;
		this.city = city;
		this.adress = adress;
		this.number = number;
		this.salary = salary;
		this.creation_date = Date.from(Instant.now());
	}

}
