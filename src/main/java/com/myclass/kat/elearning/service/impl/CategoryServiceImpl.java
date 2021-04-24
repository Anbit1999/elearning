package com.myclass.kat.elearning.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myclass.kat.elearning.dto.CategoryDto;
import com.myclass.kat.elearning.entity.Category;
import com.myclass.kat.elearning.repository.CategoryRepository;
import com.myclass.kat.elearning.service.CategoryService;
import com.myclass.kat.elearning.transfer.CategoryTransfer;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private CategoryTransfer categoryTransfer;
	
	@Override
	public List<CategoryDto> findAll() {
		List<Category> categories = categoryRepository.findAll();
		List<CategoryDto> categoryDtos = new ArrayList<>();
		if (categories == null) {
			return null;
		}
		for (Category entity : categories) {
			CategoryDto dto = categoryTransfer.entityToDto(entity);
			categoryDtos.add(dto);
		}
		return categoryDtos;
	}

	@Override
	public CategoryDto finById(Integer id) {
		Category entity = categoryRepository.findById(id).get();
		if (entity == null) {
			return null;
		}	
		return categoryTransfer.entityToDto(entity);
	}

	@Override
	public boolean insert(CategoryDto dto) {
		if (dto == null) {
			return false;
		}
		try {
			Category entity = categoryTransfer.dtoToEntity(dto);
			categoryRepository.save(entity);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Integer id, CategoryDto dto) {
		Category entity = categoryRepository.findById(id).get();
		if (entity == null) {
			return false;
		}
		try {
			entity.setIcon(dto.getIcon());
			entity.setTitle(dto.getTitle());
			categoryRepository.save(entity);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Integer id) {
		try {
			categoryRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	

}
