package com.gestionare.gestor.dto;

import java.util.Date;


import lombok.Data;

@Data
public class ProfesionalDto {
	private String id;
	private String name;
	private String profesion;
	private String email;
	private Date birthdate;
	private String address;
	private String city;
	private String number;
	private Float salary;
	private Byte[] img;
}
