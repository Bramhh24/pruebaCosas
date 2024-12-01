package com.bramh.pruebaCosas.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bramh.pruebaCosas.Models.Cosa;

import java.util.List;

@Repository
public interface CosaRepository extends CrudRepository<Cosa, Long> {

    List<Cosa> findByPropietario(String propietario);
} 
