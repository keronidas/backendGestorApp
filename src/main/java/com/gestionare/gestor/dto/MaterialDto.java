package com.gestionare.gestor.dto;



import lombok.Data;

@Data
public class MaterialDto {
	private String id;
	private String name;
	private String code;
	private Float saleCost;
	private String supplierName;
	private Integer quantity;
}
