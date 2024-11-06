package com.example.demo.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.domain.BoardDTO;
import com.example.demo.domain.Criteria;
import com.example.demo.domain.FileDTO;

public interface BoardService {
	HashMap<String, Object> getList(Criteria cri);
	long regist(BoardDTO board, MultipartFile[] files) throws Exception;
	HashMap<String, Object> getDetail(long boardnum, String loginUser);
	long modify(BoardDTO board, MultipartFile[] files, String[] deleteFiles) throws Exception;
	long remove(long boardnum);
}
