package com.example.Migradordatos.service;

import com.example.Migradordatos.repository.EncuestaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EncuestaService {

    @Autowired
    private EncuestaRepository encuestaRepository;

    public boolean eliminarSQL(Integer id)
    {
        if (encuestaRepository.existsById(id)) 
        {
            encuestaRepository.deleteById(id);
            return true;
            
        } 
        else 
        {
            System.out.println("Error al eliminar, encuesta no encontrada");
            return false;
        }
    }
}
