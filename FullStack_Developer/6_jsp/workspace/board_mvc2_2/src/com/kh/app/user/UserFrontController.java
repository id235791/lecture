package com.kh.app.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.action.Transfer;

public class UserFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		doProcess(req, resp);
	}
	
	private void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//길 나누는 코드
		String requestURI = req.getRequestURI();// ???/userjoin.us
		String contextPath = req.getContextPath(); // ???
		String command = requestURI.substring((contextPath+"/user").length());
		
		System.out.println(command);

		Transfer transfer = null;
		switch(command) {
		case "/join":
			transfer = new Transfer();
			transfer.setRedirect(false);
			transfer.setPath("/app/user/join.jsp");
			break;
		case "/joinok":
			try {
				transfer = new UserJoinOkAction().execute(req, resp);
			} catch (Exception e) {
				System.out.println("/joinok : "+e);
			}
			break;
		case "/login":
			transfer = new Transfer();
			transfer.setPath("/");
			transfer.setRedirect(false);
			break;
		case "/loginok":
			try {
				transfer = new UserLoginOkAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println("/loginok : "+e);
			}
			break;
		case "/checkid":
			try {
				new CheckIdOkAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println("/checkid : "+e);
			}
			break;
		case "/logout":
			req.getSession().invalidate();
			transfer = new Transfer();
			transfer.setRedirect(false);
			transfer.setPath("/");
			break;
		}
		
		if(transfer != null) {
			if(transfer.isRedirect()) {
				resp.sendRedirect(transfer.getPath());
			}
			else {
				req.getRequestDispatcher(transfer.getPath()).forward(req, resp);
			}
		}
	}
}













