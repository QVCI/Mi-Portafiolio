package com.example.Migradordatos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.Migradordatos.model.Encuesta;
import com.example.Migradordatos.repository.EncuestaRepository;
import com.example.Migradordatos.service.EncuestaService;
import com.example.Migradordatos.service.FirebaseService;
import com.example.Migradordatos.service.HistoryService;

@Service
public class DataMigrationService 
{

    @Autowired
    private EncuestaRepository encuestaRepository;

    @Autowired
    private FirebaseService firebaseService;

    @Autowired
    private EncuestaService encuestaService;
    
    @Autowired
    private HistoryService historyService;

    @Scheduled(cron = "0 * * * * ?")

    public void migrateData() 
    {
        List<Encuesta> encuestas = encuestaRepository.findAll();
        if (encuestas.isEmpty()) 
        {
            System.out.println("Sin Datos para migrar");
        }
        else 
        {
            System.out.println("Iniciando Migrado de datos...");
            int totalEncuestas = encuestas.size();
            int contador = 1;
            for (Encuesta encuesta : encuestas) 
            {
                System.out.println("Dato: " + totalEncuestas + " de: " + contador);
                System.out.println("----------- Firebase -----------");

                Map<String, Object> additionalData = new HashMap<>();

                boolean resultado = firebaseService.GuardarEnFirebase(
                        encuesta.getNombre(),
                        encuesta.getSituacionSentimental(),
                        encuesta.getEdad(),
                        encuesta.getSexo(),
                        encuesta.getOcupacion(),
                        encuesta.getEstudios(),
                        encuesta.getEntidad(),
                        encuesta.getMunicipio(),
                        encuesta.getP1(),
                        encuesta.getP2(),
                        encuesta.getP3(),
                        encuesta.getP4(),
                        encuesta.getP5(),
                        encuesta.getP6(),
                        encuesta.getP7(),
                        encuesta.getP8(),
                        encuesta.getP9(),
                        encuesta.getP10(),
                        encuesta.getP11(),
                        encuesta.getP12(),
                        encuesta.getP13(),
                        encuesta.getP14(),
                        encuesta.getP15(),
                        encuesta.getP16(),
                        encuesta.getP17(),
                        encuesta.getP18(),
                        encuesta.getP19(),
                        encuesta.getP20(),
                        encuesta.getP21(),
                        encuesta.getTexto(),
                        additionalData
                );

                
                if (resultado) 
                {
                    System.out.println("Datos subidos con exito a Firebase");
                    System.out.println("----------- MySQL -----------");
                    boolean respuesta = encuestaService.eliminarSQL(encuesta.getIdUsuario());
                    if (respuesta) 
                    {
                        System.out.println("Eliminado de SQL con Exito");
                    }
                    else
                    {
                        System.out.println("No encontrado en SQL");
                    }

                } 
                else 
                {
                    System.out.println("Error al migrar datos");
                }
                contador++;
            }
        }
        /* 
        System.out.println("----------- MySQL Hystory -----------");
        boolean existehistory = historyService.ChecaContenido();
        
        if(existehistory)
        {
            boolean SeEliminoHistory = historyService.VaciarTabla();
            if(SeEliminoHistory)
            {
                System.out.println("Historial Eliminado con Exito");
            }
            
        }   */     
    }
}
