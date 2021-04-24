	package com.myclass.kat.elearning.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.myclass.kat.elearning.dto.RoleDto;
import com.myclass.kat.elearning.entity.Role;
import com.myclass.kat.elearning.repository.RoleRepository;
import com.myclass.kat.elearning.service.RoleService;
import com.myclass.kat.elearning.transfer.RoleTransfer;

@Service
public class RoleServiceImpl implements RoleService{

	
	private RoleRepository roleRepository;
	
	private RoleTransfer roleTransfer;
	
	
	
	public RoleServiceImpl(RoleRepository roleRepository, RoleTransfer roleTransfer) {
		super();
		this.roleRepository = roleRepository;
		this.roleTransfer = roleTransfer;
	}

	@Override
	public List<RoleDto> findAll() {
		List<Role> roles = roleRepository.findAll();
		List<RoleDto> roleDtos = new ArrayList<>();
		if (roles == null) {
			return null;
		}
		for (Role entity : roles) {
			RoleDto dto = roleTransfer.entityToDto(entity);
			roleDtos.add(dto);
		}
		return roleDtos;
	}

	@Override
	public RoleDto finById(Integer id) {
		Role entity = roleRepository.findById(id).get();
		if (entity == null) {
			return null;
		}	
		return roleTransfer.entityToDto(entity);
	}

	@Override
	public boolean insert(RoleDto dto) {
		if (dto == null) {
			return false;
		}
		try {
			Role role = roleTransfer.dtoToEntity(dto);
			roleRepository.save(role);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Integer id, RoleDto dto) {
		Role entity = roleRepository.findById(id).get();
		if (entity == null) {
			return false;
		}
		try {
			entity.setName(dto.getDescription());
			entity.setDescription(dto.getDescription());
			roleRepository.save(entity);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Integer id) {
		try {
			roleRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
