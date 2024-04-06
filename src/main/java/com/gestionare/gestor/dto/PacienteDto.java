package com.gestionare.gestor.dto;

import java.util.Date;

import lombok.Data;

@Data
public class PacienteDto {

	private String name;
	private String profesion;
	private String email;
	private Date birthdate;
	private String city;
	private String number;
}
