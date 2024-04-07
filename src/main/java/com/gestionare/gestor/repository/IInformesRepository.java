package com.gestionare.gestor.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gestionare.gestor.models.InformesModel;

public interface IInformesRepository extends MongoRepository<InformesModel, String>{

}
