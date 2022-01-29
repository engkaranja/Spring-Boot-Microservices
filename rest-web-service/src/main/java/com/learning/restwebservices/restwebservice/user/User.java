package com.learning.restwebservices.restwebservice.user;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

@Entity
public class User {

	@Null
	@Id
	@GeneratedValue
	private Integer Id;
	
	@Size(min = 2, message = "name is too short!")
	@NotBlank
	private String name;
	
	@Past
	@NotNull
	private Date dob;	
	
	public User() {
		super();
	}
	
	public User(Integer id, String name, Date dob) {
		super();
		Id = id;
		this.name = name;
		this.dob = dob;
	}
	
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}

	@Override
	public String toString() {
		return "User [Id=" + Id + ", name=" + name + ", dob=" + dob + "]";
	}
	
	
	
}
