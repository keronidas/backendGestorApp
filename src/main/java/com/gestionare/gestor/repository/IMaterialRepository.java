package com.gestionare.gestor.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gestionare.gestor.models.MaterialModel;

public interface IMaterialRepository extends MongoRepository<MaterialModel, String> {

}
