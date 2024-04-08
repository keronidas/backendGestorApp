package com.gestionare.gestor.dto;



import com.gestionare.gestor.models.SesionModel;

import lombok.Data;

@Data
public class InformesDto {

	private String title;
	private String description;
	private SesionModel sesion;
}
