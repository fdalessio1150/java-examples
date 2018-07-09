package com.example.admin.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Client {
	
	@JsonProperty("id_cliente")
	private final UUID id;

	@JsonProperty("nome")
	private final String name;
	
	@JsonProperty("sexo")
	private final String sex;
	 
	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public String getSex() {
		return sex;
	}
	
	public static Builder builder() {
		 return new Builder();
	 }
	    
	 private Client(Builder builder) {
		 this.id = builder.id;
		 this.name = builder.name;
		 this.sex = builder.sex;
	 }
	 
	 public static class Builder {
		 
		 private UUID id;
		 private String name;
		 private String sex;
		 
		 private Builder() {}
		 
		 public Builder withId(UUID id) {
			 this.id = id;
			 return this;
		 }
		 
		 public Builder withName(String name) {
			 this.name = name;
			 return this;
		 }
		 
		 public Builder withSex(String sex) {
			 this.sex = sex;
			 return this;
		 }
		 
	     public Client build() {
	    	 return new Client(this);
	     }
	 }
}
