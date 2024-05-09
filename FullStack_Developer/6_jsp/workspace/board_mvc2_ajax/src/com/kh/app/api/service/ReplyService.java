package com.kh.app.api.service;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.model.dao.ReplyDAO;
import com.kh.model.dto.ReplyDTO;
import com.kh.model.dto.UserDTO;

public class ReplyService {

	public void delete(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		long replynum = Long.parseLong(req.getParameter("replynum"));
		
		ReplyDAO rdao = new ReplyDAO();
		
		if(rdao.deleteReply(replynum)) {
			resp.setStatus(HttpServletResponse.SC_OK);
			resp.getWriter().print("ok");
		}
		else {
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

	public void update(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Gson gson = new Gson();
		StringBuffer jb = new StringBuffer();
		String line = null;
		BufferedReader reader = req.getReader();
		while ((line = reader.readLine()) != null) {
			jb.append(line);
		}
		
		ReplyDTO reply = gson.fromJson(jb.toString(), ReplyDTO.class);
		ReplyDAO rdao = new ReplyDAO();
		
		if(rdao.updateReply(reply)) {
			resp.setStatus(HttpServletResponse.SC_OK);
			resp.getWriter().print("ok");
		}
		else {
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

	public void write(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		Gson gson = new Gson();
		StringBuffer jb = new StringBuffer();
		String line = null;
		BufferedReader reader = req.getReader();
		while ((line = reader.readLine()) != null) {
			jb.append(line);
		}
		
		ReplyDTO reply = gson.fromJson(jb.toString(), ReplyDTO.class);
		ReplyDAO rdao = new ReplyDAO();
		
		if(rdao.insertReply(reply)) {
			resp.setStatus(HttpServletResponse.SC_OK);
			resp.getWriter().print("ok");
		}
		else {
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

}





