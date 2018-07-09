package com.example.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.example.admin.model.Client;
import com.example.admin.model.ClientRequestList;
import com.example.admin.model.ClientResponse;
import com.example.admin.model.ToModel;

@Component
@Lazy
public class AdminService {

	private ClientService service;
	private ToModel model;
	
	@Autowired
	public AdminService(ClientService service, ToModel model) {
		this.service = service;
		this.model = model;
	}
	
	public List<ClientResponse> retrieveClients(String name) {
		List<Client> clientList = service.retrieveClients(name);
		return model.toClientResponse(clientList);
	}
	
	public void createClient(ClientRequestList clientList) {
		service.createClient(clientList);
	}
	
}
