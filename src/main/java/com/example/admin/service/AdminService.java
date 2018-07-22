package com.example.admin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
		return service.retrieveClients(name)
				.parallelStream()
				.map(this::toClientResponse)
				.collect(Collectors.toList());
	}

	public void createClient(ClientRequestList clientList) {
	    service.createClient(clientList);
	}
	
	private ClientResponse toClientResponse(Client client) {
		return ClientResponse.builder()
                .withClient(client)
				.build();
	}
	
}
