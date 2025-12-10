/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Manuel
 */
public class Enfermedad {
    
    private String nombre;
    private String recomendaciones;
    private String categoria;
    private List<String> sintomas;

    public Enfermedad(String nombre, String recomendaciones, String categoria){
        this.nombre = nombre;
        this.recomendaciones = recomendaciones;
        this.categoria = categoria;
        this.sintomas = new ArrayList<>();
    }

    public Enfermedad() {
        this.sintomas = new ArrayList<>();
    }
    
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRecomendaciones() {
        return recomendaciones;
    }

    public void setRecomendaciones(String recomendaciones) {
        this.recomendaciones = recomendaciones;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public List<String> getSintomas() {
        return sintomas;
    }

    public void setSintomas(List<String> sintomas) {
        this.sintomas = sintomas;
    }
    
    
    
}
