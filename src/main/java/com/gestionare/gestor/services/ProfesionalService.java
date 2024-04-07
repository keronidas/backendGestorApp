package com.gestionare.gestor.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gestionare.gestor.models.ProfesionalModel;
import com.gestionare.gestor.repository.IProfesionalRepository;

@Service
@Primary
public class ProfesionalService {

	@Autowired
	private IProfesionalRepository repositorio;
	
	public List<ProfesionalModel> getAll() {
		return this.repositorio.findAll(Sort.by("id").descending());
	}
	public ProfesionalModel findById(String id) {
		Optional<ProfesionalModel> optional = this.repositorio.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	public void save(ProfesionalModel modelo) {
		this.repositorio.save(modelo);
	}
}
