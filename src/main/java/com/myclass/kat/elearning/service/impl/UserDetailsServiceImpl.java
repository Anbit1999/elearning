package com.myclass.kat.elearning.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.myclass.kat.elearning.dto.UserDetailsDto;
import com.myclass.kat.elearning.entity.User;
import com.myclass.kat.elearning.repository.UserRepository;

@Service
@Transactional(rollbackOn = Exception.class)
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// Gọi hàm trả về email
		User entity = userRepository.findByEmail(email);
		if (entity == null) 
			throw new UsernameNotFoundException("Email không tồn tại");
		// Trả về đối tượng có kiểu dữ liệu là UserDetails
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		String roleName = entity.getRole().getName();
		authorities.add(new SimpleGrantedAuthority(roleName));
		UserDetails userDetails = new UserDetailsDto(entity.getEmail(), entity.getPassword(),
				authorities);
		return userDetails;
	}

}
