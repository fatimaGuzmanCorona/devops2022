package mx.edu.itlapiedad.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import mx.edu.itlp.models.Perfil;


@Repository
public class PerfilJDBC {
	
	@Autowired
	private JdbcTemplate conexion;
	
	public List<Perfil> consultar(){
		String sql = "SELECT * FROM perfiles_usuarios WHERE activo=1";
		return conexion.query(sql, new PerfilRM());
	}
	
	public Perfil buscar(int id) {
		String sql = "SELECT * FROM perfiles_usuarios WHERE id=? AND activo=1";
		return conexion.queryForObject(sql, new PerfilRM(), id);
		
	}
	
	public List<Perfil> consultarperfil(int cuentas_id) {
		String sql = "SELECT * FROM perfiles_usuarios WHERE activo=1 AND cuentas_id=?";
		return conexion.query(sql,new PerfilRM(),cuentas_id);
	}
	
	public void desactivar(int id) {
		String sql = "UPDATE perfiles_usuarios SET activo=0 WHERE id =?";
		conexion.update(sql, id);
	}
	
	public void modificar(int id, Perfil perfil) {
		String sql = "UPDATE perfiles_usuarios SET nombre = ?, idioma = ?,"
				+ "clasificacion_edad = ?"
				+ "WHERE id = ?";
		conexion.update(sql, perfil.getNombre(), perfil.getIdioma(), 
						perfil.getClasificacion_edad(), id);
	}
	
	public int insertar(Perfil nuevo_perfil) {
		String sql = "INSERT INTO perfiles_usuarios ( nombre, idioma, clasificacion_edad, cuentas_id ) VALUES ( ?, ?, ?, ? )";
		conexion.update(sql, nuevo_perfil.getNombre(), nuevo_perfil.getIdioma(), nuevo_perfil.getClasificacion_edad(), 
				nuevo_perfil.getCuentas_id());
		sql = "SELECT LAST_INSERT_ID()";
		return conexion.queryForObject(sql, Integer.class);
	}
}
