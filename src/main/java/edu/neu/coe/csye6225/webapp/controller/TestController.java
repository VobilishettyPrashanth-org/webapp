package edu.neu.coe.csye6225.webapp.controller;
import com.timgroup.statsd.StatsDClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.neu.coe.csye6225.webapp.model.UserDto;



@RestController
@RequestMapping("/healthz")



public class TestController {
	@Autowired
	private StatsDClient statsDClient;
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	@GetMapping()
	public ResponseEntity<?> getHealth() {
		logger.info("Check of Healthz ");
		statsDClient.incrementCounter("endpoint.Healthz.http.get");
		return new ResponseEntity<UserDto>( HttpStatus.OK);
	}
}
