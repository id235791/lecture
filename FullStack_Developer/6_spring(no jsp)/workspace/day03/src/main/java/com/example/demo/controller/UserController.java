package com.example.demo.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.dto.UserDTO;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/user/*")
public class UserController {
	// /views/user/joinview.jsp
	//여러 요청 주소를 하나의 메소드에 매핑하는 방법
	@GetMapping(value = {"joinview","loginview","main"})
	public void replace() {}

	
	@PostMapping("joinOk")
	public String joinOk(UserDTO dto, HttpServletResponse resp) throws Exception{
		System.out.println(dto);
		//DB처리
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/gb";
		String user = "root";
		String password = "1234";
		
		Connection conn = DriverManager.getConnection(url, user, password);
		
		String sql = "insert into test_user values(?,?,?)";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, dto.getUserid());
		ps.setString(2, dto.getUserpw());
		ps.setString(3, dto.getUsername());
		
		int result = ps.executeUpdate();
		
		if(result == 1) {
			Cookie cookie = new Cookie("joinid", dto.getUserid());
			cookie.setMaxAge(60*5);
			resp.addCookie(cookie);
			return "/user/loginview";
		}
		else {
			return "/user/joinview";
		}
	}
	@PostMapping("loginOk")
	public String loginOk(String userid, String userpw, HttpServletRequest req) throws Exception {
		//DB처리
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/gb";
		String user = "root";
		String password = "1234";
		
		Connection conn = DriverManager.getConnection(url, user, password);
		
		String sql = "select * from test_user where userid=? and userpw=?";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, userid);
		ps.setString(2, userpw);
		
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			req.getSession().setAttribute("loginUser", userid);
			return "/user/main";
		}
		else {
			return "/user/loginview";
		}
		
	}
	@GetMapping("logout")
	public String logout(HttpServletRequest req) {
//		특정 키에 해당하는 데이터만 지우기
//		req.getSession().removeAttribute("loginUser");

//		초기화
		req.getSession().invalidate();
		return "/user/loginview";
	}
}














