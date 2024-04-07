package com.gestionare.gestor.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(value="oficios")
public class OficioModel {
	@Id
	private String id;
	private String name;
	private Float minIncome;

}
