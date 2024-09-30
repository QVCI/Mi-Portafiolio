package com.example.Migradordatos.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;

@Service
public class FirebaseService 
{

    private final Firestore db;

    @Autowired
    public FirebaseService(Firestore db) 
    {
        this.db = db;
    }

    public Boolean GuardarEnFirebase(String Nombre, String EstadoCivil, int Edad, String Sexo, String Ocupacion, String Educacion, String Entidad, String Municipio,
                                     String p1, String p2, String p3, String p4, String p5, String p6, String p7, String p8, String p9, String p10, String p11, 
                                     String p12, String p13, String p14, String p15, String p16, String p17, String p18, String p19, String p20, String p21, 
                                     String Texto, Map<String, Object> data) 
    {

        LocalDate fecha = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaformateada = fecha.format(formatter);

        int calificacion = 0;
        calificacion += p1.charAt(0) - '0';
        calificacion += p2.charAt(0) - '0';
        calificacion += p3.charAt(0) - '0';
        calificacion += p4.charAt(0) - '0';
        calificacion += p5.charAt(0) - '0';
        calificacion += p6.charAt(0) - '0';
        calificacion += p7.charAt(0) - '0';
        calificacion += p8.charAt(0) - '0';
        calificacion += p9.charAt(0) - '0';
        calificacion += p10.charAt(0) - '0';
        calificacion += p11.charAt(0) - '0';
        calificacion += p12.charAt(0) - '0';
        calificacion += p13.charAt(0) - '0';
        calificacion += p14.charAt(0) - '0';
        calificacion += p15.charAt(0) - '0';
        calificacion += p16.charAt(0) - '0';
        calificacion += p17.charAt(0) - '0';
        calificacion += p18.charAt(0) - '0';
        calificacion += p19.charAt(0) - '0';
        calificacion += p20.charAt(0) - '0';
        calificacion += p21.charAt(0) - '0';

        DocumentReference documentRef;
        


        Map<String, Object> documentData = new HashMap<>();
        documentData.put("Nombre", Nombre);
        //datos omitidos
       

     
        if (data != null) 
        {
            documentData.putAll(data);
        }

       
        ApiFuture<WriteResult> future = documentRef.set(documentData);

        try 
        {
            future.get();
            return true; 
        } catch (InterruptedException | ExecutionException e)
        {
            e.printStackTrace();
            return false; 
        }
    }
}
