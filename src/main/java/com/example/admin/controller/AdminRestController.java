package com.example.admin.controller;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.admin.model.ClientRequestList;
import com.example.admin.model.ClientResponseList;
import com.example.admin.service.ClientService;

@RestController
public class AdminRestController {

	private ClientService service;

	@Autowired
	public AdminRestController(ClientService service) {
		this.service = service;
	}

	@RequestMapping(value = "/clients", method = RequestMethod.GET)
	public List<ClientResponseList> getClients(@RequestParam(value = "name", required = false) String name) {
		service.retrieveClients(name);
		return null;
	}

	@RequestMapping(value = "/client/{name}", method = RequestMethod.POST,
					consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
		            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<ClientResponseList> createClients(@NotNull @RequestBody @Validated ClientRequestList body,
											      @PathVariable String name) {
		service.createClient(body);
		return null;
	}
}
