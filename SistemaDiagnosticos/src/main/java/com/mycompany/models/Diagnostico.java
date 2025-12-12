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
public class Diagnostico {

    private String NombrePaciente;
    private String EnfermedadDetectada;
    private String Recomendaciones;
    private String Categoria;
    private List<String> sintomasPaciente;

    public Diagnostico() {
        this.sintomasPaciente = new ArrayList<>();
    }

    public Diagnostico(String NombrePaciente, String EnfermedadDetectada, String Recomendaciones, String Categoria) {
        this.NombrePaciente = NombrePaciente;
        this.EnfermedadDetectada = EnfermedadDetectada;
        this.Recomendaciones = Recomendaciones;
        this.Categoria = Categoria;
        this.sintomasPaciente = new ArrayList<>();
    }

    public String getNombrePaciente() {
        return NombrePaciente;
    }

    public void setNombrePaciente(String NombrePaciente) {
        this.NombrePaciente = NombrePaciente;
    }

    public String getEnfermedadDetectada() {
        return EnfermedadDetectada;
    }

    public void setEnfermedadDetectada(String EnfermedadDetectada) {
        this.EnfermedadDetectada = EnfermedadDetectada;
    }

    public String getRecomendaciones() {
        return Recomendaciones;
    }

    public void setRecomendaciones(String Recomendaciones) {
        this.Recomendaciones = Recomendaciones;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String Categoria) {
        this.Categoria = Categoria;
    }

    public List<String> getSintomasPaciente() {
        return sintomasPaciente;
    }

    public void setSintomasPaciente(List<String> sintomasPaciente) {
        this.sintomasPaciente = sintomasPaciente;
    }

}
