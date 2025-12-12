/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prolog;

import com.mycompany.models.Enfermedad;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.jpl7.Query;
import org.jpl7.Term;



/**
 *
 * @author Manuel
 */
public class PrologQueryExecutor {
    public static void createDynamicFact(String fact){
        Query q = new Query(fact);
        if(q.hasSolution()){
            System.out.println("Hecho dinamico Agregado:" + fact);
        }        
    }
    
    public static List<Enfermedad> getEnfermedades(String rule){
        List<Enfermedad> lista = new ArrayList<>();
        Query q = new Query(rule);
        
        while(q.hasMoreSolutions()){
            Map<String, Term> sol = q.nextSolution();
            
            String nombre = null;
            Term nombreT = sol.get("Nom"); 
            if (nombreT != null) {
                nombre = nombreT.toString();
            }
            
            List<String> sintomas = null;
            Term sintomasT = sol.get("Sin");
            if (sintomasT != null) {
                sintomas = pListToJList(sintomasT);
            }
            
            String categoria = null;
            Term categoriaT = sol.get("Cat");
            if (categoriaT != null) {
                categoria = categoriaT.toString();
            }
            String recomendaciones = null;
            Term recomendacionesT = sol.get("Rec");
            if (recomendacionesT != null) {
                recomendaciones = recomendacionesT.toString();
            }
            Enfermedad e = new Enfermedad(nombre, recomendaciones, categoria);
            e.setSintomas(sintomas);
            lista.add(e);
            
        }
        q.close();
        return lista;
    }
    
    public static Enfermedad getEnfermedad(String rule){
        Query q = new Query(rule);
        Enfermedad e = new Enfermedad();
        while(q.hasMoreSolutions()){
            Map<String, Term> sol = q.nextSolution();
            
            String nombre = null;
            Term nombreT = sol.get("Nom"); 
            if (nombreT != null) {
                nombre = nombreT.toString();
            }
            
            List<String> sintomas = null;
            Term sintomasT = sol.get("Sin");
            if (sintomasT != null) {
                sintomas = pListToJList(sintomasT);
            }
            
            String categoria = null;
            Term categoriaT = sol.get("Cat");
            if (categoriaT != null) {
                categoria = categoriaT.toString();
            }
            String recomendaciones = null;
            Term recomendacionesT = sol.get("Rec");
            if (recomendacionesT != null) {
                recomendaciones = recomendacionesT.toString();
            }
            e.setNombre(nombre);
            e.setCategoria(categoria);
            e.setRecomendaciones(recomendaciones);
            e.setSintomas(sintomas);
            
        }
        q.close();
        return e;
    }
    
    private static List<String> pListToJList(Term t) {
    List<String> lista = new ArrayList<>();
    Term list = t;

    while (list.isListPair()) {
        Term head = list.arg(1);   // primer elemento
        lista.add(head.toString());
        list = list.arg(2);        // resto de la lista
    }

    return lista;
}

}
