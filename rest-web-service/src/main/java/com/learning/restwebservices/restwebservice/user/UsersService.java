package com.learning.restwebservices.restwebservice.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UsersService {
	
	private static List<User> users = new ArrayList<>();
	private static int userCount = 3;
	
	static {
		users.add(new User(1, "Simon", new Date()));
		users.add(new User(2, "John", new Date()));
		users.add(new User(3, "Mary", new Date()));
	}
	
	//find all method
	public List<User> findAll(){
		return users;
	}
	
	//save user	
	public User save(User user) {
		if(user.getId() == null) {
			user.setId(++userCount);
		}		
		users.add(user);		
		return user;
	}
	
	//find one
	public User findOne(int id) {
		for(User user: users) {
			if(user.getId() == id) {
				return user;				
			}
		}
		return null;
	}
	
	//delete user
		public User deleteUserById(int id) {
			Iterator<User> iterator = users.iterator();
			while(iterator.hasNext()) {
				User user = iterator.next();
				if(user.getId() == id) {
					iterator.remove();
					return user;				
				}
			}			
			return null;
		}

}
