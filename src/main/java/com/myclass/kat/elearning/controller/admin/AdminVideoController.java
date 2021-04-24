package com.myclass.kat.elearning.controller.admin;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myclass.kat.elearning.dto.VideoDto;
import com.myclass.kat.elearning.service.VideoService;

@RestController
@RequestMapping("api/admin/videos")
public class AdminVideoController {
	
	private VideoService videoService;

	public AdminVideoController(VideoService videoService) {
		super();
		this.videoService = videoService;
	}
	
	@GetMapping("")
	public ResponseEntity<List<VideoDto>> index() {
		List<VideoDto> videoDtos = videoService.findAll();
		if (videoDtos.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(videoDtos,HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<VideoDto> add(@RequestBody VideoDto dto,
			BindingResult error) {
		if (error.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		videoService.insert(dto);
		return new ResponseEntity<>(dto,HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<VideoDto> put(@PathVariable("id") Integer id , @RequestBody VideoDto dto,
			BindingResult error) {
		if (error.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		videoService.update(id, dto);
		return new ResponseEntity<>(dto,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<VideoDto> delete(@PathVariable("id") Integer id) {
		videoService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
