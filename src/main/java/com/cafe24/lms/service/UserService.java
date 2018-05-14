package com.cafe24.lms.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.lms.domain.Role;
import com.cafe24.lms.domain.User;
import com.cafe24.lms.repository.UserRepository;
import com.cafe24.lms.util.PasswordEncoding;
import com.cafe24.lms.util.SHAPasswordEncoder;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;

	private SHAPasswordEncoder shaPasswordEncoder;
	private PasswordEncoding passwordEncoding;

	public User getNo(Long no) {

		return userRepository.findOne(no);
	}

	public User getUserEmailandPassword(User user) {
		
		passwordEncoding(user);
		return userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
	}

	public boolean existEmail(String email) {
		User user = userRepository.findByEmail(email);

		return user != null;
	}

	public void join(User user) {

		passwordEncoding(user);
		user.setRegDate(new Date());
		user.setRole(Role.USER);

		userRepository.save(user);
	}

	public void userModify(User authUser, User vo) {

		authUser.setGender(vo.getGender());
		authUser.setName(vo.getName());
		authUser.setPassword(vo.getPassword());

		passwordEncoding(authUser);

		userRepository.update(authUser);
	}

	public void passwordEncoding(User user) {
		shaPasswordEncoder = new SHAPasswordEncoder(512);
		shaPasswordEncoder.setEncodeHashAsBase64(true);
		passwordEncoding = new PasswordEncoding(shaPasswordEncoder);
		user.setPassword(passwordEncoding.encode(user.getPassword()));
	}

}
