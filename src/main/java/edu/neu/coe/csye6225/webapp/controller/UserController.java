package edu.neu.coe.csye6225.webapp.controller;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.neu.coe.csye6225.webapp.constants.UserConstants;
import edu.neu.coe.csye6225.webapp.exeception.DataNotFoundExeception;
import edu.neu.coe.csye6225.webapp.exeception.InvalidInputException;
import edu.neu.coe.csye6225.webapp.exeception.UserAuthrizationExeception;
import edu.neu.coe.csye6225.webapp.exeception.UserExistException;
import edu.neu.coe.csye6225.webapp.model.User;
import edu.neu.coe.csye6225.webapp.model.UserDto;
import edu.neu.coe.csye6225.webapp.model.UserUpdateRequestModel;
import edu.neu.coe.csye6225.webapp.service.AuthService;
import edu.neu.coe.csye6225.webapp.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController()
@RequestMapping("v1/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	AuthService authService;
	
	
    @GetMapping(value = "/{userId}")
    public ResponseEntity<?> getUserDetails(@PathVariable("userId") Long userId,HttpServletRequest request){
    	try {
    		if(userId.toString().isBlank()||userId.toString().isEmpty()) {
            	throw new InvalidInputException("Enter Valid User Id");
            }
    		authService.isAuthorised(userId,request.getHeader("Authorization").split(" ")[1]);
			return new ResponseEntity<UserDto>( userService.getUserDetails(userId),HttpStatus.OK);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<String>( e.getMessage(),HttpStatus.BAD_REQUEST);
		}
    	catch (UserAuthrizationExeception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<String>( e.getMessage(),HttpStatus.FORBIDDEN);
		}
    	catch (DataNotFoundExeception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<String>( e.getMessage(),HttpStatus.NOT_FOUND);
		}
    	catch(Exception e) {
    		return new ResponseEntity<String>(UserConstants.InternalErr,HttpStatus.INTERNAL_SERVER_ERROR);
    	}
        
    }
    
    @PutMapping(value = "/{userId}")
    public ResponseEntity<?> updateUserDetails(@PathVariable("userId") Long userId,@Valid @RequestBody UserUpdateRequestModel user,
    		HttpServletRequest request,Errors error){
    	try {
    		if(userId.toString().isBlank()||userId.toString().isEmpty()) {
            	throw new InvalidInputException("Enter Valid User Id");
            }
    		authService.isAuthorised(userId,request.getHeader("Authorization").split(" ")[1]);
    		if(error.hasErrors()) {
    			String response = error.getAllErrors().stream().map(ObjectError::getDefaultMessage)
    					.collect(Collectors.joining(","));
    			throw new InvalidInputException(response);
    		}
			return new ResponseEntity<String>( userService.updateUserDetails(userId,user),HttpStatus.NO_CONTENT);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<String>( e.getMessage(),HttpStatus.BAD_REQUEST);
		}
    	catch (UserAuthrizationExeception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<String>( e.getMessage(),HttpStatus.FORBIDDEN);
		}
    	catch (DataNotFoundExeception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<String>( e.getMessage(),HttpStatus.NOT_FOUND);
		}
    	catch(Exception e) {
    		return new ResponseEntity<String>(UserConstants.InternalErr,HttpStatus.INTERNAL_SERVER_ERROR);
    	}
        
    }
    
    @PostMapping()
    public ResponseEntity<?> createUser(@Valid @RequestBody User user,Errors error){
    	try {
    		if(error.hasErrors()) {
    			String response = error.getAllErrors().stream().map(ObjectError::getDefaultMessage)
    					.collect(Collectors.joining(","));
    			throw new InvalidInputException(response);
    		}
			return new ResponseEntity<User>( userService.createUser(user),HttpStatus.CREATED);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<String>( e.getMessage(),HttpStatus.BAD_REQUEST);
		}
    	catch (UserExistException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<String>( e.getMessage(),HttpStatus.BAD_REQUEST);
		}
    	catch(Exception e) {
    		return new ResponseEntity<String>(UserConstants.InternalErr,HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }
}
