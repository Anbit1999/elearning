package com.myclass.kat.elearning.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
@Entity
@Table(name = "courses")
public class Course implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String title;
	private String image;
	private int lectures_count;
	private int hour_count;
	private int view_count;
	private BigDecimal price;
	private int discount;
	private BigDecimal promotion_price;
	
	@Column(name = "description", columnDefinition = "NVARCHAR(1000)")
	private String description;
	private String content;
	
	@ManyToOne
	@JoinColumn(name = "category_id", insertable = false, updatable = false)
	private Category category;
	
	@OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	private List<Video> videos;
	
	@OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
	private List<Target> targets;
	
	
	private LocalDate last_update;
}
