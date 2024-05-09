package com.kh.app.api.service;

import java.io.BufferedReader;
import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.kh.model.dao.UserDAO;
import com.kh.model.dto.UserDTO;

public class UserService {

	public void checkId(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String userid = req.getParameter("userid");
		
		UserDAO udao = new UserDAO();
		UserDTO temp = udao.getUserById(userid);
		
		PrintWriter out = resp.getWriter();
		if(temp == null) {
			out.write("O");
		}
		else {
			out.write("X");
		}
	}

	public void join(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Gson gson = new Gson();
		StringBuffer jb = new StringBuffer();
		String line = null;
		BufferedReader reader = req.getReader();
		while ((line = reader.readLine()) != null) {
			jb.append(line);
		}
		
		UserDTO user = gson.fromJson(jb.toString(), UserDTO.class);
		UserDAO udao = new UserDAO();
		if(udao.insertUser(user)) {
			Cookie cookie = new Cookie("joinid", user.getUserid());
			cookie.setPath("/");
			cookie.setMaxAge(5);
			resp.addCookie(cookie);
			resp.setStatus(HttpServletResponse.SC_OK);
			resp.getWriter().print("ok");
		}
		else {
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

	public void login(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		String userid = req.getParameter("userid");
		String userpw = req.getParameter("userpw");
		UserDAO udao = new UserDAO();
		UserDTO temp = udao.getUserById(userid);
		
		if(temp != null && temp.getUserpw().equals(userpw)) {
			req.getSession().setAttribute("loginUser", userid);
			resp.setStatus(HttpServletResponse.SC_OK);
			resp.getWriter().print("ok");
		}
		else {
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

}





