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
    
    public PacienteDAO(){
        this.conn = MySQLConnection.getInstance().getConnection();
    }
    
    public Boolean agregarPaciente(String N, int E) throws SQLException{        
        String sql = "INSERT INTO paciente (nombre, edad) VALUES (? , ?)";
        PreparedStatement ps = null;
        try{
            ps = conn.prepareStatement(sql);
            ps.setString(1, N);
            ps.setInt(2, E);
            int f = ps.executeUpdate();
            
            return f > 0;
        }finally{
            if(ps != null){
                try{
                    ps.close();
                }catch(SQLException ignored){
                    System.out.println("Error clossing Prepared Statement");    
                }
            }
        }
    }
}
