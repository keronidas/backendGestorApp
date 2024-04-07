package com.gestionare.gestor.dto;

import java.util.List;

import com.gestionare.gestor.models.SesionModel;

import lombok.Data;

@Data
public class FacturasDto {

	private Float amount;
	private Float discount;
	private Boolean paid;
	private List<SesionModel> sesiones;
}
