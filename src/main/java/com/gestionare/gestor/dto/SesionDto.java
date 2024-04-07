package com.gestionare.gestor.dto;

import java.util.Date;

import com.gestionare.gestor.models.PacienteModel;
import com.gestionare.gestor.models.ProfesionalModel;

import lombok.Data;

@Data
public class SesionDto {
	
	private Date fecha;
	private String motivo;
	private String diagnostico;
	private String tratamiento;
	private Float precio;
	private Float descuento;
	private PacienteModel paciente;
	private ProfesionalModel profesional;
}
