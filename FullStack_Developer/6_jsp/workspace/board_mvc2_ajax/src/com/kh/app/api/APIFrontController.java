package com.kh.app.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.app.api.service.BoardService;
import com.kh.app.api.service.ReplyService;
import com.kh.app.api.service.UserService;

public class APIFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//select
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String requestURI = req.getRequestURI();// ???/userjoin.us
		String contextPath = req.getContextPath(); // ???
		String command = requestURI.substring((contextPath+"/api").length());
		
		System.out.println(requestURI);
		
		switch(command) {
		case "/user/checkid":
			try {
				new UserService().checkId(req,resp);
			} catch (Exception e) {
				System.out.println("/user/checkid : "+e);
			}
			break;
		case "/user/login":
			try {
				new UserService().login(req,resp);
			} catch(Exception e) {
				System.out.println("/user/login : "+e);
			}
			break;
		case "/user/logout":
			req.getSession().invalidate();
			resp.sendRedirect(req.getContextPath()+"/");
			break;
		case "/board/list":
			try {
				new BoardService().getList(req,resp);
			} catch (Exception e) {
				System.out.println("/board/list : "+e);
			}
			break;
		case "/board/get":
			try {
				new BoardService().getDetail(req,resp);
			} catch (Exception e){
				System.out.println("/board/get : "+e);
			}
			break;
		case "/board/file":
			try {
				new BoardService().downloadFile(req,resp);
			} catch (Exception e) {
				System.out.println("/board/file : "+e);
			}
		}
	}
	
	//insert
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String requestURI = req.getRequestURI();// ???/userjoin.us
		String contextPath = req.getContextPath(); // ???
		String command = requestURI.substring((contextPath+"/api").length());
		
		System.out.println(requestURI);
		
		switch(command) {
		case "/user/join":
			try {
				new UserService().join(req,resp);
			} catch (Exception e) {
				System.out.println("/user/join : "+e);
			}
			break;
		case "/board/write":
			try {
				new BoardService().write(req,resp);
			} catch(Exception e) {
				System.out.println("/board/write : "+e);
			}
			break;
		case "/reply/write":
			try {
				new ReplyService().write(req,resp);
			} catch (Exception e) {
				System.out.println("/reply/write : "+e);
			}
			break;
		}
	}
	
	//update
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String requestURI = req.getRequestURI();// ???/userjoin.us
		String contextPath = req.getContextPath(); // ???
		String command = requestURI.substring((contextPath+"/api").length());
		
		switch(command) {
		case "/reply/update":
			try {
				new ReplyService().update(req,resp);
			} catch (Exception e) {
				System.out.println("/reply/update : "+e);
			}
			break;
		case "/board/update":
			try {
				new BoardService().update(req,resp);
			} catch(Exception e) {
				System.out.println("/board/update : "+e);
			}
		}
	}
	
	//delete
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String requestURI = req.getRequestURI();// ???/userjoin.us
		String contextPath = req.getContextPath(); // ???
		String command = requestURI.substring((contextPath+"/api").length());
		
		switch(command) {
		case "/reply/delete":
			try {
				new ReplyService().delete(req,resp);
			} catch (Exception e) {
				System.out.println("/reply/delete : "+e);
			}
			break;
		case "/board/delete":
			try {
				new BoardService().delete(req,resp);
			} catch (Exception e) {
				System.out.println("/board/delete : "+e);
			}
			break;
		}
	}
	
}