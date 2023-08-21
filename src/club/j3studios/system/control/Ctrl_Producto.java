package club.j3studios.system.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import club.j3studios.system.database.SQL;
import club.j3studios.system.model.Producto;

public class Ctrl_Producto {
    
    public boolean saveProducto(Producto producto) {
        boolean response = false;
        Connection con = SQL.conectar();
        
        try {
            PreparedStatement consulta = con.prepareStatement("insert into " + SQL.PRODUCTOS_TABLE + " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            consulta.setInt(1, 0);
            consulta.setString(2, producto.getCodigo());
            consulta.setString(3, producto.getDescripcion());
            consulta.setInt(4, producto.getIdCategoria());
            consulta.setInt(5, producto.getIdMarca());
            consulta.setDouble(6, producto.getPrecioCosto());
            consulta.setDouble(7, producto.getGanancia());
            consulta.setDouble(8, producto.getPrecioVenta());
            consulta.setBoolean(9, producto.getControlStock());
            consulta.setInt(10, producto.getStock());
            consulta.setInt(11, producto.getMinStock());
            consulta.setInt(12, producto.getMaxStock());
            consulta.setString(13, producto.getUnidadMedida());
            consulta.setBoolean(14, producto.getExento());
			
            if (consulta.executeUpdate() > 0) {
                response = true;
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("[J3Studios] Error al guardar el producto: " + e);
        }
        return response;
    }
    
    public boolean existProductoByCode(String codigo) {
        boolean response = false;
        String sql = "select " + SQL.PRODUCTOS_CODIGO + " from " + SQL.PRODUCTOS_TABLE + " where " + SQL.PRODUCTOS_CODIGO + " = '" + codigo + "'";
        Statement st;		
        try {
            Connection con = SQL.conectar();
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                response = true;
            }
            
        } catch (SQLException e) {
            System.out.println("[J3Studios] Error al consultar los productos: " + e);
        }
        return response;
    }
	
    public boolean existProductoByName(String nombre) {
        boolean response = false;
        String sql = "select " + SQL.PRODUCTOS_DESC + " from " + SQL.PRODUCTOS_TABLE + " where " + SQL.PRODUCTOS_DESC + " = '" + nombre + "'";
        Statement st;		
        try {
            Connection con = SQL.conectar();
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                response = true;
            }            
        } catch (SQLException e) {
            System.out.println("[J3Studios] Error al consultar los productos: " + e);
        }
        return response;
    }
	
    public String getExistProductoByCode(String codigo) {
        String response = "";
        String sql = "select " + SQL.PRODUCTOS_CODIGO + " from " + SQL.PRODUCTOS_TABLE + " where " + SQL.PRODUCTOS_CODIGO + " = '" + codigo + "'";
        Statement st;		
        try {
            Connection con = SQL.conectar();
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                response = rs.getString(SQL.PRODUCTOS_CODIGO);
            }			
        } catch (SQLException e) {
            System.out.println("[J3Studios] Error al consultar los productos: " + e);
        }
        return response;
    }
	
    public String getExistProductoByName(String nombre) {
        String response = "";
        String sql = "select " + SQL.PRODUCTOS_DESC + " from " + SQL.PRODUCTOS_TABLE + " where " + SQL.PRODUCTOS_DESC + " = '" + nombre + "'";
        Statement st;		
        try {
            Connection con = SQL.conectar();
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                response = rs.getString(SQL.PRODUCTOS_DESC);
            }			
        } catch (SQLException e) {
            System.out.println("[J3Studios] Error al consultar los productos: " + e);
        }
        return response;
    }
	
    public Integer getIdProductoByCode(String codigo) {
        Integer response = 0;
        String sql = "select " + SQL.PRODUCTOS_IDPRODUCT + " from " + SQL.PRODUCTOS_TABLE + " where " + SQL.PRODUCTOS_CODIGO + " = '" + codigo + "'";
        Statement st;
        try {
            Connection con = SQL.conectar();
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                response = rs.getInt(SQL.PRODUCTOS_IDPRODUCT);
            }			
        } catch (SQLException e) {
            System.out.println("[J3Studios] Error al consultar los productos: " + e);
        }
        return response;
    }
		
    public Integer getIdProductoByName(String nombre) {
        Integer response = 0;
        String sql = "select " + SQL.PRODUCTOS_IDPRODUCT + " from " + SQL.PRODUCTOS_TABLE + " where " + SQL.PRODUCTOS_DESC + " = '" + nombre + "'";
        Statement st;		
        try {
            Connection con = SQL.conectar();
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                response = rs.getInt(SQL.PRODUCTOS_IDPRODUCT);
            }			
        } catch (SQLException e) {
            System.out.println("[J3Studios] Error al consultar los productos: " + e);
        }
        return response;
    }
	
    public boolean updateProducto(Producto producto, Integer idProducto) {
        boolean response = false;
        Connection con = SQL.conectar();
        try {
            PreparedStatement consulta = con.prepareStatement("update " + SQL.PRODUCTOS_TABLE + " set "
                    + SQL.PRODUCTOS_CODIGO + "=?, "
                    + SQL.PRODUCTOS_DESC + "=?, "
                    + SQL.PRODUCTOS_IDCATEGORIA + "=?, "
                    + SQL.PRODUCTOS_IDMARCA + "=?, "
                    + SQL.PRODUCTOS_COSTE + "=?, "
                    + SQL.PRODUCTOS_GANANCIA + "=?, "
                    + SQL.PRODUCTOS_VENTA + "=?, "
                    + SQL.PRODUCTOS_STOCK_CONTROL + "=?, "
                    + SQL.PRODUCTOS_STOCK_ACTUAL + "=?, "
                    + SQL.PRODUCTOS_STOCK_MIN + "=?, "
                    + SQL.PRODUCTOS_STOCK_MAX + "=?, "
                    + SQL.PRODUCTOS_UNIDADMEDIDA + "=?, "
                    + SQL.PRODUCTOS_EXENTO + "=? "
                    + "where " + SQL.PRODUCTOS_IDPRODUCT + " ='" + idProducto + "'");	
            consulta.setString(1, producto.getCodigo());
            consulta.setString(2, producto.getDescripcion());
            consulta.setInt(3, producto.getIdCategoria());
            consulta.setInt(4, producto.getIdMarca());
            consulta.setDouble(5, producto.getPrecioCosto());
            consulta.setDouble(6, producto.getGanancia());
            consulta.setDouble(7, producto.getPrecioVenta());
            consulta.setBoolean(8, producto.getControlStock());
            consulta.setInt(9, producto.getStock());
            consulta.setInt(10, producto.getMinStock());
            consulta.setInt(11, producto.getMaxStock());
            consulta.setString(12, producto.getUnidadMedida());
            consulta.setBoolean(13, producto.getExento());
            
            if (consulta.executeUpdate() > 0) {
                response = true;
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("[J3Studios] Error al actualizar el producto: " + e);
        }
        return response;
    }
	
    public boolean deleteProducto (Integer idProducto) {
        boolean response = false;
        Connection con = SQL.conectar();
        try {
            PreparedStatement consulta = con.prepareStatement("delete from " + SQL.PRODUCTOS_TABLE + " where " + SQL.PRODUCTOS_IDPRODUCT + "='" + idProducto + "'");
            consulta.executeUpdate();			
            if (consulta.executeUpdate() > 0) {
                response = true;
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("[J3Studios] Error al eliminar el producto: " + e);
        }
        return response;
    }
}
