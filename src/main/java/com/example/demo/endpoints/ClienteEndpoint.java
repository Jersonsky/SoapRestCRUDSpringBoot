package com.example.demo.endpoints;

import java.sql.SQLException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.example.demo.services.ClienteServiceSoap;
import com.example.demo.soap.Cliente;
import com.example.demo.soap.DeleteClientRequest;
import com.example.demo.soap.DeleteClientResponse;
import com.example.demo.soap.GetClienteByIdRequest;
import com.example.demo.soap.GetClienteByIdResponse;
import com.example.demo.soap.SaveClientRequest;
import com.example.demo.soap.SaveClientResponse;
import com.example.demo.soap.ServiceStatus;
import com.example.demo.soap.UpdateClientRequest;
import com.example.demo.soap.UpdateClientResponse;

@Endpoint
public class ClienteEndpoint {
	
	public final static String  NAMESPACE = "http://www.baeldung.com/springsoap";
	
	@Autowired
	private ClienteServiceSoap clienteServiceSoap;	

	@PayloadRoot(namespace = NAMESPACE, localPart = "getClienteByIdRequest")
	@ResponsePayload
	public GetClienteByIdResponse getArticle(@RequestPayload GetClienteByIdRequest request) {
		GetClienteByIdResponse response = new GetClienteByIdResponse();
		Cliente cliente = new Cliente();
		BeanUtils.copyProperties(clienteServiceSoap.getById(request.getId()), cliente);
		response.setCliente(cliente);
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE, localPart = "saveClientRequest")
	@ResponsePayload
	public SaveClientResponse saveClientes(@RequestPayload SaveClientRequest request) throws BeansException, SQLException {
		SaveClientResponse response = new SaveClientResponse();
		Cliente cli = new Cliente();
		cli = request.getCliente();
		BeanUtils.copyProperties(clienteServiceSoap.saveCli(cli), cli);
		response.setEstado("exitoso");
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE, localPart = "updateClientRequest")
	@ResponsePayload
	public UpdateClientResponse updateArticle(@RequestPayload UpdateClientRequest request) {
		Cliente cliente = new Cliente();
		BeanUtils.copyProperties(request.getCliente(), cliente);
		clienteServiceSoap.updateCli(cliente);
    	ServiceStatus serviceStatus = new ServiceStatus();
    	serviceStatus.setStatusCode("Exito");
    	serviceStatus.setMessage("Contenido actualizado");
    	UpdateClientResponse response = new UpdateClientResponse();
    	response.setServiceStatus(serviceStatus);
    	return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE, localPart = "deleteClientRequest")
	@ResponsePayload
	public DeleteClientResponse deleteArticle(@RequestPayload DeleteClientRequest request) {
		Cliente cliente = clienteServiceSoap.getById(request.getId());
    	ServiceStatus serviceStatus = new ServiceStatus();
		if (cliente == null ) {
	    	serviceStatus.setStatusCode("Fallo");
	    	serviceStatus.setMessage("Contenido no disponible");
		} else {
			clienteServiceSoap.deleteCli(cliente);
	    	serviceStatus.setStatusCode("Exitoso");
	    	serviceStatus.setMessage("Se ha borrado");
		}
    	DeleteClientResponse response = new DeleteClientResponse();
    	response.setServiceStatus(serviceStatus);
		return response;
	}

}
