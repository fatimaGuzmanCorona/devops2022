package mx.edu.itlapiedad.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UusariosJDBC {

	@Autowired
	JdbcTemplate conexion;
	public boolean login (String email, String contrasena) {
		// password palabra reservada, 0 si no lo encuentra, 1 si es correcto
		String sql = "SELECT COUNT(*) "
				+ "FROM cuentas "
				+ "WHERE email = ? "
				+ "AND `password` = ? AND activa = 1";
		return conexion.queryForObject(sql, Integer.class, email, contrasena) == 1;
		
		
	} // FIN public boolean
} // FINC CLASE