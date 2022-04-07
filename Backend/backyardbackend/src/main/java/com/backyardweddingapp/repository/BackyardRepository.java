package com.backyardweddingapp.repository;

import com.backyardweddingapp.entity.Backyard;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BackyardRepository extends CrudRepository<Backyard, Integer>{
  
}
