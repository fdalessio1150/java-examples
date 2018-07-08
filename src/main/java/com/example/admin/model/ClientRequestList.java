package com.example.admin.model;

import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonCreator;

public class ClientRequestList {

    @Valid
    private final List<ClientRequest> data;

    public List<ClientRequest> getAll() {
		return data;
	}

	private ClientRequestList(Builder builder) {
        this.data = Objects.requireNonNull(builder.clients);
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
