package com.myclass.kat.elearning.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;


public class AuthFilter extends BasicAuthenticationFilter{

	private UserDetailsService userDetailsService;
	
	public AuthFilter(AuthenticationManager authenticationManager,UserDetailsService userDetailsService) {
		super(authenticationManager);
		this.userDetailsService = userDetailsService;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		String authorizationHeader = request.getHeader("Authorization");
		
		System.out.println(authorizationHeader);
		
		if (authorizationHeader != null && !authorizationHeader.isEmpty()) {
			
			String token = authorizationHeader.replace("Bearer ", "");
			
			// Lấy token từ request header
			String email = Jwts.parser()
				.setSigningKey("ABCDEF")
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
			//System.out.println(authorizationHeader);
			
			//DÙNG EMAIL TRUY VẤN DB LẤY THÔNG TIN USER
			UserDetails userDetails = userDetailsService.loadUserByUsername(email);
			
			//SET THÔNG TIN USER VÀO SECURITY CONTEXT
			
			Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
					userDetails.getAuthorities());
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
		}
		
		chain.doFilter(request, response);
	}
}
