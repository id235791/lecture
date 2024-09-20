package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.dto.UserDTO;
import com.example.demo.mapper.TimeMapper;
import com.example.demo.mapper.UserMapper;


@Controller
public class SampleController {
	
	@Autowired
	TimeMapper mapper;
	
	@Autowired
	UserMapper mapper2;
	
	@GetMapping("time1")
	public void time1() {
		//1. mapper 객체는 com.example.demo.mapper.TimeMapper 타입
		//2. mapper.getTime1() 호출 시 mapper XML 파일들을 스캔
		//3. 그 중에서 namespace 속성값이 com.example.demo.mapper.TimeMapper 인 XML을 찾음
		//4. 그 안에 선언되어 있는 쿼리문들 중 getTime1 이라는 id를 가진 쿼리문을 찾아서 수행
		
		System.out.println("Test : "+mapper.getTime1());
	}
	
	@GetMapping("time2")
	public void time2() {
		System.out.println("Test : "+mapper.getTime2());
	}
	
	@GetMapping("user1")
	public void user1() {
		System.out.println("Test : "+mapper2.getUsernameByUserid("apple"));
	}
	
	@GetMapping("user2")
	public void user2() {
		UserDTO user = mapper2.getUserByUserid("apple");
		System.out.println("Test : "+user);
	}
	
}











