package com.example.demo.service;

import com.example.demo.domain.dto.UserDTO;

import jakarta.servlet.http.HttpServletRequest;

public interface UserService {
	boolean join(UserDTO user);
	
	boolean modifyUser(UserDTO user);
	
	boolean leaveId(String userid);
	
	boolean checkId(String userid);
	UserDTO getDetail(String userid); 
	UserDTO login(String userid, String userpw);
}
