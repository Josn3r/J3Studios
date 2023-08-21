package club.j3studios.system.model;

public class Cajas {

    private Integer idCaja;
    private String nombre;
    private Integer idUsuario;
    
    // INFORMACIÓN PARA GUARDAR EN UN LOG:    
    private String dateOpen;
    private String dateClose;
    
    private String facturas;
    private Double totalFacturado;
    
    private Double valueBs;
    private Double valueUsd;
	
    public Cajas() {
        this.idCaja = 0;
        this.nombre = "";
        this.idUsuario = 0;
        //
        this.dateOpen = "";
        this.dateClose = "";
        this.facturas = "";
        this.totalFacturado = 0.0;
        this.valueBs = 0.0;
        this.valueUsd = 0.0;
    }  

    public Integer getIdCaja() {
        return idCaja;
    }

    public void setIdCaja(Integer idCaja) {
        this.idCaja = idCaja;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getDateOpen() {
        return dateOpen;
    }

    public void setDateOpen(String dateOpen) {
        this.dateOpen = dateOpen;
    }

    public String getDateClose() {
        return dateClose;
    }

    public void setDateClose(String dateClose) {
        this.dateClose = dateClose;
    }

    public String getFacturas() {
        return facturas;
    }

    public void setFacturas(String facturas) {
        this.facturas = facturas;
    }

    public Double getTotalFacturado() {
        return totalFacturado;
    }

    public void setTotalFacturado(Double totalFacturado) {
        this.totalFacturado = totalFacturado;
    }

    public Double getValueBs() {
        return valueBs;
    }

    public void setValueBs(Double valueBs) {
        this.valueBs = valueBs;
    }

    public Double getValueUsd() {
        return valueUsd;
    }

    public void setValueUsd(Double valueUsd) {
        this.valueUsd = valueUsd;
    }
    
    
    
}
