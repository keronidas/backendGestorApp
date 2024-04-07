package com.gestionare.gestor.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gestionare.gestor.models.SalaTratamientoModel;
import com.gestionare.gestor.repository.ISalaTratamientosRepository;

@Service
@Primary
public class SalaTratamientoService {

	@Autowired
	private ISalaTratamientosRepository repositorio;

	public List<SalaTratamientoModel> getAll() {
		return this.repositorio.findAll(Sort.by("id").descending());
	}

	public SalaTratamientoModel findById(String id) {
		Optional<SalaTratamientoModel> optional = this.repositorio.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	public void save(SalaTratamientoModel modelo) {
		this.repositorio.save(modelo);
	}

	public void delete(String id) {
		this.repositorio.deleteById(id);
	}
}
