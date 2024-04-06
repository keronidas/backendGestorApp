package com.gestionare.gestor.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gestionare.gestor.models.PacienteModel;

public interface IPacienteRepository extends MongoRepository<PacienteModel, String>{

}
