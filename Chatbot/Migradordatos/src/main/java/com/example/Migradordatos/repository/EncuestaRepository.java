package com.example.Migradordatos.repository;

import com.example.Migradordatos.model.Encuesta;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncuestaRepository extends JpaRepository<Encuesta, Integer> {

 
    
}
