package club.j3studios.system.model;

public class Producto {

    private Integer idProducto;
    private String codigo;
    private String descripcion;
    private Integer idCategoria;
    private Integer idMarca;
    private Double precioCosto;
    private Double ganancia;
    private Double precioVenta;
    private Boolean controlStock;
    private Integer stock;
    private Integer minStock;
    private Integer maxStock;
    private String unidadMedida;
    private Boolean exento;
    
    public Producto() {
        this.idProducto = 0;
        this.codigo = "";
        this.descripcion = "";
        this.idCategoria = 0;
        this.idMarca = 0;
        this.precioCosto = 0.0;
        this.ganancia = 0.0;
        this.precioVenta = 0.0;
        this.controlStock = true;
        this.stock = 0;
        this.minStock = 0;
        this.maxStock = 0;
        this.unidadMedida = "";
        this.exento = true;
    }

    public Producto(Integer idProducto, String codigo, String descripcion, Integer idCategoria, Integer idMarca, Double precioCosto, Double ganancia, Double precioVenta, Boolean controlStock, Integer stock, Integer minStock, Integer maxStock, String unidadMedida, Boolean exento) {
        this.idProducto = idProducto;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.idCategoria = idCategoria;
        this.idMarca = idMarca;
        this.precioCosto = precioCosto;
        this.ganancia = ganancia;
        this.precioVenta = precioVenta;
        this.controlStock = controlStock;
        this.stock = stock;
        this.minStock = minStock;
        this.maxStock = maxStock;
        this.unidadMedida = unidadMedida;
        this.exento = exento;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Integer getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Integer idMarca) {
        this.idMarca = idMarca;
    }

    public Double getPrecioCosto() {
        return precioCosto;
    }

    public void setPrecioCosto(Double precioCosto) {
        this.precioCosto = precioCosto;
    }

    public Double getGanancia() {
        return ganancia;
    }

    public void setGanancia(Double ganancia) {
        this.ganancia = ganancia;
    }

    public Double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Boolean getControlStock() {
        return controlStock;
    }

    public void setControlStock(Boolean controlStock) {
        this.controlStock = controlStock;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getMinStock() {
        return minStock;
    }

    public void setMinStock(Integer minStock) {
        this.minStock = minStock;
    }

    public Integer getMaxStock() {
        return maxStock;
    }

    public void setMaxStock(Integer maxStock) {
        this.maxStock = maxStock;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public Boolean getExento() {
        return exento;
    }

    public void setExento(Boolean exento) {
        this.exento = exento;
    }
    
    
        
}
