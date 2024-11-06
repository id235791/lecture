package com.example.demo.service;

import java.io.File;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.domain.BoardDTO;
import com.example.demo.domain.Criteria;
import com.example.demo.domain.FileDTO;
import com.example.demo.domain.PageDTO;
import com.example.demo.mapper.BoardMapper;
import com.example.demo.mapper.FileMapper;
import com.example.demo.mapper.ReplyMapper;

@Service
public class BoardServiceImpl implements BoardService{

	@Value("${file.dir}")
	private String saveFolder;
	
	@Autowired
	private BoardMapper bmapper;
	@Autowired
	private ReplyMapper rmapper;
	@Autowired
	private FileMapper fmapper;
	
	@Override
	public HashMap<String, Object> getList(Criteria cri) {
		//실제 결과들을 담아줄 HashMap
		HashMap<String, Object> result = new HashMap<>();
		
		List<BoardDTO> list = bmapper.getList(cri);
		long total = bmapper.getTotal(cri);
		
		//현재 시간 정보
		LocalDateTime now = LocalDateTime.now();
		//시간 형태의 문자열을 해석하는 해석기
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		//게시글 하나씩 꺼내오면서
		for(BoardDTO board : list) {
			//게시글의 등록 시간 정보						해석	   게시글의 등록시간 문자열 을	dtf 형태에 맞추어서
			LocalDateTime regdate = LocalDateTime.parse(board.getRegdate(),dtf);
			//두 시간 사이의 걸린 시간 정보 
			Duration duration = Duration.between(regdate, now);
			long elapsedHours = duration.toHours();
			if(elapsedHours < 2) {
				board.setNew(true);
			}
			long replyCnt = rmapper.getTotal(board.getBoardnum());
			board.setReplyCnt(replyCnt);
			int recentReplyCnt = rmapper.getRecentReplyCnt(board.getBoardnum());
			if(recentReplyCnt > 5) {
				board.setHot(true);
			}
		}
		//여기까지 왔다면 리스트 페이지를 생성하기 위한 모든 데이터들의 준비가 끝났으므로, result에 전부 추가
		result.put("list",list);
		result.put("pageMaker", new PageDTO(total, cri));
		
		return result;
	}

	@Override
	public long regist(BoardDTO board, MultipartFile[] files) throws Exception{
		if(bmapper.insertBoard(board) != 1) {
			return -1;
		}
		long boardnum = bmapper.getLastNum(board.getUserid());
		if(files == null || files.length == 0) {
			return boardnum;
		}
		else {
			//방금 등록한 게시글 번호
			System.out.println("파일 개수 : "+files.length);
			
			for(int i=0;i<files.length;i++) {
				MultipartFile file = files[i];
				
				//apple.png
				String orgname = file.getOriginalFilename();
				//5
				int lastIdx = orgname.lastIndexOf(".");
				//.png
				String ext = orgname.substring(lastIdx);
				
				LocalDateTime now = LocalDateTime.now();
				//20240911161030123
				String time = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
				//20240911161030123랜덤문자열.png
				String systemname = time+UUID.randomUUID().toString()+ext;
				
				//실제 생성될 파일의 경로
				// D:/0900_GB_JDS/6_spring/file/20240911161030123랜덤문자열.png
				String path = saveFolder+systemname;
				
				//FileDTO 로 DB에 업로드 될 파일의 정보들 저장
				FileDTO fdto = new FileDTO();
				fdto.setOrgname(orgname);
				fdto.setSystemname(systemname);
				fdto.setBoardnum(boardnum);
				fmapper.insertFile(fdto);
				
				//실제 파일 업로드
				file.transferTo(new File(path));
			}
			return boardnum;
		}
	}
	
