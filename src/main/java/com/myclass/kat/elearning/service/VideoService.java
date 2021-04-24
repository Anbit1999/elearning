package com.myclass.kat.elearning.service;

import java.util.List;

import com.myclass.kat.elearning.dto.VideoDto;

public interface VideoService extends BaseService<VideoDto, Integer>{

	List<VideoDto> findVideoByCourseId(Integer courseId);
	
}
