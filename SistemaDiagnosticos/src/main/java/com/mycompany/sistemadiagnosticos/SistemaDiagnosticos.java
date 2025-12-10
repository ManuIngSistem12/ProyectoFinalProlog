/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.sistemadiagnosticos;

import com.mycompany.models.Enfermedad;
import com.mycompany.models.EnfermedadDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Manuel
 */
public class SistemaDiagnosticos {

    public static void main(String[] args) {
        EnfermedadDAO ed = new EnfermedadDAO();
        try{
            List<Enfermedad>enfermedades = ed.obtenerEnfermedades();
            for(Enfermedad e : enfermedades){
                System.out.println("----ENFERMEDAD-----------");
                System.out.println(e.getNombre());
                System.out.println("----CATEGORIA------------");
                System.out.println(e.getCategoria());
                System.out.println("-----SINTOMAS------------");
                for(String t : e.getSintomas()){
                    System.out.println(t);
                }
                System.out.println("-----RECOMENDACIONES-----");
                System.out.println(e.getRecomendaciones());
                System.out.println("-------------------------");
            }
        }catch (SQLException ex){
            System.getLogger(EnfermedadDAO.class.getName()).log(System.Logger.Level.ERROR,(String) null, ex);
        }
    }
}
