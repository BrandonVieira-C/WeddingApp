package com.backyardweddingapp.repository;

import com.backyardweddingapp.entity.Partner;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerRepository extends CrudRepository<Partner, Integer> {
  
}
