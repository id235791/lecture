package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.dto.BoardDTO;
import com.example.demo.domain.dto.UserDTO;
import com.example.demo.mapper.BoardMapper;
import com.example.demo.mapper.FileMapper;
import com.example.demo.mapper.ReplyMapper;
import com.example.demo.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper umapper;
	@Autowired
	private BoardMapper bmapper;
	@Autowired
	private ReplyMapper rmapper;
	@Autowired
	private FileMapper fmapper;
	
	@Override
	public boolean join(UserDTO user) {
		return umapper.insertUser(user) == 1;
	}

	@Override
	public boolean login(String userid, String userpw) {
		UserDTO user = umapper.getUserById(userid);
		if(user != null) {
			if(user.getUserpw().equals(userpw)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean checkId(String userid) {
		return umapper.getUserById(userid) == null;
	}
	
	@Override
	public UserDTO getDetail(String loginUser) {
		return umapper.getUserById(loginUser);
	}

	@Override
	public boolean modify(UserDTO user) {
		return umapper.updateUser(user) == 1;
	}

	@Override
	public boolean leaveId(String userid) {
		List<BoardDTO> list = bmapper.getListByUserid(userid);
		for(BoardDTO board : list) {
			rmapper.deleteRepliesByBoardnum(board.getBoardnum());
			fmapper.deleteFilesByBoardnum(board.getBoardnum());
		}
		return umapper.deleteUser(userid) == 1;
	}
	
}
