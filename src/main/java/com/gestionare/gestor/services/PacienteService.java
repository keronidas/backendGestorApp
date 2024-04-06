package com.gestionare.gestor.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gestionare.gestor.models.PacienteModel;
import com.gestionare.gestor.repository.IPacienteRepository;

@Service
@Primary
public class PacienteService {
	@Autowired
	private IPacienteRepository repositorio;

	public List<PacienteModel> getAll() {
		return this.repositorio.findAll(Sort.by("id").descending());
	}

	public PacienteModel findById(String id) {
		Optional<PacienteModel> optional = this.repositorio.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	public void save(PacienteModel modelo) {
		this.repositorio.save(modelo);
	}

}
