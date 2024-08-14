package com.example.demo.service;

import java.util.HashMap;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.domain.dto.BoardDTO;
import com.example.demo.domain.dto.Criteria;

public interface BoardService {
	long regist(BoardDTO board, MultipartFile[] files) throws Exception;
	
	HashMap<String, Object> getDetail(long boardnum, String loginUser);
	HashMap<String, Object> getList(Criteria cri);
	
	long modify(BoardDTO board, MultipartFile[] files, String[] deleteFiles) throws Exception;
	
	long remove(long boardnum);
}
