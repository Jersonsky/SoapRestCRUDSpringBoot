package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.soap.Cliente;

@Repository
@Transactional
public interface ClienteRepoRest extends JpaRepository<Cliente, Integer>{

	@Query(value = "SELECT id, cedula, nombre, apellido, telefono, correo FROM cliente ", nativeQuery = true)
	public List<Cliente> getAllQuery();
	
}
