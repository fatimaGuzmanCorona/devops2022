package mx.edu.itlapiedad.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mx.edu.itlapiedad.dao.CuentaJDBC;
import mx.edu.itlp.models.Cuenta;
import mx.edu.itlp.models.Perfil;

@RestController
@RequestMapping("/cuentas")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH})
public class CuentaWS {
	@Autowired
	CuentaJDBC repo;
	
	@PostMapping("/planes/{plan_id}/cuentas")
	public ResponseEntity<?> insertar(@RequestBody Cuenta nueva_cuenta){
		try {
			int id = repo.insertar(nueva_cuenta);
			return new ResponseEntity<>(id, HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
	@PutMapping("/cuentas/{cuenta_id}")
	public ResponseEntity<?> modificar(@PathVariable int cuenta_id, @RequestBody Cuenta cuenta){
		repo.modificar(cuenta_id, cuenta);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping("/planes/{plan_id}/cuentas/{cuenta_id}")
	public ResponseEntity<?> modificarPlan(@PathVariable int cuenta_id, @RequestBody Cuenta cuenta1){
		repo.modificarPlan(cuenta_id, cuenta1);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@DeleteMapping("/cuentas/{cuenta_id}")
	public ResponseEntity<?> desactivar(@PathVariable int cuenta_id){
		repo.desactivar(cuenta_id);
		return new ResponseEntity<>(HttpStatus.OK);
	}	
	
	@PostMapping("/login")
	public ResponseEntity<?> verificar(@RequestBody Cuenta login_cuenta){
		try {
			Cuenta resultado = repo.verificar(login_cuenta);
			return new ResponseEntity<Cuenta>(resultado, HttpStatus.OK);
		} catch (DataAccessException e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
	@GetMapping("/cuentas/{cuenta_id}/perfiles")
	public ResponseEntity<?> consultarperfiles(@PathVariable("cuenta_id") int cuenta_id){
		List<Perfil> resultado;
		try {
			resultado = repo.consultarperfil(cuenta_id);
			return new ResponseEntity<List<Perfil>>(resultado,HttpStatus.OK);
		}catch (DataAccessException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
	}
	
}