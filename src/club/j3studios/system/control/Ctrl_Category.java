package club.j3studios.system.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import club.j3studios.system.database.SQL;
import club.j3studios.system.model.Categoria;

public class Ctrl_Category {
	
	public boolean saveCategory(Categoria category) {
		boolean response = false;
		Connection con = SQL.conectar();
		try {
			PreparedStatement consulta = con.prepareStatement("insert into tb_categoria(idCategoria, descripcion) values(?, ?)");
			consulta.setInt(1, 0);
			consulta.setString(2, category.getDescripcion());
			
			if (consulta.executeUpdate() > 0) {
				response = true;
			}
			con.close();
		} catch (SQLException e) {
			System.out.println("[J3Studios] � Error al guardar la categoria: " + e);
		}
		return response;
	}

	public boolean existCategory(String category) {
		boolean response = false;
		String sql = "select descripcion from tb_categoria where descripcion = '" + category + "'";
		Statement st;		
		try {
			Connection con = SQL.conectar();
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				response = true;
			}
			
		} catch (SQLException e) {
			System.out.println("[J3Studios] � Error al consultar la categoria: " + e);
		}
		return response;
	}
	
	public boolean updateCategory(Categoria category, Integer idCategoria) {
		boolean response = false;
		Connection con = SQL.conectar();
		try {
			PreparedStatement consulta = con.prepareStatement("update tb_categoria set descripcion=? where idCategoria='" + idCategoria + "'");
			consulta.setString(1, category.getDescripcion());
			
			if (consulta.executeUpdate() > 0) {
				response = true;
			}
			con.close();
		} catch (SQLException e) {
			System.out.println("[J3Studios] � Error al actualizar la categoria: " + e);
		}
		return response;
	}
	
	public boolean deleteCategory (Integer idCategoria) {
		boolean response = false;
		Connection con = SQL.conectar();
		try {
			PreparedStatement consulta = con.prepareStatement("delete from tb_categoria where idCategoria='" + idCategoria + "'");
			consulta.executeUpdate();			
			if (consulta.executeUpdate() > 0) {
				response = true;
			}
			con.close();
		} catch (SQLException e) {
			System.out.println("[J3Studios] � Error al eliminar la categoria: " + e);
		}
		return response;
	}
}
