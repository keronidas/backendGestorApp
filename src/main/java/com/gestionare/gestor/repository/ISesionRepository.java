package com.gestionare.gestor.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gestionare.gestor.models.SesionModel;

public interface ISesionRepository  extends MongoRepository<SesionModel, String>{

}
