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

/**
 *
 * @author Manuel
 */
public class PacienteDAO {

    private Connection conn;

    public PacienteDAO() {
        this.conn = MySQLConnection.getInstance().getConnection();
    }

    public Boolean agregarPaciente(String N, int E) throws SQLException {
        String sql = "INSERT INTO paciente (nombre, edad) VALUES (? , ?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, N);
            ps.setInt(2, E);
            int f = ps.executeUpdate();

            return f > 0;
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

    public Paciente buscarPaciente(String N) throws SQLException {
        String sql = "SELECT * FROM paciente WHERE nombre = ?";
        Paciente p = new Paciente();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, N);
            rs = ps.executeQuery();
            p = mapearObjeto(rs);
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
        return p;
    }

    private Paciente mapearObjeto(ResultSet rs) {
        Paciente p = new Paciente();
        try {
            while (rs.next()) {
                p.setId(rs.getInt("pac_id"));
                p.setNombre(rs.getString("nombre"));
                p.setEdad(rs.getInt("edad"));
            }
        } catch (SQLException ex) {
            System.getLogger(PacienteDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return p;
    }
}
