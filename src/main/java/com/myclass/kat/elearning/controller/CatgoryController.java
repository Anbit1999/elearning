package com.myclass.kat.elearning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myclass.kat.elearning.dto.CategoryDto;
import com.myclass.kat.elearning.service.CategoryService;

@RestController
@RequestMapping("api/category")
public class CatgoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("")
	public Object get() {
		try {
			List<CategoryDto>  dtos = categoryService.findAll();
			if (dtos == null || dtos.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>(dtos,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
