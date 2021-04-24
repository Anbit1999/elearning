package com.myclass.kat.elearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myclass.kat.elearning.entity.UserCourse;

@Repository
public interface UserCourseRepository extends JpaRepository<UserCourse, Integer>{

}
