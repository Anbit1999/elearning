package com.myclass.kat.elearning.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myclass.kat.elearning.dto.VideoDto;
import com.myclass.kat.elearning.entity.Video;
import com.myclass.kat.elearning.repository.VideoRepository;
import com.myclass.kat.elearning.service.VideoService;
import com.myclass.kat.elearning.transfer.VideoTransfer;

@Service
public class VideoServiceImpl implements VideoService{

	private VideoRepository videoRepository;
	
	@Autowired
	private VideoTransfer videoTransfer;
	
	public VideoServiceImpl(VideoRepository videoRepository) {
		super();
		this.videoRepository = videoRepository;
	}

	@Override
	public List<VideoDto> findAll() {
		List<Video> videos = videoRepository.findAll();
		List<VideoDto> videoDtos = new ArrayList<>();
		if (videos.isEmpty() || videos == null) {
			return null;
		}
		for (Video entity : videos) {
			VideoDto dto = videoTransfer.entityToDto(entity);
			videoDtos.add(dto);
		}
		return videoDtos;
	}

	@Override
	public VideoDto finById(Integer id) {
		Video entity = videoRepository.findById(id).get();
		if (entity == null) {
			return null;
		}
		return videoTransfer.entityToDto(entity);
	}

	@Override
	public boolean insert(VideoDto dto) {
		if (dto == null) {
			return false;
		}
		Video video = videoTransfer.dtoToEntity(dto);
		videoRepository.save(video);
		return true;
	}

	@Override
	public boolean update(Integer id, VideoDto dto) {
		Video entity = videoRepository.findById(id).get();
		if (entity == null) {
			return false;
		}
		entity.setTitle(dto.getTitle());
		entity.setTime_count(dto.getTime_count());
		entity.setUrl(dto.getUrl());
		videoRepository.save(entity);
		return true;
	}

	@Override
	public boolean delete(Integer id) {
		videoRepository.deleteById(id);
		return true;
	}

	@Override
	public List<VideoDto> findVideoByCourseId(Integer courseId) {
		List<VideoDto> videoDtos = new ArrayList<>();
		List<Video> videos = videoRepository.findByCourseId(courseId);
		for (Video video : videos) {
			VideoDto dto = videoTransfer.entityToDto(video);
			videoDtos.add(dto);
		}
		return videoDtos;
	}


}