	@Override
	public HashMap<String, Object> getDetail(long boardnum,String loginUser) {
		HashMap<String, Object> result = new HashMap<>();
		
		BoardDTO board = bmapper.getBoardByBoardnum(boardnum);
		List<FileDTO> files = fmapper.getFiles(boardnum);
		
		if(board != null && !board.getUserid().equals(loginUser)) {
			bmapper.updateReadCount(boardnum, board.getReadcount()+1);
			board.setReadcount(board.getReadcount()+1);
		}
		
		result.put("board",board);
		result.put("files", files);
		
		return result;
	}
	
	
	@Override
	public long modify(BoardDTO board, MultipartFile[] files, String[] deleteFiles) throws Exception {
		System.out.println(files);
		long boardnum = board.getBoardnum();
		//실패시 원래대로 돌아가기 위한 이전의 정보 검색
		BoardDTO orgBoard = bmapper.getBoardByBoardnum(boardnum);
		//데이터베이스의 데이터 수정 실패시 그냥 실패
		if(bmapper.updateBoard(board) != 1) {
			return -1;
		}
		
		List<FileDTO> orgFileList = fmapper.getFiles(board.getBoardnum());
		if(orgFileList.size() == 0 && (files == null || files.length == 0)) {
			return boardnum;
		}
		else {
			if(files != null && files.length != 0) {
				boolean flag = false;
				//후에 비즈니스 로직 실패 시 원래대로 복구하기 위해 업로드 성공했던 파일들도 삭제해 주어야 한다.
				//업로드 성공한 파일들의 이름을 해당 리스트에 추가하면서 로직을 진행한다.
				ArrayList<String> sysnames = new ArrayList<>();
				for (int i = 0; i < files.length; i++) {
					MultipartFile file = files[i];
					String orgname = file.getOriginalFilename();
					//수정의 경우 중간에 있는 파일이 수정되지 않은 경우도 있다.(원본 파일 그대로 둔 경우)
					//그런 경우 file의 orgname은 null 이거나 "" 이다.
					//따라서 파일 처리를 할 필요가 없으므로 반복문을 넘어간다.
					if(orgname == null || orgname.equals("")) {
						continue;
					}
					//파일 업로드 과정(regist와 동일)
					int lastIdx = orgname.lastIndexOf(".");
					String ext = orgname.substring(lastIdx);
					LocalDateTime now = LocalDateTime.now();
					String time = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
					String systemname = time+UUID.randomUUID().toString()+ext;
					String path = saveFolder+systemname;
					FileDTO fdto = new FileDTO();
					fdto.setOrgname(orgname);
					fdto.setSystemname(systemname);
					fdto.setBoardnum(board.getBoardnum());
					flag = fmapper.insertFile(fdto) == 1;
					file.transferTo(new File(path));
					
					//업로드 성공한 파일의 이름(systemname)을 sysnames 리스트에 추가
					sysnames.add(systemname);
					
					if(!flag) {
						break;
					}
				}
				//강제탈출(DB insert 실패)
				if(!flag) {
					//업로드 했던 파일 삭제, 게시글 데이터 돌려놓기, 파일 data(실제 파일) 삭제, ...
					//아까 추가했던 systemname들(업로드 성공했던 파일의 이름)을 꺼내오면서
					for(String systemname : sysnames) {
						//실제 파일이 존재한다면 삭제
						File file = new File(saveFolder,systemname);
						if(file.exists()) {
							file.delete();
						}
						//DB상에서도 삭제
						fmapper.deleteFileBySystemname(systemname);
					}
					//board 원래대로 돌리기(orgBoard 이용)
				}
				else {
					// [apple.png, banana.png]
					for(String systemname : deleteFiles) {
						File file = new File(saveFolder,systemname);
						if(file.exists()) {
							file.delete();
						}
						fmapper.deleteFileBySystemname(systemname);
					}
					return boardnum;
				}
			}
		}
		return -1;
	}
	
	@Override
	public long remove(long boardnum) {
		if(bmapper.deleteBoard(boardnum) == 1) {
			rmapper.deleteRepliesByBoardnum(boardnum);
			List<FileDTO> files = fmapper.getFiles(boardnum);
			//게시글에 달린 파일의 정보들을 하나씩 꺼내오며
			for(FileDTO fdto : files) {
				//saveFolder(파일이 저장되는 폴더)에서 꺼내온 DTO의 systemname에 해당하는 파일을 자바의 객체로 가져옴
				File file = new File(saveFolder,fdto.getSystemname());
				//그 파일이 존재한다면
				if(file.exists()) {
					//삭제
					file.delete();
					//DB상에서도 삭제
					fmapper.deleteFileBySystemname(fdto.getSystemname());
				}
			}
			return boardnum;
		}
		return -1;
	}
}










