package com.example.demo.service;

import com.example.demo.domain.dto.UserDTO;

public interface UserService {
	boolean join(UserDTO user);
	boolean modify(UserDTO user);
	boolean login(String userid, String userpw);
	boolean checkId(String userid);
	UserDTO getDetail(String loginUser);
	boolean leaveId(String userid);
}
