package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.domain.BoardDTO;
import com.example.demo.domain.Criteria;
import com.example.demo.service.BoardService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/board/*")
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	@GetMapping("list/{pagenum}")
	public ResponseEntity<HashMap<String, Object>> list(Criteria cri, @PathVariable("pagenum")int pagenum) {
		cri.setPagenum(pagenum);
		HashMap<String, Object> result = service.getList(cri);
		if(result != null) {
			return new ResponseEntity<>(result,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PostMapping("write")
	public ResponseEntity<Long> write(BoardDTO board, MultipartFile[] files, HttpServletRequest req) throws Exception{
		String userid = (String)req.getSession().getAttribute("loginUser");
		board.setUserid(userid);
		long boardnum = service.regist(board,files);
		System.out.println(boardnum);
		if(boardnum != -1) {
			return new ResponseEntity<>(boardnum,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("{boardnum}")
	public ResponseEntity<HashMap<String, Object>> get(@PathVariable("boardnum") long boardnum, HttpServletRequest req) {
		HttpSession session = req.getSession();
		String loginUser = (String)session.getAttribute("loginUser");
		
		HashMap<String,Object> result = service.getDetail(boardnum, loginUser);
		
		if(result.get("board") != null) {
			return new ResponseEntity<HashMap<String,Object>>(result,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<HashMap<String,Object>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("{boardnum}")
	public ResponseEntity<Long> modify(BoardDTO board, MultipartFile[] files, String[] deleteFiles) throws Exception{
		long boardnum = service.modify(board,files,deleteFiles);
		if(boardnum != -1) {
			return new ResponseEntity<>(boardnum,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("{boardnum}")
	public ResponseEntity<Long> remove(@PathVariable("boardnum") long boardnum) {
		if(service.remove(boardnum) != -1) {
			return new ResponseEntity<>(boardnum, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}














