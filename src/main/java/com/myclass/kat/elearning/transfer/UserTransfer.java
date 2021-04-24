package com.myclass.kat.elearning.transfer;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

import com.myclass.kat.elearning.dto.UserDto;
import com.myclass.kat.elearning.entity.User;

@Component
public class UserTransfer implements BaseTransfer<User, UserDto>{

	@Override
	public UserDto entityToDto(User entity) {
		return new UserDto()
				.setId(entity.getId())
				.setEmail(entity.getEmail())
				.setFullname(entity.getFullname())
				.setPassword(entity.getPassword())
				.setPhone(entity.getPhone())
				.setAvatar(entity.getAvatar())
				.setAddress(entity.getAddress())
				;
	}

	@Override
	public User dtoToEntity(UserDto dto) {
		String hashed= BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt());
		return new User()
				.setId(dto.getId())
				.setEmail(dto.getEmail())
				.setFullname(dto.getFullname())
				.setPassword(hashed)
				.setPhone(dto.getPhone())
				.setAvatar(dto.getAvatar())
				.setAddress(dto.getAddress())
				;
	}

}
