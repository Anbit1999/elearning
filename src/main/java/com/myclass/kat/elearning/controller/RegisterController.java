package com.myclass.kat.elearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myclass.kat.elearning.dto.UserDto;
import com.myclass.kat.elearning.service.UserService;

@RestController
@RequestMapping("api/register")
public class RegisterController {

	@Autowired
	private UserService userService;
	
	@PostMapping("")
	private Object register(UserDto userDto) {
		try {
			userService.register(userDto);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
