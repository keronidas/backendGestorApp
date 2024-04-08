package com.gestionare.gestor.models;

import java.time.Instant;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(value = "material")
public class MaterialModel {
	@Id
	private String id;
	private String code;
	@Indexed(unique = true)
	private String name;
	private Float cost;
	private String supplierName;
	private Date buyDate;
	private Integer quantity;

	public MaterialModel(String name, Float cost, String supplierName) {
		this.code = java.util.UUID.randomUUID().toString();
		this.name = name;
		this.cost = cost;
		this.quantity=0;
		this.supplierName = supplierName;
		this.buyDate = Date.from(Instant.now());
	}

	public Integer moveMaterial(Integer quantity) {
		this.quantity += quantity;
		return getQuantity();

	}

}
