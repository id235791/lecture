package com.example.demo.controller;

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

import com.example.demo.domain.Criteria;
import com.example.demo.domain.ReplyDTO;
import com.example.demo.domain.ReplyPageDTO;
import com.example.demo.service.ReplyService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/reply/*")
public class ReplyController {
	
	@Autowired
	private ReplyService service;
	
	@PostMapping("regist")
	public ResponseEntity<Long> regist(@RequestBody ReplyDTO reply) {
		ReplyDTO createdReply = service.regist(reply);
		if(createdReply != null) {
			return new ResponseEntity<>(createdReply.getReplynum(), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatusCode.valueOf(500));
		}
	}
	
	@GetMapping("/{boardnum}/{pagenum}")
	public ResponseEntity<ReplyPageDTO> list(
			@PathVariable("boardnum") long boardnum,
			@PathVariable("pagenum") int pagenum
	) {
		Criteria cri = new Criteria(pagenum,5);
		//{replyCnt:10, list:[]}
		//1. HashMap
		//	key				value
		//	replyCnt		10
		//	list			[]
		//2. DTO
		//	replyCnt
		//	list
		return new ResponseEntity<>(service.getList(cri,boardnum),HttpStatus.OK);
	}
	
	//@DeleteMapping : REST 방식의 설계 이용 시 삭제 요청을 받을 때 사용하는 매핑 방식
	@DeleteMapping("{replynum}")
	public ResponseEntity<Long> remove(@PathVariable("replynum") long replynum) {
		return service.remove(replynum) ? 
				new ResponseEntity<>(replynum,HttpStatus.OK) :
				new ResponseEntity<>(-1l,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//PUT
	//	자원의 전체 수정, 모든 데이터들을 다 전달, 전달 된 자원의 필드 중 비어있는 것이 있다면 DB상의 데이터도 null로 변화 
	//PATCH
	//	자원의 일부 수정, 수정 할 필드의 값만 전달
	@PutMapping("{replynum}")
	public ResponseEntity<Long> modify(@RequestBody ReplyDTO reply, HttpServletRequest req) {
//		String loginUser = (String)req.getSession().getAttribute("loginUser");
//		reply.setUserid(loginUser);
		return service.modify(reply) ? 
				new ResponseEntity<>(reply.getReplynum(),HttpStatus.OK) :
				new ResponseEntity<>(-1l,HttpStatus.INTERNAL_SERVER_ERROR);
	}

}














