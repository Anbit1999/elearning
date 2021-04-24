package com.myclass.kat.elearning.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myclass.kat.elearning.dto.RoleDto;
import com.myclass.kat.elearning.service.RoleService;

@RestController
@RequestMapping("api/admin/roles")
public class AdminRoleController {

	@Autowired
	private RoleService roleService;
	
	@GetMapping("")
	public ResponseEntity<List<RoleDto>> index() {
		List<RoleDto> roleDtos = roleService.findAll();
		if (roleDtos.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(roleDtos,HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<RoleDto> add(@RequestBody RoleDto dto,
			BindingResult error) {
		System.out.println(dto.getName());
		if (error.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		roleService.insert(dto);
		return new ResponseEntity<>(dto,HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<RoleDto> put(@PathVariable("id") Integer id , @Validated @ModelAttribute("role") RoleDto dto,
			BindingResult error) {
		if (error.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		roleService.update(id, dto);
		return new ResponseEntity<>(dto,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<RoleDto> delete(@PathVariable("id") Integer id) {
		roleService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
