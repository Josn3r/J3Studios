package club.j3studios.system.control;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import club.j3studios.system.database.SQL;
import club.j3studios.system.model.Cliente;
import club.j3studios.system.model.Usuario;
import club.j3studios.system.utils.Tools;
import java.sql.PreparedStatement;

public class Ctrl_Usuario {

            public boolean loginUser(Usuario usuario) {		
		boolean response = false;		
		Connection con = SQL.conectar();		
		String sql = "select " + SQL.USUARIO_USER + ", " + SQL.USUARIO_PASS + " from " + SQL.USUARIO_TABLE + " "
                        + "where " + SQL.USUARIO_USER + " = '" + usuario.getUsuario() + "' and " + SQL.USUARIO_PASS + " = '" + usuario.getPassword() + "'";		
		Statement st;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				response = true;
			}
		} catch (SQLException e) {
			System.out.println("[J3Studios] � Error al iniciar sesi�n.");
		}
		return response;
            }
        
            public void loadUserData(Usuario usuario) {	
		Connection con = SQL.conectar();		
		String sql = "select * from " + SQL.USUARIO_TABLE + " "
                        + "where " + SQL.USUARIO_USER + " = '" + usuario.getUsuario() + "' and " + SQL.USUARIO_PASS + " = '" + usuario.getPassword() + "'";		
		Statement st;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
                            usuario.setIdUsuario(rs.getInt(SQL.USUARIO_IDUSER));
                            usuario.setNombre(rs.getString(SQL.USUARIO_NOMBRE));
                            usuario.setTelefono(rs.getString(SQL.USUARIO_TLF));
                            usuario.setCorreo(rs.getString(SQL.USUARIO_CORREO));
                            usuario.setRango(rs.getInt(SQL.USUARIO_RANK));
                            usuario.setStatus(rs.getBoolean(SQL.USUARIO_STATUS));
			}
		} catch (SQLException e) {
			System.out.println("[J3Studios] � Error al iniciar sesi�n.");
		}
            }
            
        public void updateStatus(Usuario user, Boolean status) {
            new Tools().debug("Actualizando el Estado de "+ user.getNombre() + " a " + (status ? "Activo" : "Inactivo"));
            Connection cn = SQL.conectar();
            try {
                PreparedStatement consulta = cn.prepareStatement("update " + SQL.USUARIO_TABLE + " set " + SQL.USUARIO_STATUS + "=? where " + SQL.USUARIO_IDUSER + " ='" + user.getIdUsuario() + "'");
                consulta.setBoolean(1, status);
                consulta.executeUpdate();
                cn.close();
                
                new Tools().debug("Actualización completada!");
            } catch (SQLException e) {
                System.out.println("Error al actualizar el estado del usuario: " + e);
            }
        }
	
}
