package com.myclass.kat.elearning.transfer;

import org.springframework.stereotype.Component;

import com.myclass.kat.elearning.dto.CourseDto;
import com.myclass.kat.elearning.entity.Course;

@Component
public class CourseTransfer implements BaseTransfer<Course, CourseDto>{

	@Override
	public CourseDto entityToDto(Course entity) {
		
		return new CourseDto()
				.setId(entity.getId())
				.setTitle(entity.getTitle())
				.setImage(entity.getImage())
				.setLectures_count(entity.getLectures_count())
				.setHour_count(entity.getHour_count())
				.setView_count(entity.getView_count())
				.setPrice(entity.getPrice())
				.setDiscount(entity.getDiscount())
				.setPromotion_price(entity.getPromotion_price())
				.setDescription(entity.getDescription())
				.setContent(entity.getContent());
	}

	@Override
	public Course dtoToEntity(CourseDto dto) {
		// TODO Auto-generated method stub
		return new Course()
				.setId(dto.getId())
				.setTitle(dto.getTitle())
				.setImage(dto.getImage())
				.setLectures_count(dto.getLectures_count())
				.setHour_count(dto.getHour_count())
				.setView_count(dto.getView_count())
				.setPrice(dto.getPrice())
				.setDiscount(dto.getDiscount())
				.setPromotion_price(dto.getPromotion_price())
				.setDescription(dto.getDescription())
				.setContent(dto.getContent());
	}

}
