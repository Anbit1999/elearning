package com.myclass.kat.elearning.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myclass.kat.elearning.dto.CourseDetailsDto;
import com.myclass.kat.elearning.dto.CourseDto;
import com.myclass.kat.elearning.dto.VideoDto;
import com.myclass.kat.elearning.service.CourseService;
import com.myclass.kat.elearning.service.VideoService;

@RestController
@RequestMapping("api/admin/courses")
public class AdminCourseController {

	@Autowired
	private CourseService courseService;
	
	@Autowired
	private VideoService videoService;
	
	@GetMapping("")
	public Object get() {
		try {
			List<CourseDto> dtos = courseService.findAll();
			if (dtos == null | dtos.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>(dtos, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("{id}")
	public Object get(@PathVariable("id") int id) {
		try {
			CourseDto dto = courseService.finById(id);
			List<VideoDto> videoDtos = videoService.findVideoByCourseId(id);
			CourseDetailsDto detailsDto = new CourseDetailsDto()
					.setCourseDto(dto)
					.setVideoDtos(videoDtos);
					
			if (dto == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<Object>(dto, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
