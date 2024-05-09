package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.dto.UserDTO;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.mapper.UserMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper umapper;
	
	@Autowired
	private ProductMapper pmapper;

	@Override
	public boolean join(UserDTO user) {
		return umapper.insert(user) == 1;
	}

	@Override
	public boolean modifyUser(UserDTO user) {
		return umapper.update(user) == 1;
	}
	
	@Override
	public boolean leaveId(String userid) {
		pmapper.deleteAll(userid);
		return umapper.delete(userid) == 1;
	}
	
	@Override
	public boolean checkId(String userid) {
		return umapper.selectById(userid) == null;
	}
	
	@Override
	public UserDTO getDetail(String userid) {
		return umapper.selectById(userid);
	}

	@Override
	public UserDTO login(String userid, String userpw) {
		UserDTO user = umapper.selectById(userid);
		if(user != null) {
			if(user.getUserpw().equals(userpw)) {
				return user;
			}
		}
		return null;
	}
}
