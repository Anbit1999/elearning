package com.myclass.kat.elearning.transfer;

import org.springframework.stereotype.Component;

import com.myclass.kat.elearning.dto.RoleDto;
import com.myclass.kat.elearning.entity.Role;

@Component
public class RoleTransfer implements BaseTransfer<Role, RoleDto>{
	
	public RoleDto entityToDto(Role entity) {
		return new RoleDto()
				.setId(entity.getId())
				.setName(entity.getName())
				.setDescription(entity.getDescription())
				;	
	}
	
	public Role dtoToEntity(RoleDto dto) {		
		return new Role()
				.setName(dto.getName())
				.setDescription(dto.getDescription());	
	}
	
}
