package com.kh.app.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.action.Transfer;
import com.mysql.cj.Session;

public class BoardFrontController extends HttpServlet {
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
		String command = requestURI.substring((contextPath+"/board").length());
		
		System.out.println(command);

		Transfer transfer = null;
		switch(command) {
		case "/list":
			try {
				transfer = new BoardListOkAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println("/list : "+e);
			}
			break;
//		case "boardlistok.bo":
		case "/write":
			transfer = new Transfer();
			transfer.setRedirect(false);
			transfer.setPath("/app/board/write.jsp");
			break;
		case "/writeok":
			try {
				transfer = new BoardWriteOkAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println("/writeok : "+e);
			}
			break;
		case "/view":
			try {
				transfer = new BoardViewOkAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println("/view : "+e);
			}
			break;
		case "/delete":
			try {
				transfer = new BoardDeleteOkAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println("/delete : "+e);
			}
			break;
		case "/update":
			try {
				transfer = new BoardUpdateAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println("/update : "+e);
			}
			break;
		case "/updateok":
			try {
				transfer = new BoardUpdateOkAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println("/updateok : "+e);
			}
			break;
		case "/file":
			try {
				new FileDownloadAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println("/file : "+e);
			}
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













