package com.kh.app.api.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.kh.action.Transfer;
import com.kh.model.dao.BoardDAO;
import com.kh.model.dao.FileDAO;
import com.kh.model.dao.ReplyDAO;
import com.kh.model.dto.BoardDTO;
import com.kh.model.dto.FileDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BoardService {

	public void getList(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		String temp = req.getParameter("page");
		int page = temp == null || temp.equals("") ? 1 : Integer.parseInt(temp);
		String keyword = req.getParameter("keyword");
		
		BoardDAO bdao = new BoardDAO();
		//전체 게시글의 개수
		long totalCnt = 0;
		if(keyword == null || keyword.equals("")) {
			totalCnt = bdao.getBoardCnt();
		}
		else {
			totalCnt = bdao.getBoardCnt(keyword);
		}
		
		//한 페이지에서 보여줄 게시글의 개수
		int pageSize = 10;
		
		//페이징 처리 시 아래에 나올 페이지 번호의 개수
		int pageCnt = 10;
		
		//아래쪽 페이징 처리 부분에 보여질 첫 번째 페이지 번호
		int startPage = (page-1)/pageCnt*pageCnt+1;
		
		//아래쪽 페이징 처리 부분에 보여질 마지막 페이지 번호
		int endPage = startPage + (pageCnt-1);
		
		//전체 개시글의 개수를 기반으로 한 띄워질 수 있는 가장 마지막 페이지(실제로 존재할 수 있는 가장 마지막 페이지) 번호
		int totalPage = (int)(totalCnt-1)/pageSize + 1;

		//가장 마지막 페이지 번호(totalPage)보다 단순한 연산으로 구해진 endPage가 더 큰 경우가 있다.(허구의 페이지 번호가 존재할 수 있다)
		//그 때는 endPage를 가장 마지막 페이지 번호(totalPage)로 바꿔주어야 한다.
		endPage = endPage > totalPage ? totalPage : endPage;
		
		int startRow = (page-1)*pageSize;
		List<BoardDTO> list = null;
		if(keyword == null || keyword.equals("")) {
			list = bdao.getList(startRow,pageSize);
		}
		else {
			list = bdao.getList(startRow,pageSize,keyword);
		}
		
		ReplyDAO rdao = new ReplyDAO();
		ArrayList<Integer> reply_cnt_list = new ArrayList<Integer>();
		ArrayList<String> hot_board = new ArrayList<String>();
		for(BoardDTO board : list) {
			reply_cnt_list.add(rdao.getReplyCnt(board.getBoardnum()));
			int cnt = rdao.getRecentReplyCnt(board.getBoardnum());
			if(cnt < 5) {
				hot_board.add("X");
			}
			else {
				hot_board.add("O");
			}
		}
		
		Gson gson = new Gson();
		JsonObject json = new JsonObject();
		json.add("list", gson.toJsonTree(list));
		json.add("reply_cnt_list", gson.toJsonTree(reply_cnt_list));
		json.add("hot_board", gson.toJsonTree(hot_board));
		
		json.addProperty("totalPage", totalPage);
		json.addProperty("totalCnt", totalCnt);
		json.addProperty("startPage", startPage);
		json.addProperty("endPage", endPage);
		json.addProperty("page", page);
		json.addProperty("keyword", keyword);
		
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		out.print(json.toString());
	}

	public void write(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		JsonObject json = new JsonObject();
		Gson gson = new Gson();
		//cos : http://www.servlets.com
		
		//파일이 실제로 저장될 경로
		String saveFolder = req.getServletContext().getRealPath("file");
		System.out.println(saveFolder);
		
		//저장될 파일의 최대 크기(1.5GB)
		int size = (int)(1024*1024*1024*1.5);
		
		//cos 이용
		MultipartRequest multi = new MultipartRequest(req, saveFolder, size,
				"UTF-8", new DefaultFileRenamePolicy());
		
		//						input[type=file]의 name 속성값들
		Enumeration<?> temp = multi.getFileNames();
		ArrayList<String> fileNames = new ArrayList<String>();
		while(temp.hasMoreElements()) {
			fileNames.add((String)temp.nextElement());
		}
		//fileNames 리스트에는 모든 input[type=file]의 name들이 추가되어 있다.
		//비어있는 input[type=file] name도 추가되어 있으므로
		int len = fileNames.size();
		//비어있는 그 name 하나 삭제
		fileNames.remove("file"+(len-1));
		
		Collections.reverse(fileNames);
		
		String boardtitle = multi.getParameter("boardtitle");
		String boardcontents = multi.getParameter("boardcontents");
		String userid = multi.getParameter("userid");
		
		BoardDTO board = new BoardDTO();
		board.setBoardtitle(boardtitle);
		board.setBoardcontents(boardcontents);
		board.setUserid(userid);
		
		System.out.println(board.getBoardtitle());
		System.out.println(board.getBoardcontents());
		System.out.println(board.getUserid());
		
		BoardDAO bdao = new BoardDAO();
		
		//파일 데이터 삽입 성공 여부
		boolean fcheck = false;
		//게시글을 쓸 때 파일을 업로드 했었는지 여부
		boolean flag = false;
		if(bdao.insertBoard(board)) {
			long boardnum = bdao.getLastNum(userid);
			FileDAO fdao = new FileDAO();
			
			for(String name : fileNames) {
				flag = true;
				String orgname = multi.getOriginalFileName(name);
				String systemname = multi.getFilesystemName(name);
				
				FileDTO fdto = new FileDTO();
				fdto.setBoardnum(boardnum);
				fdto.setOrgname(orgname);
				fdto.setSystemname(systemname);
				
				fcheck=fdao.insertFile(fdto);
				
				if(!fcheck) {
					break;
				}
			}
			//파일 업로드 했니?
			if(flag) {
				//DB삽입은 실패했니?
				if(!fcheck) {
					for(String name : fileNames) {
						String systemname = multi.getFilesystemName(name);
						//DB상에 있는 t_file 테이블에 올라갔던 내용들 삭제
						fdao.deleteFile(systemname);
						
						//실제 경로에 존재하는 파일을 자바의 객체로 가져옴
						File file = new File(saveFolder,systemname);
						//파일이 존재한다면
						if(file.exists()) {
							//삭제
							file.delete();
						}
					}
					bdao.deleteBoard(boardnum);
					//실패 페이지로 이동
					resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				}
			}
			
			//get
			json.addProperty("w", bdao.getLastNum(userid));
		}
		else {
			//list
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		out.print(json.toString());
	}

	public void getDetail(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		long boardnum = Long.parseLong(req.getParameter("boardnum"));
		String loginUser = (String)req.getSession().getAttribute("loginUser");
		
		BoardDAO bdao = new BoardDAO();
		
		BoardDTO board = bdao.getBoardByNum(boardnum);
		if(!board.getUserid().equals(loginUser)) {
			bdao.updateReadCount(boardnum);
			board.setReadcount(board.getReadcount()+1);
		}
		FileDAO fdao = new FileDAO();
		ReplyDAO rdao = new ReplyDAO();
		
		JsonObject json = new JsonObject();
		Gson gson = new Gson();
		
		json.add("board", gson.toJsonTree(board));
		json.add("files", gson.toJsonTree(fdao.getFiles(boardnum)));
		json.add("replies", gson.toJsonTree(rdao.getReplies(boardnum)));
		
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		out.print(json.toString());
	}

	public void delete(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		long boardnum = Long.parseLong(req.getParameter("boardnum"));
		BoardDAO bdao = new BoardDAO();
		
		String saveFolder = req.getServletContext().getRealPath("file");
		FileDAO fdao = new FileDAO();
		List<FileDTO> files = fdao.getFiles(boardnum);
		
		if(bdao.deleteBoard(boardnum)) {
			for(FileDTO fdto : files) {
				File file = new File(saveFolder,fdto.getSystemname());
				if(file.exists()) {
					fdao.deleteFile(fdto.getSystemname());
					file.delete();
				}
			}
			resp.setStatus(HttpServletResponse.SC_OK);
			resp.getWriter().print("ok");
		}
		else {
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

	public void update(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		String saveFolder = req.getServletContext().getRealPath("file");
		int size = (int)(1024*1024*1024*1.5);
		
		MultipartRequest multi = new MultipartRequest(req,saveFolder,size,"UTF-8",
				new DefaultFileRenamePolicy());
		//삭제해야 할 파일명들
		String updateCnt = multi.getParameter("updateCnt");
		System.out.println("updateCnt:"+updateCnt);
		long boardnum = Long.parseLong(multi.getParameter("boardnum"));
		String boardtitle = multi.getParameter("boardtitle");
		String boardcontents = multi.getParameter("boardcontents");
		String userid = multi.getParameter("userid");
		
		BoardDTO board = new BoardDTO();
		board.setBoardnum(boardnum);
		board.setBoardtitle(boardtitle);
		board.setBoardcontents(boardcontents);
		board.setUserid(userid);
		//input[type=file] 의 name들
		Enumeration<?> temp = multi.getFileNames();
		ArrayList<String> fileNames = new ArrayList<String>();
		while(temp.hasMoreElements()) {
			fileNames.add((String)temp.nextElement());
		}
		//fileNames 리스트에는 모든 input[type=file]의 name들이 거꾸로 추가되어 있다.
		int len = fileNames.size();
		Collections.reverse(fileNames);
		fileNames.remove(len-1);
		System.out.println("filenames : "+fileNames);
		
		FileDAO fdao = new FileDAO();
		//파일이 없음 O
		//파일이 있었고 수정을 안함 
		//파일이 있었고 수정을 함
		
		boolean flag = false;
		boolean fcheck = false;
		int cnt = 0;
		for(String name : fileNames) {
			flag = true;
			String orgname = multi.getOriginalFileName(name);
			String systemname = multi.getFilesystemName(name);
			//orgname이 null이라는 뜻은 실제 파일데이터는 날라오지 않았다는 뜻
			//기존 파일에서 변화 없이 그대로 뒀다는 뜻(새롭게 insert 하지 않아야 한다.)
			if(orgname == null){
				continue;
			}
			
			FileDTO fdto = new FileDTO();
			fdto.setBoardnum(boardnum);
			fdto.setOrgname(orgname);
			fdto.setSystemname(systemname);
			
			fcheck=fdao.insertFile(fdto);
			cnt++;
			if(!fcheck) {
				break;
			}
		}

		//파일 업로드 했니?
		if(flag) {
			if(cnt == 0) {
				
			}
			else {
				//DB삽입은 실패했니?
				if(!fcheck) {
					for(String name : fileNames) {
						String systemname = multi.getFilesystemName(name);
						if(systemname == null) {
							continue;
						}
						//DB상에 있는 t_file 테이블에 올라갔던 내용들 삭제
						fdao.deleteFile(systemname);
						
						//실제 경로에 존재하는 파일을 자바의 객체로 가져옴
						File file = new File(saveFolder,systemname);
						//파일이 존재한다면
						if(file.exists()) {
							//삭제
							file.delete();
						}
					}
					//실패 페이지로 이동
					resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				}
			}
		}
	
		//수정 혹은 삭제된 기존 파일들을 지우기 위한 로직
		String[] deleteFiles = updateCnt.split("\\\\");
		for(String name : deleteFiles) {
			File file = new File(saveFolder,name);
			if(file.exists()) {
				file.delete();
				fdao.deleteFile(name);
			}
		}
		
		BoardDAO bdao = new BoardDAO();
		if(bdao.updateBoard(board)) {
			resp.setStatus(HttpServletResponse.SC_OK);
			resp.getWriter().print("ok");
		}
		else {
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

	public void downloadFile(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String systemname = req.getParameter("systemname");
		String orgname = req.getParameter("orgname");
		String saveFolder = req.getServletContext().getRealPath("file");
		
		InputStream is = null;
		OutputStream os = null;
		
		//다운로드 받으려는 파일을 자바의 객체로 가져옴
		File file = new File(saveFolder,systemname);
		//그 파일을 향한 통로 개설(파일 데이터를 자바쪽으로 읽어오기 위한 스트림)
		is = new FileInputStream(file);
		
		//다운로드를 위한 준비
		resp.reset();
		//파일 다운로드를 요청한 클라이언트의 정보(브라우저,...)
		String client = req.getHeader("User-Agent");

		//파일을 응답해줄 준비, 세팅(응답 정보를 작성하는 과정, 파일 데이터를 응답할 것이라는 사전 정보 작성)
		resp.setContentType("application/octet-stream");
		resp.setHeader("Content-Description", "JSP Generated Data");
		
		String dwName = "";
		try {
			//파일 이름 인코딩(띄어쓰기)
			try {
				dwName = URLEncoder.encode(orgname,"UTF-8").replaceAll("\\+", "%20");
			} catch (UnsupportedEncodingException e) {
				dwName = URLEncoder.encode(systemname,"UTF-8").replaceAll("\\+", "%20");
			}
			
			//클라이언트의 브라우저가 MSIE라면
			if(client.indexOf("MSIE") != -1) {
				//이렇게 세팅  >>>>>>>>>>>>>>>>>>>>>>		attachment; fileName=apple.png
				resp.setHeader("Content-Disposition", "attachment; fileName="+dwName);
			}
			//그 외의 브라우저라면
			else {
				//저렇게 세팅 >>>>>>>>>>>>>>>>>>>>>>		attachment; fileName="apple.png"
				resp.setHeader("Content-Disposition", "attachment; fileName=\""+dwName+"\"");
			}
			//응답할 내용의 길이(파일의 길이)
			resp.setHeader("Content-Length", file.length()+"");
			
			//다운로드 받을 클라이언트 컴퓨터를 향한 통로 개설
			os = resp.getOutputStream();
			
			//파일 다운로드 : is를 통해 파일 데이터를 읽어서 os를 통해 그 데이터를 작성
			//b : 버퍼
			byte[] b = new byte[(int)file.length()];
			int len = 0;
			//is.read() : 버퍼에 데이터 읽기(파일 끝까지 읽었다면 -1)
			while((len = is.read(b,0,b.length)) != -1) {
				//읽은 데이터(len)을 os를 통해 작성
				os.write(b,0,len);
			}
		} catch (Exception e) {
			System.out.println("filedownloadaction : "+e);
		} finally {
			//통로들 다 닫기
			if(is != null) {
				is.close();
			}
			if(os != null) {
				os.close();
			}
		}
	}

}
