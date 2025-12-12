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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Manuel
 */
public class EnfermedadDAO {
    private Connection conn;
    
    public EnfermedadDAO(){
        this.conn = MySQLConnection.getInstance().getConnection();
    }
    
    public List<Enfermedad> obtenerEnfermedades() throws SQLException{
        String sql = "SELECT e.enf_id, e.enf_nombre, e.enf_recomendacion_basica, c.cat_nombre, s2.sin_nombre FROM enfermedad AS e JOIN sintoma_enfermedad AS s ON e.enf_id = s.enf_id JOIN categoria AS c ON e.cat_id = c.cat_id JOIN sintomas AS s2 ON s.sin_id = s2.sin_id;";
        List<Enfermedad> enfermedades;
        PreparedStatement ps =null;
        ResultSet rs = null;
        
        try{
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            enfermedades = mapearEnfermedadObjeto(rs);
        }finally{
            if(rs != null){
                try{
                    rs.close();
                }catch (SQLException ignored){
                    System.out.println("Error clossing result set");
                }
            }
            
            if(ps != null){
                try{
                    ps.close();
                }catch(SQLException ignored){
                    System.out.println("Error clossing Prepared Statement");    
                }
            }
        }
        return enfermedades;
    }
    
    
    
    public Enfermedad obtenerEnfermedad(String N) throws SQLException{
        String sql = "SELECT e.enf_id, e.enf_nombre, e.enf_recomendacion_basica, c.cat_nombre FROM enfermedad AS e JOIN categoria AS c ON c.cat_id = e.cat_id WHERE e.enf_nombre = ?;";
        Enfermedad e = new Enfermedad();
        PreparedStatement ps =null;
        ResultSet rs = null;
        
        try{
            ps = conn.prepareStatement(sql);
            ps.setString(1, N);
            rs = ps.executeQuery();
            e = mapearEnfermedad(rs);
        }finally{
            if(rs != null){
                try{
                    rs.close();
                }catch (SQLException ignored){
                    System.out.println("Error clossing result set");
                }
            }
            
            if(ps != null){
                try{
                    ps.close();
                }catch(SQLException ignored){
                    System.out.println("Error clossing Prepared Statement");    
                }
            }
        }
        return e;
    }
    
    private Enfermedad mapearEnfermedad(ResultSet rs) {
        Enfermedad e = new Enfermedad();
        try {
            while (rs.next()) {
                e.setId(rs.getInt("enf_id"));
                e.setNombre(rs.getString("enf_nombre"));
                e.setRecomendaciones(rs.getString("enf_recomendacion_basica"));
                e.setCategoria(rs.getString("cat_nombre"));
            }
        } catch (SQLException ex) {
            System.getLogger(PacienteDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return e;
    }
    
    private List<Enfermedad> mapearEnfermedadObjeto(ResultSet rs){
        List<Enfermedad> enfermedades = new ArrayList<>();
        Set<String> enfermedadesSinDuplicados = new HashSet<>();
        
        try{
            while(rs.next()){
                if(!enfermedadesSinDuplicados.contains(rs.getString("enf_nombre"))){
                    enfermedadesSinDuplicados.add(rs.getString("enf_nombre"));
                    Enfermedad e = new Enfermedad();
                    e.setId(rs.getInt("enf_id"));
                    e.setNombre(rs.getString("enf_nombre"));
                    e.setRecomendaciones(rs.getString("enf_recomendacion_basica"));
                    e.setCategoria(rs.getString("cat_nombre"));
                    e.getSintomas().add(rs.getString("sin_nombre"));
                    enfermedades.add(e);                    
                }else{
                    for(int i = 0; i < enfermedades.size(); i++){
                        if(enfermedades.get(i).getNombre().equals(rs.getString("enf_nombre"))){
                            enfermedades.get(i).getSintomas().add(rs.getString("sin_nombre"));
                            break;
                        }
                    }
                }
            }
        }catch (SQLException ex){
            System.getLogger(EnfermedadDAO.class.getName()).log(System.Logger.Level.ERROR,(String) null, ex);
        }
        return enfermedades;
    }
}
