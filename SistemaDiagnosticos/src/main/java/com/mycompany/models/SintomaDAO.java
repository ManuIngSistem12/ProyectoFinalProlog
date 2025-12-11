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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Manuel
 */
public class SintomaDAO {

    private Connection conn;

    public SintomaDAO() {
        this.conn = MySQLConnection.getInstance().getConnection();
    }

    public List<Sintoma> obtenerSintomas() throws SQLException {
        String sql = "SELECT * FROM sintomas";
        List<Sintoma> sintomas;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            sintomas = mapearSintomasObjeto(rs);
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
        return sintomas;
    }
    
    private List<Sintoma> mapearSintomasObjeto(ResultSet rs){
        List<Sintoma> sintomas = new ArrayList<>();
        try{
            while(rs.next()){
                Sintoma s = new Sintoma();
                s.setNombre(rs.getString("sin_nombre"));
                s.setId(rs.getInt("sin_id"));
                sintomas.add(s);
            }
        }catch(SQLException ex){
            System.getLogger(SintomaDAO.class.getName()).log(System.Logger.Level.ERROR,(String) null, ex);
        }
        return sintomas;
    }
}
