package com.example.admin.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClientResponse {
	
	@JsonProperty("cliente")
	private final Client client;
	
	private ClientResponse(Builder builder) {
	        this.client = Objects.requireNonNull(builder.client);
	}
	
    public Client getClient() {
        return client;
    }
    
    public static Builder builder() {
        return new Builder();
    }
    
    public static class Builder {

        private Client client;
        
        private Builder() {}
        
        public Builder withClient(Client client) {
            this.client = client;
            return this;
        }

        public ClientResponse build() {
            return new ClientResponse(this);
        }
    }
        
}
