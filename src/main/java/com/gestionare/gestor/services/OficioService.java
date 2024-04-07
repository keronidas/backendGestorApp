package com.gestionare.gestor.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gestionare.gestor.models.OficioModel;
import com.gestionare.gestor.repository.IOficioRepository;

@Service
@Primary
public class OficioService {

	@Autowired
	private IOficioRepository repositorio;
	
	public List<OficioModel> getAll() {
		return this.repositorio.findAll(Sort.by("id").descending());
	}

	public OficioModel findById(String id) {
		Optional<OficioModel> optional = this.repositorio.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	public void save(OficioModel modelo) {
		this.repositorio.save(modelo);
	}

	public void delete(String id) {
		this.repositorio.deleteById(id);
	}
}
