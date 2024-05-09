package com.example.demo.controller;

import java.io.PrintWriter;

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

import com.example.demo.domain.dto.UserDTO;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/user/*")
public class UserController {
	@Autowired
	UserService service;
	
	@GetMapping(value="login")
	public String login(HttpServletRequest req) {
		ObjectNode json = JsonNodeFactory.instance.objectNode();
		HttpSession session = req.getSession();
		String loginUser = (String)session.getAttribute("loginUser");
		json.put("loginUser",loginUser);
		String joinid = "";
		Cookie[] cookies = req.getCookies();
		if(cookies != null && cookies.length > 0) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals("joinid")) {
					joinid = cookie.getValue();
					break;
				}
			}
		}
		json.put("joinid", joinid);
		System.out.println(json.toString());
		return json.toString();
	}
	@GetMapping("myinfo")
	public String myinfo(HttpServletRequest req) {
		String userid = (String)req.getSession().getAttribute("loginUser");
		UserDTO user = service.getDetail(userid);
		ObjectNode json = JsonNodeFactory.instance.objectNode();
		json.putPOJO("user", user);
		return json.toString();
	}
	
	@PostMapping(value="login", produces = "text/plain;")
	public String login(String userid, String userpw, HttpServletRequest req) {
		UserDTO loginUser = service.login(userid, userpw);
		if(loginUser != null) {
			req.getSession().setAttribute("loginUser", loginUser.getUserid());
			return loginUser.getUserid();
		}
		else {
			return "X";
		}
	}
	
	@GetMapping(value="checkId", produces = "text/plain;")
	public ResponseEntity<String> checkId(String userid,HttpServletRequest req) {
		return service.checkId(userid)?new ResponseEntity<>(HttpStatus.OK):new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	@PostMapping(value="join",consumes = "application/json;charset=utf-8")
	public ResponseEntity<String> join(@RequestBody UserDTO user,HttpServletResponse resp){
		if(service.join(user)) {
			Cookie cookie = new Cookie("joinid", user.getUserid());
			cookie.setMaxAge(300);
			resp.addCookie(cookie);
			return new ResponseEntity<>("O",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("X",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("logout")
	public void logout(HttpServletRequest req,HttpServletResponse resp) throws Exception{
		req.getSession().invalidate();
		PrintWriter out = resp.getWriter();
		out.print("<script>");
		out.print("location.replace('/')");
		out.print("</script>");
	}
	
	@PutMapping("{userid}")
	public ResponseEntity<String> modify(@RequestBody UserDTO user, @PathVariable String userid) {
		if(service.modifyUser(user)) {
			return new ResponseEntity<>("O",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("X",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("{userid}")
	public ResponseEntity<String> leave(@PathVariable("userid")String userid){
		if(service.leaveId(userid)) {
			return new ResponseEntity<String>("O",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("X",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}









