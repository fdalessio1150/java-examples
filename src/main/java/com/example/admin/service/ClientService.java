package com.example.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.datastax.driver.core.ResultSet;
import com.example.admin.model.ClientRequest;
import com.example.admin.model.ClientRequestList;
import com.example.admin.repository.Client;
import com.example.admin.repository.ClientRepositoryImpl;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

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
		
		Client found = clientExists(clientList);
		
			
		if (clientList.getAll().size() > 0 && found == null) {
			for (ClientRequest client : clientList.getAll()) {
				Client c = toClientRepository(client);
				repository.createClient(c);
			}
		}
	}
	
	private Client clientExists(ClientRequestList clientList) {
		
		if (clientList.getAll().size() > 0) {
			
			for (ClientRequest client : clientList.getAll()) {
				
				ListenableFuture<Client> future = repository.retrieveClientByName(client.getName());
				Futures.addCallback(future, new FutureCallback<Client>() {
					@Override
					public void onSuccess(Client result) {

					}

					@Override
					public void onFailure(Throwable t) {

					}
									
				});
			}
		}
		
	}
	
	private Client toClientRepository(ClientRequest client) {
		return new Client(client.getName(), 
					client.getSex(), 
					client.getId());
	}

}
