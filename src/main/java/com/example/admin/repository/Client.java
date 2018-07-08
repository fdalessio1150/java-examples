package com.example.admin.repository;

import java.util.UUID;

import com.datastax.driver.mapping.annotations.ClusteringColumn;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

@Table(name = "TB001_CLIENT")
public class Client {

	@PartitionKey(0)
    @Column(name = "CLI_NOME")
    private String name;
	
	@ClusteringColumn(0)
    @Column(name = "CLI_SEXO")
    private String sex;
    
    @Column(name = "CLI_ID")
    private UUID id;
    
    public Client() {
    	
    }
    
    public Client(String name,
    			  String sex,
    			  UUID id) {
    	this.name = name;
    	this.sex = sex;
    	this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
    
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    
}
