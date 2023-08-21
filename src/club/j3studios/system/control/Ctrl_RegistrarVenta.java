package club.j3studios.system.control;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import club.j3studios.system.database.SQL;
import club.j3studios.system.model.CabeceraVenta;
import club.j3studios.system.model.DetalleVenta;
import club.j3studios.system.utils.Tools;

public class Ctrl_RegistrarVenta {
    
    public static int idCabeceraRegistrada;
    BigDecimal iDColVar;
   
    public boolean guardar(CabeceraVenta objeto) {
        boolean respuesta = false;
        Connection cn = SQL.conectar();
        try {
            PreparedStatement consulta = cn.prepareStatement("insert into tb_cabecera_venta values(?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            consulta.setInt(1, 0);//id
            consulta.setInt(2, objeto.getIdCaja());
            consulta.setInt(3, objeto.getIdUsuario());
            consulta.setInt(4, objeto.getIdCliente());
            consulta.setDouble(5, objeto.getValorPagar());
            consulta.setString(6, objeto.getFechaVenta());
            consulta.setInt(7, objeto.getEstado());
            
            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            
            ResultSet rs = consulta.getGeneratedKeys();
            while(rs.next()){
                iDColVar = rs.getBigDecimal(1);
                idCabeceraRegistrada = iDColVar.intValue();
            }
            
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al guardar cabecera de venta: " + e);
        }
        return respuesta;
    }
    
     /**
     * **************************************************
     * metodo para guardar el detalle de venta
     * **************************************************
     * @return 
     */
    public boolean guardarDetalle(DetalleVenta objeto) {
        boolean respuesta = false;
        Connection cn = SQL.conectar();
        try {
            PreparedStatement consulta = cn.prepareStatement("insert into tb_detalle_venta values(?,?,?,?,?,?,?,?,?,?,?)");
            consulta.setInt(1, 0);//id
            consulta.setInt(2, idCabeceraRegistrada);
            consulta.setInt(3, objeto.getIdProducto());
            consulta.setString(4, objeto.getCodigo());
            consulta.setString(5, objeto.getNombre());
            consulta.setDouble(6, objeto.getCantidad());
            consulta.setDouble(7, objeto.getPrecioVenta());
            consulta.setDouble(8, objeto.getSubTotal());
            consulta.setDouble(9, objeto.getIva());
            consulta.setDouble(10, objeto.getTotalPagarUsd());
            consulta.setDouble(11, objeto.getTotalPagarBs());
            
            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }            
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al guardar detalle de venta: " + e);
        }
        return respuesta;
    }
}
