package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.dto.UserDTO;

@Controller
@RequestMapping("/ajax/*")
public class AjaxController {
	@GetMapping(value = {"ajaxTest","rank","userlist"})
	public void replace() {}
	
	@GetMapping("test")
	//@ResponseBody : 리턴하는 데이터가 응답 데이터 자체라는 뜻
	@ResponseBody
	public String test() {
		return "Test Data";
	}
	
	@GetMapping("list")
	@ResponseBody
	public ArrayList<UserDTO> list() {
		ArrayList<UserDTO> list = new ArrayList<>();
		UserDTO apple = new UserDTO();
		apple.setUserid("apple");
		apple.setUserpw("1234");
		apple.setUsername("김사과");
		list.add(apple);
		UserDTO banana = new UserDTO();
		banana.setUserid("banana");
		banana.setUserpw("1234");
		banana.setUsername("반하나");
		list.add(banana);
		UserDTO cherry = new UserDTO();
		cherry.setUserid("cherry");
		cherry.setUserpw("1234");
		cherry.setUsername("이체리");
		list.add(cherry);
		
		return list;
	}
	
	
	
}





