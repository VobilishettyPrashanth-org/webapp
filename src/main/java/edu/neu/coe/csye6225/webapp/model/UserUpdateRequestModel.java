package edu.neu.coe.csye6225.webapp.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;


@JsonIgnoreProperties(allowGetters = true, ignoreUnknown = false)
@Data
public class UserUpdateRequestModel {

	@NotEmpty(message="First Name cannot be null/empty")
	@JsonAlias("first_name")
    private String firstName;

	@JsonAlias("last_name")
    @NotEmpty(message="Last Name cannot be null/empty")
    private String lastName;
  
	@JsonAlias("password")
    @NotEmpty(message="Password cannot be null/empty")
    private String password;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	


    
}