 package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.demo.model.EtatKit;

@RepositoryRestResource
public interface EtatkitRep extends JpaRepository<EtatKit,String> {

}
