package com.example.admin.repository;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;

@Accessor
public interface ClientAccessor {
	
    @Query("SELECT * FROM TB001_CLIENT")
    Result<ClientRepository> getAll();
    
    @Query("SELECT * FROM TB001_CLIENT WHERE CLI_NOME = :n")
    Result<ClientRepository> getClientByName(@Param("n") String name);
}
