
package club.j3studios.a_login;

import club.j3studios.system.database.SQL;
import club.j3studios.system.utils.Tools;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Random;

public class LoginRegisterUtil {
    
    public void insertUser(ModelUser user) {
        Connection cn = SQL.conectar2();
        try {
            PreparedStatement consulta = cn.prepareStatement("insert into tb_cuentas values(?, ?, ?, ?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            String code = generateVerifyCode();
        
            consulta.setInt(1, 0);//id
            consulta.setString(2, user.getEmail());
            consulta.setString(3, user.getPassword());
            consulta.setString(4, user.getNombre());
            consulta.setString(5, user.getRazonSocial());
            consulta.setString(6, code);
            consulta.setString(7, "Unverified");
            consulta.setInt(8, 0);
            consulta.setInt(9, 0);
            consulta.execute();
            
            ResultSet rs = consulta.getGeneratedKeys();
            rs.first();
            int userID = rs.getInt(1);
            
            rs.close();
            consulta.close();
            
            user.setUserID(userID);
            user.setVerifyCode(code);            
        } catch (SQLException e) {
            System.out.println("Error al guardar una nueva cuenta: " + e);
        }
    }
    
    public boolean login(String correo, String password) {		
        boolean response = false;        
        Connection con = SQL.conectar2();        
        String sql = "select correo, password from tb_cuentas where correo = '" + correo + "' and password = '" + password + "'";		
	Statement st;
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                response = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return response;
    }
    
    public ModelUser login(ModelLogin login) throws SQLException {
        ModelUser data = null;
        Connection con = SQL.conectar2();        
        String sql = "select * from tb_cuentas where BINARY(correo)=? and BINARY(`Password`)=? and `status`='Verified' limit 1";		
	try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, login.getEmail());
            ps.setString(2, login.getPassword());
            
            ResultSet rs = ps.executeQuery();
            if (rs.first()) {
                int userID = rs.getInt("idCuenta");
                String email = rs.getString("correo");
                String nombre = rs.getString("nombre");
                String razonSocial = rs.getString("razonSocial");
                data = new ModelUser(userID, email, "", nombre, razonSocial);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }        
        return data;
    }
    
    private boolean checkDuplicateCode(String code) throws SQLException {
        boolean duplicate = false;
        Connection con = SQL.conectar2();        
        String sql = "select idCuenta from tb_cuentas where VerifyCode = ? limit 1";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();
            if (rs.first()) {
                duplicate = true;
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return duplicate;
    }
    
    private String generateVerifyCode() throws SQLException {
        DecimalFormat df = new DecimalFormat("000000");
        Random ran = new Random();
        String code = df.format(ran.nextInt(1000000));  //  Random from 0 to 999999
        while (checkDuplicateCode(code)) {
            code = df.format(ran.nextInt(1000000));
        }
        return code;
    }

    public boolean checkDuplicateEmail(String correo) throws SQLException {
        boolean duplicate = false;
        Connection con = SQL.conectar2();        
        String sql = "select idCuenta from tb_cuentas where correo=? and status='Verified' limit 1";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, correo);
            ResultSet rs = ps.executeQuery();
            if (rs.first()) {
                duplicate = true;
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return duplicate;
    }

    public void doneVerify(int userID) throws SQLException {
        Connection con = SQL.conectar2();
        String sql = "update tb_cuentas set VerifyCode='', `status`='Verified' where idCuenta=? limit 1";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userID);
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            System.out.println("[J3Studios] Error al obtener la información de la caja en la base de datos.");
        }
    }

    public boolean verifyCodeWithUser(int userID, String code) throws SQLException {
        boolean verify = false;
        
        Connection con = SQL.conectar2();
        String sql = "select idCuenta from tb_cuentas where idCuenta=? and VerifyCode=? limit 1";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userID);
            ps.setString(2, code);
            ResultSet rs = ps.executeQuery();
            if (rs.first()) {
                verify = true;
            }   
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("[J3Studios] Error al obtener la información en la base de datos.");
            new Tools().debug(e.getMessage());
        }
        return verify;
    }
    
    public boolean deleteUser(int idUser) {
        boolean respuesta = false;
        Connection cn = SQL.conectar();
        try {
            PreparedStatement consulta = cn.prepareStatement("delete from tb_cuentas where idCuenta ='" + idUser + "'");
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
