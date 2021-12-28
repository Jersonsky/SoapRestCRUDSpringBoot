package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.services.ClienteServiceRest;
import com.example.demo.soap.Cliente;

@RestController
@RequestMapping("clientes")
public class ClienteRestController {

	@Autowired
	private ClienteServiceRest clienteRest;
	
	@PostMapping("/save")
	public ResponseEntity<?> saveCliente(@RequestBody Cliente cli){
		try {
			clienteRest.saveCliente(cli);
			return new ResponseEntity<>("Cliente insertado", HttpStatus.OK);
		}catch(Error err) {
			return new ResponseEntity<>("Error insertando el cliente", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/show")
	public List<Cliente> getAllClis(){
		return clienteRest.getAllCli();
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteCli(@PathVariable("id") int id){
		clienteRest.deleteCli(id);
		return new ResponseEntity<String>("El cliente "+ id +" se ha borrado.", HttpStatus.OK);
	}
	
}
