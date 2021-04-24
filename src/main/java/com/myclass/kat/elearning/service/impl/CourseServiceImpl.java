package com.myclass.kat.elearning.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myclass.kat.elearning.dto.CourseDto;
import com.myclass.kat.elearning.entity.Course;
import com.myclass.kat.elearning.entity.Role;
import com.myclass.kat.elearning.repository.CourseRepository;
import com.myclass.kat.elearning.service.CourseService;
import com.myclass.kat.elearning.transfer.CourseTransfer;

@Service
public class CourseServiceImpl implements CourseService{

	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private CourseTransfer courseTransfer;
	
	public CourseServiceImpl(CourseRepository courseRepository) {
		super();
		this.courseRepository = courseRepository;
	}

	@Override
	public List<CourseDto> findAll() {
		List<CourseDto> dtos = new ArrayList<>();
		List<Course> entities = courseRepository.findAll();
		
		for (Course course : entities) {
			CourseDto dto = courseTransfer.entityToDto(course);
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public CourseDto finById(Integer id) {
		Course entity = courseRepository.findById(id).get();
		if (entity == null) {
			return null;
		}
		return courseTransfer.entityToDto(entity);
	}

	@Override
	public boolean insert(CourseDto dto) {
		if (dto == null) {
			return false;
		}
		try {
			Course course = courseTransfer.dtoToEntity(dto);
			courseRepository.save(course);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Integer id, CourseDto dto) {
		Course entity = courseRepository.findById(id).get();
		if (entity == null) {
			return false;
		}
		try {
			entity.setTitle(dto.getTitle());
			entity.setImage(dto.getImage());
			entity.setLectures_count(dto.getLectures_count());
			entity.setHour_count(dto.getHour_count());
			entity.setPrice(dto.getPrice());
			entity.setDiscount(dto.getDiscount());
			entity.setPromotion_price(dto.getPromotion_price());
			entity.setDescription(dto.getDescription());
			entity.setContent(dto.getContent());
			entity.setLast_update(dto.getLast_update());
			courseRepository.save(entity);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateViewCount(Integer id) {
		Course entity = courseRepository.findById(id).get();
		if (entity == null) {
			return false;
		}
		entity.setView_count(entity.getView_count()+1);
		return false;
	}

}
