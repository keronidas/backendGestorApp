package com.gestionare.gestor.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class ProfesionalDto {
	private String id;
	private String name;
	private List<String> profesion;
	private String email;
	private Date birthdate;
	private String adress;
	private String city;
	private String number;
	private Float salary;
}
