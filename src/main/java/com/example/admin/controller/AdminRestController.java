package com.example.admin.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.admin.model.ClientRequestList;
import com.example.admin.model.ClientResponse;
import com.example.admin.service.AdminService;

@RestController
public class AdminRestController {

	private AdminService service;

	@Autowired
	public AdminRestController(AdminService service) {
		this.service = service;
	}

	@RequestMapping(value = "/clients", method = RequestMethod.GET)
	public List<ClientResponse> getClients(@RequestParam(value = "name", required = false) String name) {
		return service.retrieveClients(name);
	}

	@RequestMapping(value = "/client/{name}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> createClients(@RequestBody @Valid ClientRequestList body,
											      @PathVariable String name) {
		service.createClient(body);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
