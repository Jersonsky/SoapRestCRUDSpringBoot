package com.example.demo.services;

import java.sql.SQLException;

import com.example.demo.soap.Cliente;


public interface ClienteServiceSoap {
	
	Cliente getById(int id);
	
	String saveCli(Cliente cliente) throws SQLException;
	
	void updateCli(Cliente cliente);
	
    void deleteCli(Cliente cliente);

}
