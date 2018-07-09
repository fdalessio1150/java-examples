package com.example.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.example.admin.model.Client;
import com.example.admin.model.ClientRequest;
import com.example.admin.model.ClientRequestList;
import com.example.admin.repository.ClientRepository;
import com.example.admin.repository.ClientRepositoryImpl;

@Component
@Lazy
public class ClientService {
	
	private ClientRepositoryImpl clientRepository;
	
	@Autowired
	public ClientService(ClientRepositoryImpl clientRepository) {
		this.clientRepository = clientRepository;
	}
	
	public List<Client> retrieveClients(String name) {
		if (name == null) {	
			List<ClientRepository> client = clientRepository.retrieveAllClients().all();
			return toClient(client);
		} else {
			List<ClientRepository> client = clientRepository.retrieveClientByName(name).all();
			return toClient(client);
		}
	}
	
	public void createClient(ClientRequestList clientList) {
		if (clientList.getData().size() > 0) {
			for (ClientRequest client : clientList.getData()) {
				clientRepository.createClient(toClientRepository(client));
			}
		}
	}
	
	private List<Client> toClient(List<ClientRepository> clientRepository) {
		List<Client> clientList = new ArrayList<>();
		
		for (ClientRepository client : clientRepository) {
			Client clientModel = Client.builder().withId(client.getId())
					.withName(client.getName())
					.withSex(client.getSex())
					.build();
			
			clientList.add(clientModel);
		}
		
		return clientList;
	}
	
	private ClientRepository toClientRepository(ClientRequest client) {
		return new ClientRepository(client.getName(), 
					client.getSex(), 
					client.getId());
	}

}
