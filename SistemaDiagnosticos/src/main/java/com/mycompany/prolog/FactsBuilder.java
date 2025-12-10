/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prolog;

import com.mycompany.conn.MySQLConnection;
import com.mycompany.models.Enfermedad;
import com.mycompany.models.EnfermedadDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Manuel
 */
public class FactsBuilder {

    private Connection conn;

    public FactsBuilder() {
        this.conn = MySQLConnection.getInstance().getConnection();
    }

    public static boolean construirFactsEnfermedad() {
        EnfermedadDAO dao = new EnfermedadDAO();
        try {
            List<Enfermedad> lista = dao.obtenerEnfermedades();

            for (Enfermedad t : lista) {
                StringBuilder sb = new StringBuilder();
                sb.append("assertz(enfermedad(")
                        .append(t.getNombre()).append(",")
                        .append("sintomas([");
                for (int i = 0; i < t.getSintomas().size(); i++) {
                    sb.append(t.getSintomas().get(i));
                    if(i < t.getSintomas().size()-1){
                        sb.append(",");
                    }
                }
                sb.append("]),")
                        .append("categoria(")
                        .append(t.getCategoria()).append("),")
                        .append("recomendaciones([")
                        .append(t.getRecomendaciones()).append("])))");
                PrologQueryExecutor.createDynamicFact(sb.toString());
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving data");
        }
        return true;
    }

}
