package com.myclass.kat.elearning.dto;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.myclass.kat.elearning.entity.Course;
import com.myclass.kat.elearning.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserCourseDto {

	private UserDto userDto;
	
	private CourseDto courseDto;
	
}
