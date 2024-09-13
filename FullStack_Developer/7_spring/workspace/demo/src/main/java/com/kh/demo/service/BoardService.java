package com.kh.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.kh.demo.domain.dto.BoardDTO;
import com.kh.demo.domain.dto.Criteria;
import com.kh.demo.domain.dto.FileDTO;

public interface BoardService {
	List<BoardDTO> getList(Criteria cri);
	long getTotal(Criteria cri);
	boolean regist(BoardDTO board, MultipartFile[] files) throws Exception;
	BoardDTO getDetail(long boardnum);
	long getLastNum(String userid);
	void increaseReadCount(long boardnum);
	boolean modify(BoardDTO board, MultipartFile[] files, String updateCnt) throws Exception;
	boolean remove(long boardnum);
	List<FileDTO> getFiles(long boardnum);
}






