package com.gestionare.gestor.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(value="sala-tratamiento")
public class SalaTratamientoModel {
	@Id
	private String id;
	private String name;
	private String description;
	private String img;
	


	public SalaTratamientoModel(String name, String description) {
		super();
		this.name = name;
		this.description = description;

	}
	
	

}
