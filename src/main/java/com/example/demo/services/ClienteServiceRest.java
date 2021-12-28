package com.example.demo.services;


import java.util.List;

import com.example.demo.soap.Cliente;

public interface ClienteServiceRest {
	
	Cliente saveCliente(Cliente cliente);
	
	List<Cliente> getAllCli();
	
	void deleteCli(int id);
	

}
