package club.j3studios.system.database;

import club.j3studios.system.utils.Tools;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQL {
        
    public static Connection conectar () {        
        /*String DB_HOST = "localhost";
        String DB_PORT = "3306";
        String DB_NAME = "bd_sistema_ventas";
        String USER_LOGIN = "datauserlogin";
        String USER_PASSWORD = "Ij3TA2L!NkYGgf0)";*/
        
        String DB_HOST = new Tools().getConfig().getProperty("DB_HOST");
        String DB_PORT = new Tools().getConfig().getProperty("DB_PORT");
        String DB_NAME = new Tools().getConfig().getProperty("DB_NAME");
        String USER_LOGIN = new Tools().getConfig().getProperty("DB_USER");
        String USER_PASSWORD = new Tools().getConfig().getProperty("DB_PASS");
        
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://"+DB_HOST+":"+DB_PORT+"/"+DB_NAME, USER_LOGIN, USER_PASSWORD);
            return con;
        } catch (SQLException e) {
            System.out.println("[J3Studios] Se presentó un error al conectar la base de datos.");
        }
        return null;
    }
    
    public static Connection conectar2() {        
        
        String DB_HOST = new Tools().getConfig().getProperty("DB_HOST");
        String DB_PORT = new Tools().getConfig().getProperty("DB_PORT");
        String DB_NAME = "bd_sistema";
        String USER_LOGIN = new Tools().getConfig().getProperty("DB_USER");
        String USER_PASSWORD = new Tools().getConfig().getProperty("DB_PASS");
        
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://"+DB_HOST+":"+DB_PORT+"/"+DB_NAME, USER_LOGIN, USER_PASSWORD);
            return con;
        } catch (SQLException e) {
            System.out.println("[J3Studios] Se presentó un error al conectar la base de datos.");
        }
        return null;
    }
	
    // CAJAS
    public static String CAJA_TABLE = "tb_cajas";    
    public static String CAJA_ID = "idCaja";
    public static String CAJA_NOMBRE = "nombre";
    public static String CAJA_IDUSER = "idUsuario";
    
    // USUARIO
    public static String USUARIO_TABLE = "tb_usuarios";    
    public static String USUARIO_IDUSER = "idUsuario";
    public static String USUARIO_NOMBRE = "nombre";
    public static String USUARIO_USER = "usuario";
    public static String USUARIO_PASS = "password";
    public static String USUARIO_TLF = "telefono";
    public static String USUARIO_CORREO = "correo";
    public static String USUARIO_RANK = "idRango";
    public static String USUARIO_STATUS = "estado";
    
    // RANGOS
    public static String RANGO_TABLE = "tb_rangos";    
    public static String RANGO_IDRANGO = "idRango";    
    public static String RANGO_NOMBRE = "nombre";    
    public static String RANGO_SUPERVISOR = "supervisor";    
    public static String RANGO_CAJA_A = "caja_a";    
    public static String RANGO_CAJA_B = "caja_b";    
    public static String RANGO_CAJA_C = "caja_c";    
    public static String RANGO_CAJA_D = "caja_d";    
    public static String RANGO_CAJA_E = "caja_e";    
    public static String RANGO_CAJA_F = "caja_f";    
    public static String RANGO_CAJA_G = "caja_g";    
    public static String RANGO_CAJA_H = "caja_h";    
    public static String RANGO_CAJA_I = "caja_i";    
    public static String RANGO_CAJA_J = "caja_j";    
    public static String RANGO_CLIENTES_A = "clientes_a";    
    public static String RANGO_CLIENTES_B = "clientes_b";    
    public static String RANGO_CLIENTES_C = "clientes_c";    
    public static String RANGO_CLIENTES_D = "clientes_d";    
    public static String RANGO_CLIENTES_E = "clientes_e";    
    public static String RANGO_CLIENTES_F = "clientes_f";    
    public static String RANGO_CLIENTES_G = "clientes_g";    
    public static String RANGO_CLIENTES_H = "clientes_h";    
    public static String RANGO_GENERAL_A = "general_a";    
    public static String RANGO_GENERAL_B = "general_b";    
    public static String RANGO_ESTADISTICAS_A = "estadisticas_a";    
    public static String RANGO_PROVEEDORES_A = "proveedores_a";    
    public static String RANGO_PROVEEDORES_B = "proveedores_b";    
    public static String RANGO_PRODUCTOS_A = "productos_a";    
    public static String RANGO_PRODUCTOS_B = "productos_b";    
    public static String RANGO_PRODUCTOS_C = "productos_c";    
    public static String RANGO_PRODUCTOS_D = "productos_d";    
    public static String RANGO_PRODUCTOS_E = "productos_e";    
    public static String RANGO_PRODUCTOS_F = "productos_f";    
    public static String RANGO_PRODUCTOS_G = "productos_g";    
    public static String RANGO_PRODUCTOS_H = "productos_h";    
    public static String RANGO_STOCK_A = "stock_a";    
    public static String RANGO_STOCK_B = "stock_b";    
    public static String RANGO_STOCK_C = "stock_c";    
    public static String RANGO_STOCK_D = "stock_d";    
    public static String RANGO_STOCK_E = "stock_e";    
    public static String RANGO_STOCK_F = "stock_f";    
    public static String RANGO_STOCK_G = "stock_g";    
    public static String RANGO_STOCK_H = "stock_h";    
    public static String RANGO_STOCK_I = "stock_i";    
    public static String RANGO_VENTAS_A = "ventas_a";    
    public static String RANGO_VENTAS_B = "ventas_b";    
    public static String RANGO_VENTAS_C = "ventas_c";    
    public static String RANGO_VENTAS_D = "ventas_d";    
    public static String RANGO_VENTAS_E = "ventas_e";    
    public static String RANGO_VENTAS_F = "ventas_f";    
    public static String RANGO_VENTAS_G = "ventas_g";    
    public static String RANGO_VENTAS_H = "ventas_h";    
    public static String RANGO_VENTAS_I = "ventas_i";    
    public static String RANGO_VENTAS_J = "ventas_j";    
    public static String RANGO_VENTAS_K = "ventas_k";    
    public static String RANGO_VENTAS_L = "ventas_l";    
    public static String RANGO_VENTAS_M = "ventas_m";    
    public static String RANGO_VENTAS_N = "ventas_n";    
    public static String RANGO_VENTAS_O = "ventas_o";    
    public static String RANGO_VENTAS_P = "ventas_p";    
    public static String RANGO_VENTAS_Q = "ventas_q";    
    public static String RANGO_CUENTASAPAGAR_A = "cuentasAPagar_a";    
    public static String RANGO_CUENTASAPAGAR_B = "cuentasAPagar_b";    
    public static String RANGO_CUENTASAPAGAR_C = "cuentasAPagar_c";    
    public static String RANGO_CUENTASAPAGAR_D = "cuentasAPagar_d";    
    public static String RANGO_CUENTASAPAGAR_E = "cuentasAPagar_e";    
    public static String RANGO_CUENTASAPAGAR_F = "cuentasAPagar_f";    
    public static String RANGO_CUENTASAPAGAR_G = "cuentasAPagar_g";    
    public static String RANGO_CUENTASAPAGAR_H = "cuentasAPagar_h";    
    
    // CATEGORIAS
    public static String CATEGORIA_TABLE = "tb_categorias";    
    public static String CATEGORIA_ID = "idCategoria";    
    public static String CATEGORIA_DESC = "descripcion";    
    
    // MARCAS
    public static String MARCAS_TABLE = "tb_marcas";    
    public static String MARCAS_ID = "idMarcas";    
    public static String MARCAS_DESC = "descripcion";    
    
    // PRODUCTOS
    public static String PRODUCTOS_TABLE = "tb_productos";    
    public static String PRODUCTOS_IDPRODUCT = "idProducto";    
    public static String PRODUCTOS_CODIGO = "codigo";    
    public static String PRODUCTOS_DESC = "descripcion";    
    public static String PRODUCTOS_IDCATEGORIA = "idCategoria";    
    public static String PRODUCTOS_IDMARCA = "idMarca";    
    public static String PRODUCTOS_COSTE = "precioCoste";    
    public static String PRODUCTOS_GANANCIA = "ganancia";    
    public static String PRODUCTOS_VENTA = "precioVenta";    
    public static String PRODUCTOS_STOCK_CONTROL = "stockControl";    
    public static String PRODUCTOS_STOCK_ACTUAL = "stockActual";    
    public static String PRODUCTOS_STOCK_MIN = "stockMin";    
    public static String PRODUCTOS_STOCK_MAX = "stockMax";    
    public static String PRODUCTOS_UNIDADMEDIDA = "unidadMedida";    
    public static String PRODUCTOS_EXENTO = "exento";    
    
    // CLIENTES
    public static String CLIENTES_TABLE = "tb_clientes";    
    public static String CLIENTES_IDCLIENT = "idCliente";    
    public static String CLIENTES_NOMBRE = "nombre";    
    public static String CLIENTES_CEDULA = "cedula";    
    public static String CLIENTES_GENERO = "genero";    
    public static String CLIENTES_TLF = "telefono";    
    public static String CLIENTES_DIRECCION = "direccion";    
    public static String CLIENTES_CORREO = "correo";    
    public static String CLIENTES_COMPRAS = "compras";    
    public static String CLIENTES_TOTALCONSUME = "totalConsumido";    
    public static String CLIENTES_FECHAREG = "fechaRegistro";    
    public static String CLIENTES_LASTCOMPRA = "ultimaCompra";    
    
    // DETALLE VENTA
    
    
    // CABECERA VENTA
}
