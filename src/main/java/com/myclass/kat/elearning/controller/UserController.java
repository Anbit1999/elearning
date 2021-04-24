package com.myclass.kat.elearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myclass.kat.elearning.dto.PasswordDto;
import com.myclass.kat.elearning.dto.UserDto;
import com.myclass.kat.elearning.service.CourseService;
import com.myclass.kat.elearning.service.UserService;
import com.myclass.kat.elearning.service.VideoService;

@RestController
@RequestMapping("api/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private VideoService videoService;
	
	@Autowired
	private CourseService courseService;
	
	
	@GetMapping("profile")
	public Object get() {
		try {
			UserDto userDto = userService.getProfile();
			return new ResponseEntity<>(userDto,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("password")
	public Object put(@RequestBody PasswordDto dto) {
		try {
			String message = userService.changePassword(dto);
			if (message != null) {
				return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("buyCourse")
	public Object buyCourse(@RequestParam("id") Integer id) {
		try {
			boolean result = userService.buyCourse(id);
			if (!result) {
				return new ResponseEntity<>("Mua khóa học thất bại", HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>("Mua khóa học thành công", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("course/{id}")
	public Object updateViewVideo(@PathVariable("id") Integer id) {
		try {
			boolean result = courseService.updateViewCount(id);
			if (!result) {
				return new ResponseEntity<>("Xảy ra lỗi", HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>("Khóa học có thêm 1 lượt xem", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
