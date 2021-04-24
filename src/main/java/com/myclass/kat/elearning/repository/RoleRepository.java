package com.myclass.kat.elearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myclass.kat.elearning.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

	Role findByName(String name);
}
