package com.learning.restwebservices.restwebservice.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;



@RestController
public class UserController {

	@Autowired
	private UsersService service;
	
	//retrieve all users
	@GetMapping("/users")
	public List<User> retrieveAllUsers(){
		List<User> users = service.findAll();
		if(users.isEmpty()) {
			throw new UserNotFoundException("0 records found"); 
		}else {
			return users;
		}
	}
	
	//get one user
	@GetMapping("/users/{user_id}")
	public EntityModel<User> getUser(@PathVariable int user_id) {
		User user = service.findOne(user_id);
		if(user == null) {
			throw new UserNotFoundException("id-"+user_id);
		}
		EntityModel<User> model = EntityModel.of(user);
		
		WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		model.add(linkToUsers.withRel("all-users"));		
		
		return model;
	}
	
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = service.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
		.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	//delete user
		@DeleteMapping("/users/{user_id}")
		public void deleteUser(@PathVariable int user_id) {
			User user = service.deleteUserById(user_id);
			if(user == null) {
				throw new UserNotFoundException("id-"+user_id);
			}		
		}
}
