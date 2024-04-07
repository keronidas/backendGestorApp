package com.gestionare.gestor.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gestionare.gestor.models.ProfesionalModel;

public interface IProfesionalRepository extends MongoRepository<ProfesionalModel, String>{

}
