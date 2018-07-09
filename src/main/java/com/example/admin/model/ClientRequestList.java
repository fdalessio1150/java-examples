package com.example.admin.model;

import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public class ClientRequestList {

	@Valid
    private List<ClientRequest> data;

	private ClientRequestList(Builder builder) {
        this.data = Objects.requireNonNull(builder.clients);
    }
	
	@JsonValue
    public List<ClientRequest> getData() {
		return data;
	}
	
    public void setData(List<ClientRequest> data) {
        this.data = data;
    }
    
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private List<ClientRequest> clients;

        private Builder() {}

        public Builder withClients(List<ClientRequest> clients) {
            this.clients = clients;
            return this;
        }

        public ClientRequestList build() {
            return new ClientRequestList(this);
        }
    }

    @JsonCreator
    static ClientRequestList json(List<ClientRequest> clients) {
        return builder().withClients(clients)
        		.build();
    }
    
}
