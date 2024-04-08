package com.gestionare.gestor.models;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
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

	@DBRef
	private List<SesionModel> sesiones;

	public FacturasModel(Float discount, Boolean paid, List<SesionModel> sesiones) {
		super();
		this.code = java.util.UUID.randomUUID().toString();
		if (discount > 50) {
			discount = 50F;
		}
		this.discount = discount;
		this.paid = paid;
		this.sesiones = sesiones;
		this.date = Date.from(Instant.now());
		calcularImporteTotal();
	}

	public void calcularImporteTotal() {
		this.amount = 0F;
		for (SesionModel elemento : this.sesiones) {
			this.amount += elemento.getFinalPrice();
		}
	}

}
