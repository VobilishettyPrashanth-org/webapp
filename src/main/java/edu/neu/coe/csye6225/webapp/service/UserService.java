package edu.neu.coe.csye6225.webapp.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.neu.coe.csye6225.webapp.exeception.DataNotFoundExeception;
import edu.neu.coe.csye6225.webapp.exeception.UserExistException;
import edu.neu.coe.csye6225.webapp.model.User;
import edu.neu.coe.csye6225.webapp.model.UserDto;
import edu.neu.coe.csye6225.webapp.model.UserUpdateRequestModel;
import edu.neu.coe.csye6225.webapp.repository.UserRepository;
@Service
public class UserService {

	@Autowired
	UserRepository userrepo;
	
	@Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
	
	public String createUser(User user) throws UserExistException {
		User userDto = userrepo.findByUsername(user.getUsername());
		if (userDto == null) {
			user.setPassword(encoder().encode(user.getPassword()));
			userrepo.save(user);
			return "Created User";
		}
		throw new UserExistException("User Exists Already");
	}

	public UserDto getUserDetails(UUID userId) throws DataNotFoundExeception {
		Optional<User> user = userrepo.findById(userId);
		if (user.isPresent()) {
			UserDto dto=UserDto.getUserDto(user.get());
			return dto;
		}
		throw new DataNotFoundExeception("User Not Found");
	}

	public String updateUserDetails(UUID userId, UserUpdateRequestModel user) throws DataNotFoundExeception {
		Optional<User> userObj = userrepo.findById(userId);
		if (userObj.isPresent()) {
			User dto=userObj.get();
			dto.setFirstName(user.getFirstName());
			dto.setLastName(user.getLastName());
			dto.setPassword(encoder().encode(user.getPassword()));
			userrepo.save(dto);
			return "Updated User Details Successfully";
			
		}
		throw new DataNotFoundExeception("User Not Found");
	}
	
	
}
