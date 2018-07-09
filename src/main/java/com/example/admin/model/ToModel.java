package com.example.admin.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.admin.repository.ClientRepository;

@Component
public class ToModel {
	
	public List<Client> toClient(List<ClientRepository> clientRepository) {
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
	
	public List<ClientResponse> toClientResponse(List<Client> clientList) {
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
