package com.kh.app.reply;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.action.Transfer;
import com.kh.app.board.BoardDeleteOkAction;
import com.kh.app.board.BoardListOkAction;
import com.kh.app.board.BoardUpdateAction;
import com.kh.app.board.BoardUpdateOkAction;
import com.kh.app.board.BoardViewOkAction;
import com.kh.app.board.BoardWriteOkAction;
import com.kh.app.board.FileDownloadAction;

public class ReplyFrontController extends HttpServlet {
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
		String command = requestURI.substring((contextPath+"/reply").length());
		
		System.out.println(command);

		Transfer transfer = null;
		switch(command) {
		case "/write":
			try {
				new ReplyWriteOkAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println("/write : "+e);
			}
			break;
		case "/delete":
			try {
				new ReplyDeleteOkAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println("/delete : "+e);
			}
			break;
		case "/update":
			try {
				new ReplyUpdateOkAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println("/update : "+e);
			}
		}
	}
}