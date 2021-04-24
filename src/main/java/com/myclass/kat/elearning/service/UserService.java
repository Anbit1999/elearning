package com.myclass.kat.elearning.service;

import com.myclass.kat.elearning.dto.PasswordDto;
import com.myclass.kat.elearning.dto.UserDto;

public interface UserService extends BaseService<UserDto, Integer>{

	UserDto getProfile();
	String changePassword(PasswordDto dto);
	boolean buyCourse(Integer id);
	boolean register(UserDto dto);
	
}
