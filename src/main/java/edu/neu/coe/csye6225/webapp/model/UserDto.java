package edu.neu.coe.csye6225.webapp.model;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;

@Data
public class UserDto {

	private UUID id;
    private String firstName;
    private String lastName;
    private String username;
    private LocalDateTime accountCreated;
    private LocalDateTime accountUpdated;
    
    
    public UUID getId() {
		return id;
	}


	public void setId(UUID id) {
		this.id = id;
	}


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


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public LocalDateTime getAccountCreated() {
		return accountCreated;
	}


	public void setAccountCreated(LocalDateTime accountCreated) {
		this.accountCreated = accountCreated;
	}


	public LocalDateTime getAccountUpdated() {
		return accountUpdated;
	}


	public void setAccountUpdated(LocalDateTime accountUpdated) {
		this.accountUpdated = accountUpdated;
	}


	public static UserDto getUserDto(User user) {
    	UserDto dto=new UserDto();
    	dto.setId(user.getId());
    	dto.setAccountCreated(user.getAccountCreated());
    	dto.setAccountUpdated(user.getAccountUpdated());
    	dto.setFirstName(user.getFirstName());
    	dto.setLastName(user.getLastName());
    	dto.setUsername(user.getUsername());
    	return dto;
    }
}
