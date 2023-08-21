package club.j3studios.system.model;

public class Categoria {

	private Integer idCategoria;
	private String descripcion;
	
	public Categoria() {
		this.idCategoria = 0;
		this.descripcion = "";
	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
