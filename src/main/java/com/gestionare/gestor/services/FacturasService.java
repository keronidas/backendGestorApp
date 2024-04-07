package com.gestionare.gestor.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gestionare.gestor.models.FacturasModel;
import com.gestionare.gestor.repository.IFacturasRepository;

@Service
@Primary
public class FacturasService {
	@Autowired
	private IFacturasRepository repositorio;
	
	public List<FacturasModel> getAll() {
		return this.repositorio.findAll(Sort.by("id").descending());
	}

	public FacturasModel findById(String id) {
		Optional<FacturasModel> optional = this.repositorio.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	public void save(FacturasModel modelo) {
		this.repositorio.save(modelo);
	}

	public void delete(String id) {
		this.repositorio.deleteById(id);
	}

}
