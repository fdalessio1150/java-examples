package com.example.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.example.admin.model.Client;
import com.example.admin.model.ClientRequest;
import com.example.admin.model.ClientRequestList;
import com.example.admin.model.ToModel;
import com.example.admin.repository.ClientRepository;
import com.example.admin.repository.ClientRepositoryImpl;
import com.example.admin.repository.ToRepository;

@Component
@Lazy
public class ClientService {
	
	private ClientRepositoryImpl clientRepository;
	private ToRepository repository;
	private ToModel model;
	
	@Autowired
	public ClientService(ClientRepositoryImpl clientRepository, ToRepository repository, ToModel model) {
		this.clientRepository = clientRepository;
		this.repository = repository;
		this.model = model;
	}
	
	public List<Client> retrieveClients(String name) {
		if (name == null) {	
			List<ClientRepository> client = clientRepository.retrieveAllClients().all();
			return model.toClient(client);
		} else {
			List<ClientRepository> client = clientRepository.retrieveClientByName(name).all();
			return model.toClient(client);
		}
	}
	
	public void createClient(ClientRequestList clientList) {
		if (clientList.getData().size() > 0) {
			for (ClientRequest client : clientList.getData()) {
				clientRepository.createClient(repository.toClientRepository(client));
			}
		}
	}

}
