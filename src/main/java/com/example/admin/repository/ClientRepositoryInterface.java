package com.example.admin.repository;

import com.datastax.driver.mapping.Result;
import com.google.common.util.concurrent.ListenableFuture;

public interface ClientRepositoryInterface {

	public ListenableFuture<Result<Client>> retrieveAllClients();
	
	public ListenableFuture<Client> retrieveClientByName(String name);
	
	public void createClient(Client client);
}
