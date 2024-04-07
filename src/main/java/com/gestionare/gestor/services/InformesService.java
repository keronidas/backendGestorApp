package com.gestionare.gestor.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gestionare.gestor.models.InformesModel;
import com.gestionare.gestor.repository.IInformesRepository;

@Service
@Primary
public class InformesService {
	@Autowired
	private IInformesRepository repositorio;
	
	public List<InformesModel> getAll() {
		return this.repositorio.findAll(Sort.by("id").descending());
	}

	public InformesModel findById(String id) {
		Optional<InformesModel> optional = this.repositorio.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	public void save(InformesModel modelo) {
		this.repositorio.save(modelo);
	}

	public void delete(String id) {
		this.repositorio.deleteById(id);
	}
}
