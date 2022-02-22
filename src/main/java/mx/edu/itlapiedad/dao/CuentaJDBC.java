package mx.edu.itlapiedad.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import mx.edu.itlp.models.Cuenta;
import mx.edu.itlp.models.Perfil;

@Repository
public class CuentaJDBC {
	
	@Autowired
	private JdbcTemplate conexion;
	
	public int insertar(Cuenta nueva_cuenta) {
		String sql = "INSERT INTO cuentas(email,password,activa, fecha_ultimo_pago, nombre, apellido,numero_tarjeta,"
				+ "fecha_vencimiento, codigo_seguridad, tipo_tarjeta, planes_id, eliminado) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		conexion.update(sql, nueva_cuenta.getEmail(), nueva_cuenta.getPassword(), nueva_cuenta.getActiva(), nueva_cuenta.getFecha_ultimo_pago(),nueva_cuenta.getNombre(),
				nueva_cuenta.getApellido(), nueva_cuenta.getNumero_tarjeta(), nueva_cuenta.getFecha_vencimiento(), 
				nueva_cuenta.getCodigo_seguridad(), nueva_cuenta.getTipo_tarjeta(), nueva_cuenta.getPlanes_id(), nueva_cuenta.getEliminado());
		sql = "SELECT LAST_INSERT_ID()";
		return conexion.queryForObject(sql, Integer.class);
	}
	
	public void modificar(int id, Cuenta cuenta) {
		String sql = "UPDATE cuentas SET email = ?, password = ?, activa = ?, nombre = ?, apellido=?,numero_tarjeta = ?,"
				+ "fecha_vencimiento = ?, codigo_seguridad = ?, tipo_tarjeta = ?, planes_id = ?, eliminado = ?, modificado = NOW() WHERE id = ?";
		conexion.update(sql, cuenta.getEmail(), cuenta.getPassword(), cuenta.getActiva(), cuenta.getNombre(), cuenta.getApellido(), 
				cuenta.getNumero_tarjeta(), cuenta.getFecha_vencimiento(), 
				cuenta.getCodigo_seguridad(), cuenta.getTipo_tarjeta(), cuenta.getPlanes_id(), cuenta.getEliminado(), id);
	}
	
	public void modificarPlan(int id, Cuenta cuenta1) {
		String sql = "UPDATE cuentas SET planes_id = ?, modificado = NOW() WHERE id = ?";
		conexion.update(sql, cuenta1.getPlanes_id(), id);
	}
	
	public void desactivar(int id) {
		String sql = "UPDATE cuentas SET activa = 0, eliminado = NOW() WHERE id = ?";
		conexion.update(sql, id);
	}
	
	public Cuenta verificar(Cuenta login_cuenta) {
		String sql = "SELECT * FROM cuentas WHERE email = ? AND password = ?";
		return conexion.queryForObject(sql, new CuentaRM(),login_cuenta.getEmail(),login_cuenta.getPassword());
	}
	
	public List<Perfil> consultarperfil(int cuentas_id) {
		String sql = "SELECT * FROM perfiles_usuarios WHERE cuentas_id = ?";
		return conexion.query(sql,new PerfilRM(),cuentas_id);
	}

}
