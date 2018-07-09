package com.example.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.example.admin.model.ClientRequest;
import com.example.admin.model.ClientRequestList;
import com.example.admin.repository.Client;
import com.example.admin.repository.ClientRepositoryImpl;

@Component
@Lazy
public class ClientService {
	
	private ClientRepositoryImpl repository;
	
	@Autowired
	public ClientService(ClientRepositoryImpl repository) {
		this.repository = repository;
	}
	
	public void retrieveClients(String name) {
		if (name == null) {
			repository.retrieveAllClients();
		} else {
			repository.retrieveClientByName(name);
		}
	}
	
	public void createClient(ClientRequestList clientList) {
		if (clientList.getAll().size() > 0) {
			for (ClientRequest client : clientList.getAll()) {
				Client found = repository.retrieveClientByName(client.getName()).one();
				if (clientList.getAll().size() > 0 && found == null) {
					repository.createClient(toClientRepository(client));
				}
			}
		}
	}
			
	private Client toClientRepository(ClientRequest client) {
		return new Client(client.getName(), 
					client.getSex(), 
					client.getId());
	}

}
