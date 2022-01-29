package com.learning.restwebservices.restwebservice.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {
	
	/*Versioning
	1. uri versioning
	2. request parameter versioning
	3. header versioning
	4. accept header versioning / content negotiation / media type versioning  */
	
	
	/*1. uri versioning */
	
	
	@GetMapping("v1/person")
	public PersonV1 personV1() {
		return new PersonV1("Simon Karanja");
	}
	
	@GetMapping("v2/person")
	public PersonV2 personV2() {
		return new PersonV2(new Name("Simon", "Karanja"));
	}
	
	/*2. Request parameter versioning */
	
	@GetMapping(value =  "/person/param", params = "version=1")
	public PersonV1 paramV1() {
		return new PersonV1("Simon Karanja");
	}
	
	@GetMapping(value =  "/person/param", params = "version=2")
	public PersonV2 paramV2() {
		return new PersonV2(new Name("Simon", "Karanja"));
	}
	
	/*3. header versioning */
	
	@GetMapping(value =  "/person/header", headers = "X-API-VERSION=1")
	public PersonV1 headerV1() {
		return new PersonV1("Simon Karanja");
	}
	
	@GetMapping(value =  "/person/header", headers = "X-API-VERSION=2")
	public PersonV2 headerV2() {
		return new PersonV2(new Name("Simon", "Karanja"));
	}
	
	/*4. accept header versioning / content negotiation / media type versioning */
	
	@GetMapping(value =  "/person/produces", produces = "application/vnd.company.app-v1+json")
	public PersonV1 producesV1() {
		return new PersonV1("Simon Karanja");
	}
	
	@GetMapping(value =  "/person/produces", produces = "application/vnd.company.app-v2+json")
	public PersonV2 producesV2() {
		return new PersonV2(new Name("Simon", "Karanja"));
	}

}
