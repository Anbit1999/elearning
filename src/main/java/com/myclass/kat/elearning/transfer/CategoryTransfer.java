package com.myclass.kat.elearning.transfer;

import org.springframework.stereotype.Component;

import com.myclass.kat.elearning.dto.CategoryDto;
import com.myclass.kat.elearning.entity.Category;

@Component
public class CategoryTransfer implements BaseTransfer<Category, CategoryDto>{

	@Override
	public CategoryDto entityToDto(Category entity) {
		// TODO Auto-generated method stub
		return new CategoryDto()
				.setTitle(entity.getTitle())
				.setIcon(entity.getIcon());
	}

	@Override
	public Category dtoToEntity(CategoryDto dto) {
		// TODO Auto-generated method stub
		return new Category()
				.setTitle(dto.getTitle())
				.setIcon(dto.getIcon());
	}

}
