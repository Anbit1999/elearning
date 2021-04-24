package com.myclass.kat.elearning.service.impl;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.myclass.kat.elearning.dto.LoginDto;
import com.myclass.kat.elearning.service.AuthService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
@Transactional(rollbackOn = Exception.class)
public class AuthServiceImpl implements AuthService{

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Override
	public String login(LoginDto dto) {
		Authentication authentication = new UsernamePasswordAuthenticationToken
				(dto.getEmail(), dto.getPassword());
		
		authenticationManager.authenticate(authentication);
		
		// Tạo Token
		
		Date nowDate = new Date();
		
		String token = Jwts.builder()
		.setSubject(dto.getEmail())
		.setIssuedAt(nowDate)
		.setExpiration(new Date(nowDate.getTime() + 864000000L))
		.signWith(SignatureAlgorithm.HS512, "ABCDEF")
		.compact();
		
		return token;
	}

}
