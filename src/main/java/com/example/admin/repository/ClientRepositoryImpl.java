package com.example.admin.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.datastax.driver.mapping.Result;

@Repository
@Lazy
public class ClientRepositoryImpl implements ClientRepositoryInterface {
	
	private final ClientAccessor clientAccessor;
	private final Mapper<ClientRepository> clientMapper;

	@Autowired
	public ClientRepositoryImpl(MappingManager manager) {
		 this.clientAccessor = manager.createAccessor(ClientAccessor.class);
		 this.clientMapper = manager.mapper(ClientRepository.class);
	}
	
	@Override
	public Result<ClientRepository> retrieveAllClients()  {
		return clientAccessor.getAll();
	}

	@Override
	public Result<ClientRepository> retrieveClientByName(String name) {	
		return clientAccessor.getClientByName(name);	
	}

	@Override
	public void createClient(ClientRepository client) {
		clientMapper.saveAsync(client);
	}

}
