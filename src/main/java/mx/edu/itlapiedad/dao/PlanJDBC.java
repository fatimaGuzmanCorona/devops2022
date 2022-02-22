	package mx.edu.itlapiedad.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import mx.edu.itlp.models.Plan;
import java.util.List;

@Repository
public class PlanJDBC {
	
	@Autowired
	private JdbcTemplate conexion;
	
	public List<Plan> consultar(){
		String sql = "SELECT * FROM planes WHERE activo = 1";
		return conexion.query(sql, new PlanRM());
	}
	
	public Plan buscar(int id) {
		String sql = "SELECT * FROM planes WHERE id=? AND activo = 1";
		return conexion.queryForObject(sql, new PlanRM(), id);
	}
	
	public void desactivar(int id) {
		String sql = "UPDATE planes SET activo = 0, eliminado = NOW() WHERE id = ?";
		conexion.update(sql, id);
	}

	public void modificar(int id, Plan plan) {
		String sql = "UPDATE planes SET descripcion = ?, precio_mensual = ?,"
				+ "cantidad_dispositivos = ?, modificado = NOW()"
				+ "WHERE id = ?";
		conexion.update(sql, plan.getDescripcion(), plan.getPrecio_mensual(), 
				plan.getCantidad_dispositivos(), id);
	}

	
	public int insertar(Plan nuevo_plan) {
		String sql = "INSERT INTO planes(descripcion, precio_mensual, calidad_video,"
				+ "resolucion, cantidad_dispositivos) VALUES (?, ?, ?, ?, ?)";
		conexion.update(sql, nuevo_plan.getDescripcion(), nuevo_plan.getPrecio_mensual(),
				nuevo_plan.getCalidad_video(), nuevo_plan.getResolucion(), 
				nuevo_plan.getCantidad_dispositivos());
		sql = "SELECT LAST_INSERT_ID()";
		return conexion.queryForObject(sql, Integer.class);
	}
}
