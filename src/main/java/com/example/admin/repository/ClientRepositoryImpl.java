package com.example.admin.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.datastax.driver.mapping.Result;
import com.google.common.util.concurrent.ListenableFuture;

@Repository
@Lazy
public class ClientRepositoryImpl implements ClientRepositoryInterface {
	
	private final ClientAccessor clientAccessor;
	private final Mapper<Client> clientMapper;

	@Autowired
	public ClientRepositoryImpl(MappingManager manager) {
		 this.clientAccessor = manager.createAccessor(ClientAccessor.class);
		 this.clientMapper = manager.mapper(Client.class);
	}
	
	@Override
	public ListenableFuture<Result<Client>> retrieveAllClients()  {
		return clientAccessor.getAll();
	}

	@Override
	public ListenableFuture<Client> retrieveClientByName(String name) {	
		return clientAccessor.getClientByName(name);	
	}

	@Override
	public void createClient(Client client) {
		clientMapper.saveAsync(client);
	}

}
