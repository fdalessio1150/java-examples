package com.example.admin.repository;

import com.datastax.driver.mapping.Result;

public interface ClientRepositoryInterface {

	public Result<ClientRepository> retrieveAllClients();
	
	public Result<ClientRepository> retrieveClientByName(String name);
	
	public void createClient(ClientRepository client);
}
