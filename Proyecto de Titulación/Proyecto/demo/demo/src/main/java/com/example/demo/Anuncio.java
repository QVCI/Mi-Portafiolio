package com.example.demo;

import java.time.LocalTime;

public class Anuncio 
{
    private String estacion;
    private String causa;
    private LocalTime horaEnvio;
    private String uidUsuario;

    public String getEstacion() {
        return estacion;
    }

    public void setEstacion(String estacion) {
        this.estacion = estacion;
    }

    public String getCausa() {
        return causa;
    }

    public void setCausa(String causa) {
        this.causa = causa;
    }

    public LocalTime getHoraEnvio() {
        return horaEnvio;
    }

    public void setHoraEnvio(LocalTime horaEnvio) {
        this.horaEnvio = horaEnvio;
    }

    public String getUidUsuario() {
        return uidUsuario;
    }

    public void setUidUsuario(String uidUsuario) {
        this.uidUsuario = uidUsuario;
    }
     @Override
    public String toString() {
        return "\n{" +
                "\nestacion: '" + estacion + '\'' +
                ", \ncausa: '" + causa + '\'' +
                ", \nhoraEnvio: " + horaEnvio +
                ", \nuidUsuario: " + uidUsuario +"\n" +
                '}';
    }
    
}
