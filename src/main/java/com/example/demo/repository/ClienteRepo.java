package com.example.demo.repository;


import java.sql.SQLException;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.soap.Cliente;

public interface ClienteRepo extends CrudRepository<Cliente, Integer>{
	
	Cliente findById(int id);
	
	static String insert ="INSERT INTO cliente "
			+ "VALUES(:id,"
			+ ":cedula,"
			+ ":nombre,"
			+ ":apellido,"
			+ ":telefono,"
			+ ":correo);";		
	@Modifying
	@Transactional
	@Query(value = insert, nativeQuery = true)
	void saveCli(@Param("id") Integer id, @Param("cedula") String cedula, @Param("nombre") String nombre, 
			@Param("apellido") String apellido, @Param("telefono") String telefono, @Param("correo") String correo) throws SQLException;

}
