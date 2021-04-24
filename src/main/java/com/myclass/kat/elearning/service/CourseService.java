package com.myclass.kat.elearning.service;

import com.myclass.kat.elearning.dto.CourseDto;

public interface CourseService extends BaseService<CourseDto, Integer>{

	boolean updateViewCount(Integer id);
}
