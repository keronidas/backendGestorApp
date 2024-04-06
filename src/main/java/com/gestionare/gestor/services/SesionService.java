package com.gestionare.gestor.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gestionare.gestor.models.SesionModel;
import com.gestionare.gestor.repository.ISesionRepository;

@Service
@Primary
public class SesionService {

	@Autowired
	private ISesionRepository repositorio;

	public List<SesionModel> getAll() {
		return this.repositorio.findAll(Sort.by("id").descending());
	}
}
