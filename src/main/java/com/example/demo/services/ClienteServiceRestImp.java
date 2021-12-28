package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.ClienteRepoRest;
import com.example.demo.soap.Cliente;

@Service
public class ClienteServiceRestImp implements ClienteServiceRest{

	@Autowired
	ClienteRepoRest clienteRepoRest;

	@Override
	public Cliente saveCliente(Cliente cliente) {
		return clienteRepoRest.save(cliente);
	}

	@Override
	public List<Cliente> getAllCli() {
		return clienteRepoRest.getAllQuery();
	}

	@Override
	public void deleteCli(int id) {
		clienteRepoRest.deleteById(id);
	}
	
	
}
