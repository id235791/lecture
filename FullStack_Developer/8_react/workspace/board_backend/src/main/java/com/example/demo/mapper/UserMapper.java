package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.dto.UserDTO;

@Mapper
public interface UserMapper {
	//C
	int insertUser(UserDTO user);
	//R
	UserDTO getUserById(String userid);
	//U
	int updateUser(UserDTO user);
	//D
	int deleteUser(String userid);
}
