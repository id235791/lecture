package com.example.demo.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.domain.dto.BoardDTO;
import com.example.demo.domain.dto.Criteria;
import com.example.demo.service.BoardService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/board/*")
public class BoardController {
	@Autowired
	private BoardService service;
	
	@GetMapping("list/{pagenum}")
	public ResponseEntity<HashMap<String, Object>> list(Criteria cri, @PathVariable("pagenum")int pagenum){
		cri.setPagenum(pagenum);
		HashMap<String, Object> result = service.getList(cri);
		if(result != null) {
			return new ResponseEntity<HashMap<String,Object>>(result,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<HashMap<String,Object>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("{boardnum}")
	public ResponseEntity<HashMap<String, Object>> view(@PathVariable("boardnum")long boardnum, HttpServletRequest req) {
		HttpSession session = req.getSession();
		String loginUser = (String)session.getAttribute("loginUser");
		HashMap<String,Object> datas = service.getDetail(boardnum, loginUser);
		if(datas.get("board") != null) {
			return new ResponseEntity<>(datas,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("regist")
	public ResponseEntity<Long> regist(BoardDTO board, @RequestParam(value = "files", required = false) MultipartFile[] files) throws Exception{
		long boardnum = service.regist(board, files);
		if(boardnum != -1) {
			return new ResponseEntity<Long>(boardnum,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Long>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
//		return new ResponseEntity<Long>(1000l,HttpStatus.OK);
	}
	
	@PutMapping("{boardnum}")
	public ResponseEntity<Long> modify(BoardDTO board, @RequestParam(value = "files", required = false) MultipartFile[] files, @RequestParam(value = "deleteFiles", required = false) String[] deleteFiles) throws Exception{
		long boardnum = service.modify(board, files, deleteFiles);
		if(boardnum != -1) {
			return new ResponseEntity<Long>(boardnum,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Long>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("{boardnum}")
	public ResponseEntity<Long> remove(@PathVariable("boardnum") long boardnum){
		if(service.remove(boardnum)!=-1) {
			return new ResponseEntity<Long>(boardnum,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Long>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}








