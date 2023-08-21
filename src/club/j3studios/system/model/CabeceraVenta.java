package club.j3studios.system.model;

/**
 * @author ediso
 */
public class CabeceraVenta {
    
    //Atributos
    private int idCabeceraventa;
    private int idCaja;
    private int idUsuario;
    private int idCliente; 
    private double valorPagar;
    private String fechaVenta;
    private int estado;
    
    //constructor
    public CabeceraVenta(){
        this.idCabeceraventa = 0;
        this.idCaja = 0;
        this.idUsuario = 0;
        this.idCliente = 0;
        this.valorPagar = 0.0;
        this.fechaVenta = "";
        this.estado = 0;
    }

    public CabeceraVenta(int idCabeceraventa, int idCaja, int idUsuario, int idCliente, double valorPagar, String fechaVenta, int estado) {
        this.idCabeceraventa = idCabeceraventa;
        this.idCaja = idCaja;
        this.idUsuario = idUsuario;
        this.idCliente = idCliente;
        this.valorPagar = valorPagar;
        this.fechaVenta = fechaVenta;
        this.estado = estado;
    }

    public int getIdCabeceraventa() {
        return idCabeceraventa;
    }

    public void setIdCabeceraventa(int idCabeceraventa) {
        this.idCabeceraventa = idCabeceraventa;
    }

    public int getIdCaja() {
        return idCaja;
    }

    public void setIdCaja(int idCaja) {
        this.idCaja = idCaja;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public double getValorPagar() {
        return valorPagar;
    }

    public void setValorPagar(double valorPagar) {
        this.valorPagar = valorPagar;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    //toString 

    /*@Override
    public String toString() {
        return "CabeceraVenta{" + "idCabeceraventa=" + idCabeceraventa + ", idCliente=" + idCliente + ", valorPagar=" + valorPagar + ", fechaVenta=" + fechaVenta + ", estado=" + estado + '}';
    }
    */
}
