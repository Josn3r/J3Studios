package club.j3studios.system.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import club.j3studios.system.database.SQL;
import club.j3studios.system.model.Cliente;

public class Ctrl_Cliente {

    public boolean guardar(Cliente objeto) {
        boolean respuesta = false;
        Connection cn = SQL.conectar();
        try {
            PreparedStatement consulta = cn.prepareStatement("insert into " + SQL.CLIENTES_TABLE + " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            consulta.setInt(1, 0);//id
            consulta.setString(2, objeto.getNombre());
            consulta.setString(3, objeto.getCedula());
            consulta.setString(4, objeto.getGenero());
            consulta.setString(5, objeto.getTelefono());
            consulta.setString(6, objeto.getDireccion());
            consulta.setString(7, objeto.getCorreo());
            consulta.setInt(8, objeto.getCompras());
            consulta.setDouble(9, objeto.getValorTotal());
            consulta.setString(10, objeto.getFechaRegistro());
            consulta.setString(11, objeto.getUltimaCompra());
            
            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al guardar cliente: " + e);
        }
        return respuesta;
    }

    public boolean existeCliente(String cedula) {
        boolean respuesta = false;
        String sql = "select " + SQL.CLIENTES_CEDULA + " from " + SQL.CLIENTES_TABLE + " where " + SQL.CLIENTES_CEDULA + " = '" + cedula + "';";
        Statement st;
        try {
            Connection cn = SQL.conectar();
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                respuesta = true;
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar cliente: " + e);
        }
        return respuesta;
    }

    public boolean actualizar(Cliente objeto, int idCliente) {
        boolean respuesta = false;
        Connection cn = SQL.conectar();
        try {

            PreparedStatement consulta = cn.prepareStatement("update " + SQL.CLIENTES_TABLE + " set "
                    + SQL.CLIENTES_NOMBRE + "=?, "
                    + SQL.CLIENTES_CEDULA + "= ?, "
                    + SQL.CLIENTES_GENERO + " = ?, "
                    + SQL.CLIENTES_TLF + "= ?, "
                    + SQL.CLIENTES_DIRECCION + "= ?, "
                    + SQL.CLIENTES_CORREO + "= ?, "
                    + SQL.CLIENTES_COMPRAS + "= ?, "
                    + SQL.CLIENTES_TOTALCONSUME + "= ?, "
                    + SQL.CLIENTES_FECHAREG + "= ?, "
                    + SQL.CLIENTES_LASTCOMPRA + "= ? "
                    + "where " + SQL.CLIENTES_IDCLIENT + " ='" + idCliente + "'");
            
            consulta.setString(1, objeto.getNombre());
            consulta.setString(2, objeto.getCedula());
            consulta.setString(3, objeto.getGenero());
            consulta.setString(4, objeto.getTelefono());
            consulta.setString(5, objeto.getDireccion());
            consulta.setString(6, objeto.getCorreo());
            consulta.setInt(7, objeto.getCompras());
            consulta.setDouble(8, objeto.getValorTotal());
            consulta.setString(9, objeto.getFechaRegistro());
            consulta.setString(10, objeto.getUltimaCompra());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar cliente: " + e);
        }
        return respuesta;
    }

    public boolean eliminar(int idCliente) {
        boolean respuesta = false;
        Connection cn = SQL.conectar();
        try {
            PreparedStatement consulta = cn.prepareStatement(
                    "delete from " + SQL.CLIENTES_TABLE + " where " + SQL.CLIENTES_IDCLIENT + " ='" + idCliente + "'");
            consulta.executeUpdate();

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al eliminar cliente: " + e);
        }
        return respuesta;
    }

}
