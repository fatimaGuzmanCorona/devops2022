package mx.edu.itlp.models;

public class Perfil {
	private int id;
	private String nombre;
	private String idioma;
	private String clasificacion_edad;
	private int cuentas_id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getIdioma() {
		return idioma;
	}
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	public String getClasificacion_edad() {
		return clasificacion_edad;
	}
	public void setClasificacion_edad(String clasificacion_edad) {
		this.clasificacion_edad = clasificacion_edad;
	}
	public int getCuentas_id() {
		return cuentas_id;
	}
	public void setCuentas_id(int cuentas_id) {
		this.cuentas_id = cuentas_id;
	}
	
	
}
