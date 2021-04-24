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
public class VideoDto {

	private Integer id;
	private String title;
	private String url;
	private int time_count;
	
	private Course course;
}
