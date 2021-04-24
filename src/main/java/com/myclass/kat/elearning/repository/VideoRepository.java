package com.myclass.kat.elearning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myclass.kat.elearning.entity.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, Integer>{

	@Query("Select v from Video v where course_id = :courseId")
	List<Video> findByCourseId(@Param("courseId") int courseId);
}
