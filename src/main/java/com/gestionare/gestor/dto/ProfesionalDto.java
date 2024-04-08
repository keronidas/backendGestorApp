package com.gestionare.gestor.dto;

import java.util.Date;
import java.util.List;

import com.gestionare.gestor.models.OficioModel;

import lombok.Data;

@Data
public class ProfesionalDto {
	private String id;
	private String name;
	private List<OficioModel> profesion;
	private String email;
	private Date birthdate;
	private String address;
	private String city;
	private String number;
	private Float salary;
}
