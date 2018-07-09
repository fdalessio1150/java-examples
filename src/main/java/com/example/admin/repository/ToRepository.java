package com.example.admin.repository;

import org.springframework.stereotype.Component;

import com.example.admin.model.ClientRequest;

@Component
public class ToRepository {
	
	public ClientRepository toClientRepository(ClientRequest client) {
		return new ClientRepository(client.getName(), 
					client.getSex(), 
					client.getId());
	}

}
