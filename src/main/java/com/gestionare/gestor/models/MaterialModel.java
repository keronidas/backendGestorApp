package com.gestionare.gestor.models;

import java.time.Instant;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(value = "material")
public class MaterialModel {
	@Id
	private String id;
	private String name;
	private Float cost;
	private String supplierName;
	private Date buyDate;
	private Integer quantity;
	
	public MaterialModel(String name, Float cost, String supplierName, Integer quantity) {
		super();
		this.name = name;
		this.cost = cost;
		this.supplierName = supplierName;
		this.quantity = quantity;
		this.buyDate=Date.from(Instant.now());
	}
	
	
	
	
	
	

}
