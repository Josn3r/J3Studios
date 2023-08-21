package club.j3studios.system.model;

/**
 *
 * @author ediso
 */
public class Cliente {

    //Atributos
    private int idCliente;
    private String nombre;
    private String cedula;
    private String genero;
    private String telefono;
    private String direccion;
    private String correo;
    private int compras;
    private Double valorTotal;
    private String fechaRegistro;
    private String ultimaCompra;

    //Constructor
    public Cliente() {
        this.idCliente = 0;
        this.nombre = "";
        this.cedula = "";
        this.genero = "";
        this.telefono = "";
        this.direccion = "";
        this.correo = "";
        this.compras = 0;
        this.valorTotal = 0.0;
        this.fechaRegistro = "";
        this.ultimaCompra = "";
    }

    public Cliente(int idCliente, String nombre, String cedula, String genero, String telefono, String direccion, String correo, int compras, Double valorTotal, String fechaRegistro, String ultimaCompra) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.cedula = cedula;
        this.genero = genero;
        this.telefono = telefono;
        this.direccion = direccion;
        this.correo = correo;
        this.compras = compras;
        this.valorTotal = valorTotal;
        this.fechaRegistro = fechaRegistro;
        this.ultimaCompra = ultimaCompra;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getCompras() {
        return compras;
    }

    public void setCompras(int compras) {
        this.compras = compras;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getUltimaCompra() {
        return ultimaCompra;
    }

    public void setUltimaCompra(String ultimaCompra) {
        this.ultimaCompra = ultimaCompra;
    }
    
    
    
}
