package club.j3studios.system.model;

/**
 * @author ediso
 */
public class DetalleVenta {

    //Atributos
    private int idDetalleVenta;
    private int idCabeceraVenta;
    private int idProducto;
    
    //esta de mas
    private String codigo;
    private String nombre;
    private Double cantidad;
    private double precioVenta;
    private double subTotal;
    private double iva;
    private double totalPagarUsd;
    private double totalPagarBs;

    //Contructor
    public DetalleVenta() {
        this.idDetalleVenta = 0;
        this.idCabeceraVenta = 0;
        this.idProducto = 0;
        this.codigo = "";
        this.nombre = "";
        this.cantidad = 0.0;
        this.precioVenta = 0.0;
        this.subTotal = 0.0;
        this.iva = 0.0;
        this.totalPagarUsd = 0.0;
        this.totalPagarBs = 0.0;
    }

	public DetalleVenta(int idDetalleVenta, int idCabeceraVenta, int idProducto, String codigo, String nombre,
			Double cantidad, double precioVenta, double subTotal, double iva, double totalPagarUsd, double totalPagarBs) {
		super();
		this.idDetalleVenta = idDetalleVenta;
		this.idCabeceraVenta = idCabeceraVenta;
		this.idProducto = idProducto;
		this.codigo = codigo;
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.precioVenta = precioVenta;
		this.subTotal = subTotal;
		this.iva = iva;
		this.totalPagarUsd = totalPagarUsd;
		this.totalPagarBs = totalPagarBs;
	}

	public int getIdDetalleVenta() {
		return idDetalleVenta;
	}

	public void setIdDetalleVenta(int idDetalleVenta) {
		this.idDetalleVenta = idDetalleVenta;
	}

	public int getIdCabeceraVenta() {
		return idCabeceraVenta;
	}

	public void setIdCabeceraVenta(int idCabeceraVenta) {
		this.idCabeceraVenta = idCabeceraVenta;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	public double getIva() {
		return iva;
	}

	public void setIva(double iva) {
		this.iva = iva;
	}

	public double getTotalPagarUsd() {
		return totalPagarUsd;
	}

	public void setTotalPagarUsd(double totalPagarUsd) {
		this.totalPagarUsd = totalPagarUsd;
	}

	public double getTotalPagarBs() {
		return totalPagarBs;
	}

	public void setTotalPagarBs(double totalPagarBs) {
		this.totalPagarBs = totalPagarBs;
	}
     
	@Override
    public String toString() {
        return "DetalleVenta{" + "idDetalleVenta=" + idDetalleVenta + ", idCabeceraVenta=" + idCabeceraVenta + ", idProducto=" + idProducto + ", codigo=" + codigo + ", nombre=" + nombre + ", cantidad=" + cantidad + ", precioVenta=" + precioVenta + ", subTotal=" + subTotal + ", iva=" + iva + ", totalPagarUsd=" + totalPagarUsd + ", totalPagarBs=" + totalPagarBs + '}';
    }

}
