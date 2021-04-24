package com.myclass.kat.elearning.dto;

import com.myclass.kat.elearning.entity.Course;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TargetDto {

	private Integer id;
	private String title;
	
	private Course course;
}
