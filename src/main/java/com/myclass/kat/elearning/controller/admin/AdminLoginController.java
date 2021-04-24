package com.myclass.kat.elearning.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myclass.kat.elearning.dto.LoginDto;
import com.myclass.kat.elearning.service.AuthService;

@RestController
@RequestMapping("api/admin/login")
public class AdminLoginController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping("")
	public Object post(@RequestBody LoginDto dto) {
		try {
			
			String token = authService.login(dto);		
			System.out.println(token);
			return new ResponseEntity<String>(token,HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<String>("Sai tên đăng nhập hoặc mật khẩu",HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("test")
	public String test() {
		return "how";
	}
}
