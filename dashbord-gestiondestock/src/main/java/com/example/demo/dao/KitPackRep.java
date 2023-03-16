package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.KitPack;

public interface KitPackRep extends JpaRepository <KitPack,String> {

}
