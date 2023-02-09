package edu.neu.coe.csye6225.webapp.model;


import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data
public class User {

	@Id
	@SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
	@Column(nullable = false)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

	@JsonProperty("username")
    @Email(message = "Username/Email is not in format, please check")
    private String username;
	
	@JsonProperty("first_name")
    @Column(name="first_name")
    @NotEmpty(message="First Name cannot be null/empty")
    private String firstName;

	@JsonProperty("last_name")
    @Column(name="last_name")
    @NotEmpty(message="Last Name cannot be null/empty")
    private String lastName;


    @JsonProperty("password")
    @NotEmpty(message="Password cannot be null/empty")
    private String password;

    @JsonProperty(value ="account_created",access = JsonProperty.Access.READ_ONLY)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="account_created")
    private LocalDateTime accountCreated;

    @JsonProperty(value = "account_updated",access = JsonProperty.Access.READ_ONLY)
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="account_updated")
    private LocalDateTime accountUpdated;
    
    

}
