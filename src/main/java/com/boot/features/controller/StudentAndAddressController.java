package com.boot.features.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.boot.features.entitiesP.Student;
import com.boot.features.entitiesS.Address;
import com.boot.features.service.MyService;

@RestController
public class StudentAndAddressController {

	@Autowired
	private MyService service;

	@PostMapping(value = "/student",consumes = MediaType.APPLICATION_JSON_VALUE)
	public Student saveStudent(@RequestBody Student s) {

		return service.saveStudent(s);
	}
	
	@PostMapping(value = "/address")
	public Address saveAddress(@RequestBody Address ad) {

		return service.saveAddress(ad);
	}
}
