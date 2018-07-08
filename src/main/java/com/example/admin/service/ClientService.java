package com.example.admin.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

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

import rx.Observable;
import rx.Scheduler;
import rx.schedulers.Schedulers;

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
				int found = verify(client);
				if (clientList.getAll().size() > 0 && found == 0) {
					repository.createClient(toClientRepository(client));
				}
			}
		}
	}
	
	private int verify(ClientRequest client) {
		ListenableFuture<Client> asyncClient = repository.retrieveClientByName(client.getName());
		return Observable.from(asyncClient).count();
	}
		
	private Client toClientRepository(ClientRequest client) {
		return new Client(client.getName(), 
					client.getSex(), 
					client.getId());
	}

}
