package club.j3studios.system.control;

import java.sql.Connection;
import java.sql.SQLException;

import club.j3studios.system.database.SQL;
import club.j3studios.system.model.Cajas;
import club.j3studios.system.model.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Ctrl_Cajas {
    
    public Cajas getCaja(String str) {	
        Cajas caja = new Cajas();        
        Connection con = SQL.conectar();
        String sql = "select * from " + SQL.CAJA_TABLE + " where " + SQL.CAJA_NOMBRE + " = '" + str + "'";
        Statement st;
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                caja.setIdCaja(rs.getInt(SQL.CAJA_ID));
                caja.setNombre(rs.getString(SQL.CAJA_NOMBRE));
                caja.setIdUsuario(rs.getInt(SQL.CAJA_IDUSER));
            }
        } catch (SQLException e) {
            System.out.println("[J3Studios] Error al obtener la información de la caja en la base de datos.");
        }
        
        return caja;
    }
    
    public void updateStatus(Cajas caja, Integer userId) {   
        Connection cn = SQL.conectar();
        try {
            PreparedStatement consulta = cn.prepareStatement("update " + SQL.CAJA_TABLE + " set " + SQL.CAJA_IDUSER + " = ? where " + SQL.CAJA_ID + " ='" + caja.getIdCaja() + "'");
            consulta.setInt(1, userId);
            consulta.executeUpdate();
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar el estado del usuario: " + e);
        }  
        caja.setIdUsuario(userId);
    }

    public void openCaja(Cajas caja, Double bs, Double usd) {
        Date date = new Date();
        String fechaActual = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss").format(date);
        caja.setDateOpen(fechaActual);
        caja.setValueBs(bs);
        caja.setValueUsd(usd);
        caja.setTotalFacturado(0.0);
    }

    public void closeCaja(Cajas caja) {
    
    }
}
