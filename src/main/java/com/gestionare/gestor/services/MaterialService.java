package com.gestionare.gestor.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gestionare.gestor.models.MaterialModel;
import com.gestionare.gestor.repository.IMaterialRepository;

@Service
@Primary
public class MaterialService {

	@Autowired
	private IMaterialRepository repositorio;

	public List<MaterialModel> getAll() {
		return this.repositorio.findAll(Sort.by("id").descending());
	}

	public MaterialModel findById(String id) {
		Optional<MaterialModel> optional = this.repositorio.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	public void save(MaterialModel modelo) {
		this.repositorio.save(modelo);
	}

	public void delete(String id) {
		this.repositorio.deleteById(id);
	}
}
