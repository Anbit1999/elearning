package com.myclass.kat.elearning.transfer;

import org.springframework.stereotype.Component;

import com.myclass.kat.elearning.dto.TargetDto;
import com.myclass.kat.elearning.entity.Target;


public class TargetTransfer implements BaseTransfer<Target, TargetDto>{

	@Override
	public TargetDto entityToDto(Target entity) {
		// TODO Auto-generated method stub
		return new TargetDto()
				.setId(entity.getId())
				.setTitle(entity.getTitle());
	}

	@Override
	public Target dtoToEntity(TargetDto dto) {
		// TODO Auto-generated method stub
		return new Target()
				.setId(dto.getId())
				.setTitle(dto.getTitle());
	}

}
