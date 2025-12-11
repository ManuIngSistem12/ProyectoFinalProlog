/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.sistemadiagnosticos;

import com.mycompany.models.Enfermedad;
import com.mycompany.models.EnfermedadDAO;
import com.mycompany.models.PacienteDAO;
import com.mycompany.models.Sintoma;
import com.mycompany.models.SintomaDAO;
import com.mycompany.prolog.FactsBuilder;
import com.mycompany.prolog.PrologQueryExecutor;
import com.mycompany.views.VentanaPrincipal;
import java.sql.SQLException;
import java.util.List;
import org.jpl7.Query;


/**
 *
 * @author Manuel
 */
public class SistemaDiagnosticos {

    public static void main(String[] args) {
        String rules = "consult('prolog.pl')";       
        Query q = new Query(rules);
        if (q.hasSolution()) {
            System.out.println("Archivo Prolog cargado correctamente.");
        }
        System.out.println("");
        System.out.println("=============================CREANDO HECHOS DINAMICOS===========================");
        FactsBuilder.construirFactsEnfermedad();
        VentanaPrincipal ventana = new VentanaPrincipal();
        ventana.setVisible(true);
//        System.out.println("");
//        System.out.println("========================CONSULTA [ enfermedad(Nom,sintomas(Sin),categoria(viral),recomendaciones(Rec)) ]=============");
//        List<Enfermedad> lista = PrologQueryExecutor.getEnfermedades("enfermedad(Nom,sintomas(Sin),categoria(viral),recomendaciones(Rec))");
//        for(Enfermedad e : lista){
//            System.out.println("Nombre: " + e.getNombre());
//            System.out.println("Sintomas: " + e.getSintomas());
//            System.out.println("Categoria: " + e.getCategoria());
//            System.out.println("Recomendaciones: " + e.getRecomendaciones());
//            System.out.println("");
//            System.out.println("");
//        }      
    }   
}

