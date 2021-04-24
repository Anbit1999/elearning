package com.myclass.kat.elearning.dto;

import java.util.List;

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
public class CourseDetailsDto {

	private CourseDto courseDto;
	private List<VideoDto> videoDtos;
	private List<TargetDto> targetDtos;
}
