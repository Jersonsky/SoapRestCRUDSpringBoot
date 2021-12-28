package com.example.demo.services;


import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.ClienteRepo;
import com.example.demo.soap.Cliente;

@Service
public class ClienteServiceSoapImp implements ClienteServiceSoap{

	@Autowired
	ClienteRepo clienteRepo;

	@Override
	public Cliente getById(int id) {
		Cliente cli = clienteRepo.findById(id);
		return cli;
	}

	@Override
	public String saveCli(Cliente cliente) throws SQLException {
		try {
			clienteRepo.saveCli(cliente.getId(), cliente.getCedula(), cliente.getNombre(), cliente.getApellido(), cliente.getTelefono(), cliente.getCorreo());
			return "Exitoso";
		}catch(Error err) {
			return "Fallido";
		}		
	}

	@Override
	public void updateCli(Cliente cliente) {
		clienteRepo.save(cliente);
		
	}

	@Override
	public void deleteCli(Cliente cliente) {
		clienteRepo.delete(cliente);
		
	}
	
	
	
	

}
