package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.demo.model.Fournisseur;

@RepositoryRestResource 
public interface FournisseurRep extends JpaRepository<Fournisseur,String> {

}
