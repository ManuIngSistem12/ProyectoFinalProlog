/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.models;

import com.mycompany.conn.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Manuel
 */
public class DiagnosticoDAO {

    private Connection conn;

    public DiagnosticoDAO() {
        this.conn = MySQLConnection.getInstance().getConnection();
    }

    public Boolean agregarDiagnostico(int idP, int idE, List<Sintoma> ls) throws SQLException {
        String sql = "INSERT INTO diagnostico (pac_id, sint_id, enf_id) VALUES (?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            int exitos = 0;

            for (Sintoma s : ls) {
                ps.setInt(1, idP);
                ps.setInt(2, s.getId());
                ps.setInt(3, idE);

                int f = ps.executeUpdate();
                if (f > 0) {
                    exitos++;
                }
            }
            return exitos == ls.size();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ignored) {
                    System.out.println("Error clossing Prepared Statement");
                }
            }
        }
    }
    
    public Diagnostico buscarDiagnostico(int idP) throws SQLException {
        String sql = "SELECT d.diag_id, p.nombre, e.enf_nombre, e.enf_recomendacion_basica, c.cat_nombre, s.sin_nombre FROM diagnostico AS d JOIN enfermedad AS e ON d.enf_id=e.enf_id JOIN categoria AS c ON e.cat_id=c.cat_id JOIN sintomas AS s ON d.sint_id=s.sin_id JOIN paciente AS p ON d.pac_id=p.pac_id WHERE d.pac_id = ?";
        Diagnostico d = new Diagnostico();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idP);
            rs = ps.executeQuery();
            d = mapearObjeto(rs);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ignored) {
                    System.out.println("Error clossing result set");
                }
            }

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ignored) {
                    System.out.println("Error clossing Prepared Statement");
                }
            }
        }
        return d;
    }
    
    private Diagnostico mapearObjeto(ResultSet rs){
        Diagnostico d = new Diagnostico();
        try{
            while(rs.next()){
                d.setNombrePaciente(rs.getString("nombre"));
                d.setEnfermedadDetectada(rs.getString("enf_nombre"));
                d.setRecomendaciones(rs.getString("enf_recomendacion_basica"));
                d.setCategoria(rs.getString("cat_nombre"));
                d.getSintomasPaciente().add(rs.getString("sin_nombre"));
            }
        }catch(SQLException ex){
            System.getLogger(DiagnosticoDAO.class.getName()).log(System.Logger.Level.ERROR,(String) null, ex);
        }
        return d;
    }

}
