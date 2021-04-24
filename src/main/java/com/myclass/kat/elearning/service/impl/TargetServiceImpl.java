package com.myclass.kat.elearning.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myclass.kat.elearning.dto.TargetDto;
import com.myclass.kat.elearning.entity.Target;
import com.myclass.kat.elearning.repository.TargetRepository;
import com.myclass.kat.elearning.service.TargetService;
import com.myclass.kat.elearning.transfer.TargetTransfer;

@Service
public class TargetServiceImpl implements TargetService{

	private TargetRepository targetRepository;
	

	private TargetTransfer targetTransfer;
	
	public TargetServiceImpl(TargetRepository targetRepository) {
		super();
		this.targetRepository = targetRepository;
	}

	@Override
	public List<TargetDto> findAll() {
		List<TargetDto> targetDtos = new ArrayList<>();
		List<Target> targets = targetRepository.findAll();
		if (targets == null || targets.isEmpty()) {
			return null;
		}
		for (Target target : targets) {
			TargetDto dto = targetTransfer.entityToDto(target);
			targetDtos.add(dto);
		}
		return targetDtos;
	}

	@Override
	public TargetDto finById(Integer id) {
		Target entity = targetRepository.findById(id).get();
		if (entity == null) {
			return null;
		}		
		return targetTransfer.entityToDto(entity);
	}

	@Override
	public boolean insert(TargetDto dto) {
		if (dto == null) {
			return false;
		}
		Target entity = targetTransfer.dtoToEntity(dto);
		targetRepository.save(entity);
		return true;
	}

	@Override
	public boolean update(Integer id, TargetDto dto) {
		Target entity = targetRepository.findById(id).get();
		if (entity == null) {
			return false;
		}
		entity.setTitle(dto.getTitle());
		return true;
	}

	@Override
	public boolean delete(Integer id) {
		targetRepository.deleteById(id);
		return true;
	}

}
