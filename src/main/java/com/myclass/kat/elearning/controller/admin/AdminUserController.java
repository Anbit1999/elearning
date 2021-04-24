package com.myclass.kat.elearning.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myclass.kat.elearning.dto.UserDto;
import com.myclass.kat.elearning.service.RoleService;
import com.myclass.kat.elearning.service.UserService;

@RestController
@RequestMapping("api/admin/users")
public class AdminUserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping("")
	public ResponseEntity<List<UserDto>> index() {
		List<UserDto> userDtos = userService.findAll();
		if (userDtos.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(userDtos,HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<UserDto> add(@RequestBody UserDto dto,
			BindingResult error) {
		if (error.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		userService.insert(dto);
		return new ResponseEntity<>(dto,HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserDto> put(@PathVariable("id") Integer id , @RequestBody UserDto dto,
			BindingResult error) {
		if (error.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		userService.update(id, dto);
		return new ResponseEntity<>(dto,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<UserDto> delete(@PathVariable("id") Integer id) {
		userService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
