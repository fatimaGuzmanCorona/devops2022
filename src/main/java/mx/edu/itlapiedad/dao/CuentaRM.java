package mx.edu.itlapiedad.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import mx.edu.itlp.models.Cuenta;

public class CuentaRM implements RowMapper<Cuenta>{
	
	@Override
	public Cuenta mapRow(ResultSet rs, int rowNum) throws SQLException{
		Cuenta cuenta = new Cuenta();
		cuenta.setId(rs.getInt("id"));
		cuenta.setEmail(rs.getString("email"));
		cuenta.setPassword(rs.getString("password"));
		cuenta.setActiva(rs.getInt("activa"));
		cuenta.setFecha_ultimo_pago(rs.getString("fecha_ultimo_pago"));
		cuenta.setNombre(rs.getString("nombre"));
		cuenta.setApellido(rs.getString("apellido"));
		cuenta.setNumero_tarjeta(rs.getString("numero_tarjeta"));
		cuenta.setFecha_vencimiento(rs.getString("fecha_vencimiento"));
		cuenta.setCodigo_seguridad(rs.getString("codigo_seguridad"));
		cuenta.setTipo_tarjeta(rs.getString("tipo_tarjeta"));
		cuenta.setPlanes_id(rs.getInt("planes_id"));
		cuenta.setEliminado(rs.getString("eliminado"));
		return cuenta;
	}

}
