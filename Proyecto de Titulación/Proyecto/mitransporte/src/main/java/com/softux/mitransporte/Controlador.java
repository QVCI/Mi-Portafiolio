package com.softux.mitransporte;

import jakarta.transaction.Transactional;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;

@RestController
@RequestMapping("/api/anuncios")
public class Controlador
{
    private static final Logger logger = LoggerFactory.getLogger(Controlador.class);
    private List<Anuncio> listaAnuncios = new ArrayList<>();
    private ArrayList<String> listaEstacion = new ArrayList<>();
    private ArrayList<String> listaCausa = new ArrayList<>();

    @Autowired
    private MensajeRepository mensajeRepository;

   
    @PostMapping("/agregar")
    @Transactional
    public String agregarAnuncio(@RequestBody Anuncio anuncio) throws FileNotFoundException, IOException
    {
        try (BufferedReader lector = new BufferedReader(new FileReader("baneados.txt")))
        {
            String textos;
            while ((textos = lector.readLine()) != null)
            {
                if (textos.contains(anuncio.getUidUsuario()))
                {
                    System.out.println("La cuenta suspendida: " + anuncio.getUidUsuario());
                    return "Baneado";
                }
            }
        }
        try (BufferedReader reader = new BufferedReader(new FileReader("bloqueados.txt")))
        {
            String linea;

            while ((linea = reader.readLine()) != null)
            {
                if (linea.contains(anuncio.getUidUsuario()))
                {
                    System.out.println("La cuenta suspendida: " + anuncio.getUidUsuario());
                    return "Su cuenta fue suspendida por anuncios falsos";
                }
            }

            logger.info("Se ha recibido un anuncio: {}", anuncio);
            listaAnuncios.add(anuncio);

            String estacionActual = anuncio.getEstacion();
            String causaActual = anuncio.getCausa();

            listaEstacion.add(estacionActual);
            listaCausa.add(causaActual);

            logger.info("Anuncio: {}", anuncio);

            int frecuenciaEstacion = Collections.frequency(listaEstacion, estacionActual);
            int frecuenciaCausa = Collections.frequency(listaCausa, causaActual);

            if (frecuenciaEstacion >= 3 && frecuenciaCausa >= 3)
            {
                LocalTime horaActual = LocalTime.now();
                Mensaje mensaje = new Mensaje();

                mensaje.setEstacion(estacionActual);
                mensaje.setCausa(causaActual);
                mensaje.setHoraPublicacion(horaActual);
                mensaje.setUid(1);
                mensajeRepository.save(mensaje);
                mensajeRepository.flush();
                logger.info("Se guardo el mensaje: /n{}", mensaje);
                listaEstacion.removeAll(Collections.singleton(estacionActual));
                listaCausa.removeAll(Collections.singleton(causaActual));
                listaAnuncios.removeIf(a -> a.getEstacion().equals(estacionActual));

                return "Reporte publicado";
            }
        }


        return "Reporte publicado";
    }

    @GetMapping("/consultar")
    public ResponseEntity<?> consultarMensajes()
    {
        List<Mensaje> mensajes = mensajeRepository.findAll();

        if (mensajes.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.OK).body("Sin novedades");
        }
        else
        {
            return ResponseEntity.status(HttpStatus.OK).body(mensajes);
        }
    }



    @Scheduled(cron = "0 0 * * * *")
    public void procesarMensajesCadaHora()
    {
        LocalTime horaActual = LocalTime.now();
        LocalTime horaLimite = horaActual.minusHours(1);

        logger.info("Mensaje del servidor: ","Se depuró la base de datos a las: " + horaActual);

        List<Mensaje> mensajes = mensajeRepository.findByHoraPublicacionBefore(horaLimite);

        
        List<Anuncio> anunciosParaEliminar = listaAnuncios.stream()
                .filter(anuncio -> anuncio.getHoraEnvio().isBefore(horaLimite))
                .collect(Collectors.toList());

        for (Anuncio anuncio : anunciosParaEliminar)
        {
            listaAnuncios.remove(anuncio);
        }

    
        for (Mensaje mensaje : mensajes)
        {
            guardarEnArchivo(mensaje);
            mensajeRepository.delete(mensaje);
        }
    }

    private void guardarEnArchivo(Mensaje mensaje)
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("mensajes_guardados.txt", true)))
        {
            writer.write("Estacion: " + mensaje.getEstacion() + ", Causa: " + mensaje.getCausa()
                    + ", Hora Publicacion: " + mensaje.getHoraPublicacion() + ", UID: " + mensaje.getUid());
            writer.newLine();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
