package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.domain.dto.TestDTO;

@Controller
@RequestMapping("/api/*")
public class HomeController {
	@GetMapping("test1")
	public void test1(int data) {
		
	}
	@GetMapping("test2")
	@ResponseBody
	public ResponseEntity<String> test2(TestDTO dto){
		return new ResponseEntity<>(dto.toString(),HttpStatus.OK);
	}
	@GetMapping("test3/{data}")
	public void test3(@PathVariable("data") int data) {
		
	}
}
