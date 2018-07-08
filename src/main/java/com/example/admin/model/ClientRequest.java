package com.example.admin.model;

import java.util.UUID;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ClientRequest {
	
	 private final UUID id;
	 
	 @NotNull(message = "Campo obrigatório")
	 @NotEmpty(message = "Campo obrigatório")
	 @JsonProperty("nome")
	 private final String name;

	 @NotNull(message = "Campo obrigatório")
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
	    
	 private ClientRequest(Builder builder) {
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
		 
	     public ClientRequest build() {
	    	 return new ClientRequest(this);
	     }
	 }
	 
	 @JsonCreator
	 static ClientRequest json(@JsonProperty(value = "nome") String name,
							   @JsonProperty(value = "sexo") String sex) {
		 return builder().withId(UUID.randomUUID())
				 .withName(name)
				 .withSex(sex)
				 .build();
	 }
}
