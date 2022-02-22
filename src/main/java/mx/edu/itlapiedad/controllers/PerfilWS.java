package mx.edu.itlapiedad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mx.edu.itlapiedad.dao.PerfilJDBC;
import mx.edu.itlp.models.Perfil;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH})
public class PerfilWS {
	@Autowired
	PerfilJDBC repo;
	
	@PostMapping("/cuentas/{cuenta_id}/perfiles")
	public ResponseEntity<?> insertar(@RequestBody Perfil nuevo_perfil){
		try {
			int id = repo.insertar(nuevo_perfil);
			return new ResponseEntity<>(id, HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
	
	@PutMapping("/perfiles/{perfil_id}")
	public ResponseEntity<?> modificar(@PathVariable int perfil_id, @RequestBody Perfil perfil){
		repo.modificar(perfil_id, perfil);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@DeleteMapping("/perfiles/{perfil_id}")
	public ResponseEntity<?> desactivar(@PathVariable int perfil_id){
		repo.desactivar(perfil_id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
