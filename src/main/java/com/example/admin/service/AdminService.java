package com.example.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.example.admin.model.Client;
import com.example.admin.model.ClientRequestList;
import com.example.admin.model.ClientResponse;

@Component
@Lazy
public class AdminService {

	private ClientService service;
	
	@Autowired
	public AdminService(ClientService service) {
		this.service = service;
	}
	
	public List<ClientResponse> retrieveClients(String name) {
		List<Client> clientList = service.retrieveClients(name);
		return toClientResponse(clientList);
	}
	
	public void createClient(ClientRequestList clientList) {
		service.createClient(clientList);
	}
	
	private List<ClientResponse> toClientResponse(List<Client> clientList) {
		List<ClientResponse> clientResponseList = new ArrayList<>();
		
		for (Client client : clientList) {
			ClientResponse clientResponse = ClientResponse.builder()
												.withClient(client)
												.build();
			clientResponseList.add(clientResponse);
		}
		
		return clientResponseList;
	}
	
}
