package com.gestionare.gestor.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gestionare.gestor.models.FacturasModel;

public interface IFacturasRepository extends MongoRepository<FacturasModel, String> {

}
