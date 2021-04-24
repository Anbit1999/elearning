package com.myclass.kat.elearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myclass.kat.elearning.dto.LoginDto;
import com.myclass.kat.elearning.dto.UserDto;
import com.myclass.kat.elearning.service.AuthService;
import com.myclass.kat.elearning.service.UserService;

@RestController
@RequestMapping("api/auth")
public class AuthController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthService authService;
	
	@PostMapping("login")
	public Object post(@RequestBody LoginDto loginDto) {
		try {
			String token = authService.login(loginDto);
			return new ResponseEntity<>(token, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("register")
	public Object post(@RequestBody UserDto userDto) {
		try {
			boolean result = userService.register(userDto);
			if (!result) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>(userDto,HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
