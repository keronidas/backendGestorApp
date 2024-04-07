package com.gestionare.gestor.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gestionare.gestor.models.OficioModel;

public interface IOficioRepository extends MongoRepository<OficioModel, String> {

}
