package com.myclass.kat.elearning.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.myclass.kat.elearning.dto.PasswordDto;
import com.myclass.kat.elearning.dto.RoleDto;
import com.myclass.kat.elearning.dto.UserDto;
import com.myclass.kat.elearning.entity.Course;
import com.myclass.kat.elearning.entity.Role;
import com.myclass.kat.elearning.entity.User;
import com.myclass.kat.elearning.entity.UserCourse;
import com.myclass.kat.elearning.repository.CourseRepository;
import com.myclass.kat.elearning.repository.RoleRepository;
import com.myclass.kat.elearning.repository.UserCourseRepository;
import com.myclass.kat.elearning.repository.UserRepository;
import com.myclass.kat.elearning.service.UserService;
import com.myclass.kat.elearning.transfer.UserTransfer;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserTransfer userTransfer;
	
	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private UserCourseRepository userCourseRepository;
	
	@Override
	public List<UserDto> findAll() {
		List<User> users = userRepository.findAll();
		List<UserDto> userDtos = new ArrayList<>();
		if (users.isEmpty() || users == null) {
			return null;
		}
		System.out.println(users);
		for (User entity : users) {
			UserDto dto = userTransfer.entityToDto(entity);
			Role role = entity.getRole();
			RoleDto roleDto = new RoleDto()
					.setId(role.getId())
					.setName(role.getName())
					.setDescription(role.getDescription());
			dto.setRoleDto(roleDto);
			userDtos.add(dto);
		}
		return userDtos;
	}

	@Override
	public UserDto finById(Integer id) {
		User entity = userRepository.findById(id).get();
		if (entity == null) {
			return null;
		}	
		return userTransfer.entityToDto(entity);
	}

	@Override
	public boolean insert(UserDto dto) {
		if (dto == null) {
			return false;
		}
		if (userRepository.findByEmail(dto.getEmail()) == null) {
			Role role = roleRepository.findById(dto.getRoleDto().getId()).get();
			try {
				User user = userTransfer.dtoToEntity(dto);
				user.setRole(role);
				userRepository.save(user);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return false;
	}

	@Override
	public boolean update(Integer id, UserDto dto) {
		User entity = userRepository.findById(id).get();
		
		if (entity == null) {
			return false;
		}
		Role role = roleRepository.findById(dto.getRoleDto().getId()).get();
		try {
				entity.setFullname(dto.getFullname());
				entity.setAddress(dto.getAddress());
				entity.setAvatar(dto.getAvatar());
				entity.setPhone(dto.getPhone());
				entity.setRole(role);
				userRepository.save(entity);		
				return true;
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Integer id) {
		try {
			userRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public UserDto getProfile() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//UserDetails userDetails = (UserDetails) principal;
		String email = ((UserDetails)principal).getUsername();
		
		User user = userRepository.findByEmail(email);
		return new UserDto()
				.setId(user.getId())
				.setEmail(user.getEmail())
				.setFullname(user.getFullname())
				.setPhone(user.getPhone())
				.setAddress(user.getAddress());
	}

	@Override
	public String changePassword(PasswordDto dto) {
		
		if (dto.getNewPassword().equals(dto.getOldPassword())) {
			return "Mật khẩu cũ và mật khẩu mới không được trùng nhau";
		}
		
		User user = userRepository.findByEmail(dto.getEmail());
		// So mật khẩu
		if (!BCrypt.checkpw(dto.getOldPassword(), dto.getNewPassword())) {
			return "Mật khẩu cũ không đúng";
		}
		String hashed = BCrypt.hashpw(dto.getNewPassword(), BCrypt.gensalt());
		
		// Cập nhật lại mật khẩu
		user.setPassword(hashed);
		userRepository.save(user);
		return "Cập nhật mật khẩu thành công";
	}

	@Override
	public boolean buyCourse(Integer id) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//UserDetails userDetails = (UserDetails) principal;
		if (principal == null) {
			return false;
		}
		String email = ((UserDetails)principal).getUsername();
		
		if (email == null) {
			return false;
		}
		
		User user = userRepository.findByEmail(email);
		if (user == null) {
			return false;
		}
		Course course = courseRepository.findById(id).get();
		UserCourse userCourse = new UserCourse().setCourse(course).setUser(user);
		userCourseRepository.save(userCourse);
		return true;
	}

	@Override
	public boolean register(UserDto dto) {
		if (dto == null) {
			return false;
		}
		if (userRepository.findByEmail(dto.getEmail()) == null) {
			Role role = roleRepository.findByName("ROLE_STUDENT");
			try {
				User user = userTransfer.dtoToEntity(dto);
				user.setRole(role);
				userRepository.save(user);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return false;
	}

}
