package com.example.demo.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.domain.dto.UserDTO;

@Mapper
public interface UserMapper {
	int insert(UserDTO user);
	
	int update(UserDTO user);
	
	int delete(String userid);
	
	UserDTO selectById(String userid);
}
