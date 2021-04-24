package com.myclass.kat.elearning.transfer;

import org.springframework.stereotype.Component;

import com.myclass.kat.elearning.dto.VideoDto;
import com.myclass.kat.elearning.entity.Video;

@Component
public class VideoTransfer implements BaseTransfer<Video, VideoDto>{

	@Override
	public VideoDto entityToDto(Video entity) {
		return new VideoDto()
				.setId(entity.getId())
				.setTitle(entity.getTitle())
				.setTime_count(entity.getTime_count())
				.setUrl(entity.getUrl());
	}

	@Override
	public Video dtoToEntity(VideoDto dto) {
		// TODO Auto-generated method stub
		return new Video()
				.setTitle(dto.getTitle())
				.setTime_count(dto.getTime_count())
				.setUrl(dto.getUrl());
	}

	
}
