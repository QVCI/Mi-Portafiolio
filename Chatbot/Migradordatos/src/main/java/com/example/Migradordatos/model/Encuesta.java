package com.example.Migradordatos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

@Entity
@Table(name = "ENCUESTA")
public class Encuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDUSUARIO")
    private Integer idUsuario;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "EDAD")
    private Integer edad;

    @Column(name = "SEXO")
    private String sexo;

    @Column(name = "SITUACIONSENTIMENTAL")
    private String situacionSentimental;

    @Column(name = "ESTUDIOS")
    private String estudios;

    @Column(name = "OCUPACION")
    private String ocupacion;

    @Column(name = "ENTIDAD")
    private String entidad;

    @Column(name = "MUNICIPIO")
    private String municipio;

    @Column(name = "TEXTO")
    private String texto;

    @Column(name = "P1")
    private String p1;

    @Column(name = "P2")
    private String p2;

    @Column(name = "P3")
    private String p3;

    @Column(name = "P4")
    private String p4;

    @Column(name = "P5")
    private String p5;

    @Column(name = "P6")
    private String p6;

    @Column(name = "P7")
    private String p7;

    @Column(name = "P8")
    private String p8;

    @Column(name = "P9")
    private String p9;

    @Column(name = "P10")
    private String p10;

    @Column(name = "P11")
    private String p11;

    @Column(name = "P12")
    private String p12;

    @Column(name = "P13")
    private String p13;

    @Column(name = "P14")
    private String p14;

    @Column(name = "P15")
    private String p15;

    @Column(name = "P16")
    private String p16;

    @Column(name = "P17")
    private String p17;

    @Column(name = "P18")
    private String p18;

    @Column(name = "P19")
    private String p19;

    @Column(name = "P20")
    private String p20;

    @Column(name = "P21")
    private String p21;

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getSituacionSentimental() {
        return situacionSentimental;
    }

    public void setSituacionSentimental(String situacionSentimental) {
        this.situacionSentimental = situacionSentimental;
    }

    public String getEstudios() {
        return estudios;
    }

    public void setEstudios(String estudios) {
        this.estudios = estudios;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getP1() {
        return p1;
    }

    public void setP1(String p1) {
        this.p1 = p1;
    }

    public String getP2() {
        return p2;
    }

    public void setP2(String p2) {
        this.p2 = p2;
    }

    public String getP3() {
        return p3;
    }

    public void setP3(String p3) {
        this.p3 = p3;
    }

    public String getP4() {
        return p4;
    }

    public void setP4(String p4) {
        this.p4 = p4;
    }

    public String getP5() {
        return p5;
    }

    public void setP5(String p5) {
        this.p5 = p5;
    }

    public String getP6() {
        return p6;
    }

    public void setP6(String p6) {
        this.p6 = p6;
    }

    public String getP7() {
        return p7;
    }

    public void setP7(String p7) {
        this.p7 = p7;
    }

    public String getP8() {
        return p8;
    }

    public void setP8(String p8) {
        this.p8 = p8;
    }

    public String getP9() {
        return p9;
    }

    public void setP9(String p9) {
        this.p9 = p9;
    }

    public String getP10() {
        return p10;
    }

    public void setP10(String p10) {
        this.p10 = p10;
    }

    public String getP11() {
        return p11;
    }

    public void setP11(String p11) {
        this.p11 = p11;
    }

    public String getP12() {
        return p12;
    }

    public void setP12(String p12) {
        this.p12 = p12;
    }

    public String getP13() {
        return p13;
    }

    public void setP13(String p13) {
        this.p13 = p13;
    }

    public String getP14() {
        return p14;
    }

    public void setP14(String p14) {
        this.p14 = p14;
    }

    public String getP15() {
        return p15;
    }

    public void setP15(String p15) {
        this.p15 = p15;
    }

    public String getP16() {
        return p16;
    }

    public void setP16(String p16) {
        this.p16 = p16;
    }

    public String getP17() {
        return p17;
    }

    public void setP17(String p17) {
        this.p17 = p17;
    }

    public String getP18() {
        return p18;
    }

    public void setP18(String p18) {
        this.p18 = p18;
    }

    public String getP19() {
        return p19;
    }

    public void setP19(String p19) {
        this.p19 = p19;
    }

    public String getP20() {
        return p20;
    }

    public void setP20(String p20) {
        this.p20 = p20;
    }

    public String getP21() {
        return p21;
    }

    public void setP21(String p21) {
        this.p21 = p21;
    }
    
}