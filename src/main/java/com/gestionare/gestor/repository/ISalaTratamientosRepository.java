package com.gestionare.gestor.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gestionare.gestor.models.SalaTratamientoModel;

public interface ISalaTratamientosRepository extends MongoRepository<SalaTratamientoModel, String> {

}
