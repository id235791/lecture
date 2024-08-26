package com.example.demo.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.dto.Criteria;
import com.example.demo.domain.dto.ReplyDTO;
import com.example.demo.domain.dto.ReplyPageDTO;
import com.example.demo.service.ReplyService;

@RestController
@RequestMapping("/api/reply/*")
public class ReplyController {
	
	@Autowired
	private ReplyService service;
	
	@PostMapping("regist")
	public ResponseEntity<ReplyDTO> regist(@RequestBody ReplyDTO reply){
		
		ReplyDTO result = service.regist(reply);
		if(result == null) {
			return new ResponseEntity<ReplyDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		else {
			return new ResponseEntity<ReplyDTO>(result,HttpStatus.OK);
		}
	}
	
	@GetMapping("list/{boardnum}/{pagenum}")
	public ResponseEntity<ReplyPageDTO> getList(
			@PathVariable("boardnum")long boardnum,
			@PathVariable("pagenum")int pagenum
	){
		Criteria cri = new Criteria(pagenum, 5);
		return new ResponseEntity<ReplyPageDTO>(service.getList(cri,boardnum), HttpStatus.OK);
	}
	
	@DeleteMapping("{replynum}")
	public ResponseEntity<String> remove(@PathVariable("replynum") long replynum){
		return service.remove(replynum) ? 
				new ResponseEntity<String>(HttpStatus.OK) :
				new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PutMapping("{replynum}")
	public ResponseEntity<String> modify(@RequestBody ReplyDTO reply){
		return service.modify(reply) ?
				new ResponseEntity<String>(HttpStatus.OK) :
				new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
