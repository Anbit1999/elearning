package com.myclass.kat.elearning.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myclass.kat.elearning.dto.CategoryDto;
import com.myclass.kat.elearning.entity.Category;
import com.myclass.kat.elearning.service.CategoryService;

@RestController
@RequestMapping("api/admin/category")
public class AdminCategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("")
	public ResponseEntity<List<CategoryDto>> get() {
		try {
			List<CategoryDto> dtos = categoryService.findAll();
			if (dtos == null) {
				return new ResponseEntity<List<CategoryDto>>(HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("")
	public ResponseEntity<CategoryDto> post(@RequestBody CategoryDto categoryDto) {
		
		try {
			if (categoryDto == null) {
				return new ResponseEntity<CategoryDto>(HttpStatus.BAD_REQUEST);
			}
			categoryService.insert(categoryDto);
			return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<CategoryDto>(HttpStatus.BAD_REQUEST);
		
	}
}
