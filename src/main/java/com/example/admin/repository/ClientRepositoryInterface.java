package com.example.admin.repository;

import com.datastax.driver.mapping.Result;

public interface ClientRepositoryInterface {

	public Result<Client> retrieveAllClients();
	
	public Result<Client> retrieveClientByName(String name);
	
	public void createClient(Client client);
}
