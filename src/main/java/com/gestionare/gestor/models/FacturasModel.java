package com.gestionare.gestor.models;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(value = "factura")
public class FacturasModel {

	@Id
	private String id;
	private String code;
	private Date date;
	private Boolean paid;
	private Float discount;
	private Float amount;
	private List<SesionModel> sesiones;

	public FacturasModel(Float amount, Float discount, Boolean paid, List<SesionModel> list) {
		super();
		this.amount = amount;
		this.discount = discount;
		this.paid = paid;
		this.sesiones = list;
		this.date = Date.from(Instant.now());
		for (SesionModel elemento : sesiones) {
			this.amount += elemento.getFinalPrice();
		}
	}

}
