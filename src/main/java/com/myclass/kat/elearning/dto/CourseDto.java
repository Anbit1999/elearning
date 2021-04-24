package com.myclass.kat.elearning.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.myclass.kat.elearning.entity.Category;
import com.myclass.kat.elearning.entity.Target;
import com.myclass.kat.elearning.entity.Video;

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
public class CourseDto {

	private Integer id;
	private String title;
	private String image;
	private int lectures_count;
	private int hour_count;
	private int view_count;
	private BigDecimal price;
	private int discount;
	private BigDecimal promotion_price;
	private String description;
	private String content;
	
	private Category category;
	
	private List<Video> videos;
	
	private List<Target> targets;
	
	private LocalDate last_update;
}
