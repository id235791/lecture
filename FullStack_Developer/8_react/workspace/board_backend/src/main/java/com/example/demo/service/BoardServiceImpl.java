package com.example.demo.service;

import java.io.File;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.domain.dto.BoardDTO;
import com.example.demo.domain.dto.Criteria;
import com.example.demo.domain.dto.FileDTO;
import com.example.demo.domain.dto.PageDTO;
import com.example.demo.mapper.BoardMapper;
import com.example.demo.mapper.FileMapper;
import com.example.demo.mapper.ReplyMapper;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Value("${file.dir}")
	private String saveFolder;
	
	@Autowired
	private BoardMapper bmapper;
	@Autowired
	private FileMapper fmapper;
	@Autowired
	private ReplyMapper rmapper;

	@Override
	public long regist(BoardDTO board, MultipartFile[] files) throws Exception {
		if(bmapper.insertBoard(board) != 1) {
			return -1;
		}
		//등록을 성공했으면 가장 마지막으로 등록된 게시글의 번호를 받아옴
		long boardnum = bmapper.getLastNum(board.getUserid());
		if(files == null || files.length == 0) {
			return boardnum;
		}
		else {
			boolean flag = false;
			System.out.println("파일 개수 : "+files.length);
			//후에 비즈니스 로직 실패 시 원래대로 복구하기 위해 업로드 성공했던 파일들도 삭제해 주어야 한다.
			//업로드 성공한 파일들의 이름을 해당 리스트(sysnames)에 추가하면서 로직을 진행한다.
			ArrayList<String> sysnames = new ArrayList<>();		
			for(int i=0;i<files.length;i++) {
				MultipartFile file = files[i];
				System.out.println(file.getOriginalFilename());
				
				//apple.png
				String orgname = file.getOriginalFilename();
				//5
				int lastIdx = orgname.lastIndexOf(".");
				//.png
				String extension = orgname.substring(lastIdx);
				
				LocalDateTime now = LocalDateTime.now();
				String time = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));

				//20240502162130141랜덤문자열.png
				String systemname = time+UUID.randomUUID().toString()+extension;
				
				//실제 생성될 파일의 경로
				//D:/0900_GB_JDS/7_spring/file/20240502162130141랜덤문자열.png
				String path = saveFolder+systemname;
				
				//File DB 저장
				FileDTO fdto = new FileDTO();
				fdto.setBoardnum(boardnum);
				fdto.setOrgname(orgname);
				fdto.setSystemname(systemname);
				flag = fmapper.insertFile(fdto) == 1;
				
				//실제 파일 업로드
				file.transferTo(new File(path));
				//업로드 성공한 파일의 이름을 sysnames에 추가
				sysnames.add(systemname);				
				//실패시 강제탈출
				if(!flag) {
					break;
				}
			}
			if(!flag) {
				//업로드했던 파일 삭제, 게시글 데이터 삭제, 파일 data 삭제, ...
				//아까 추가했던 systemname들(업로드 성공한 파일의 이름)을 꺼내오면서
				for(String systemname : sysnames){
					//실제 파일이 존재한다면 삭제
					File file = new File(saveFolder,systemname);
					if(file.exists()) {
						file.delete();
					}
					//DB에서도 삭제
					fmapper.deleteFileBySystemname(systemname);
				}
				//bmapper.deleteBoard();
				return -1;
			}
			return boardnum;
		}
	}

	@Override
	public HashMap<String, Object> getDetail(long boardnum, String loginUser) {
		HashMap<String, Object> datas = new HashMap<>();
		BoardDTO board = bmapper.getBoardByNum(boardnum);
		List<FileDTO> files = fmapper.getFiles(boardnum);
		datas.put("board", board);
		datas.put("files", files);
		if(board != null && !board.getUserid().equals(loginUser)){
			board.setReadcount(board.getReadcount()+1);
			bmapper.updateReadCount(boardnum, board.getReadcount());
		}
		return datas;
	}

	@Override
	public HashMap<String, Object> getList(Criteria cri) {
		//실제 결과들을 담아줄 HashMap
		HashMap<String, Object> result = new HashMap<>();
		
		//게시글 리스트 검색
		List<BoardDTO> list = bmapper.getList(cri);
		List<Long> replyCntList = new ArrayList<>();
		//전체 게시글 개수 검색
		long total = bmapper.getTotal(cri);
		//검색된 게시글 리스트로 반복문을 돌며 BoardDTO 하나씩 꺼내오기
		for(BoardDTO board : list) {
			//꺼내온 board의 최근 댓글 개수(1시간 이내에 등록된 개수) 검색
			int recentReplyCnt = rmapper.getRecentReplyCnt(board.getBoardnum());
			//그 개수가 5개보다 많다면 hot
			if(recentReplyCnt >= 5) {
				//꺼내온 BoardDTO의 hotBoard를 true로 세팅
				board.setHotBoard(true);
			}
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime regdate = LocalDateTime.parse(board.getRegdate(),fmt);
			Duration duration = Duration.between(regdate, now);
			long elapsedHours = duration.toHours();
			if(elapsedHours < 3) {
				board.setNewBoard(true);
			}
			//꺼내온 board의 전체 댓글 개수 검색
			long replyCnt = rmapper.getTotal(board.getBoardnum());
			//replyCntList에 댓글 개수들 추가
			replyCntList.add(replyCnt);
		}
		//여기까지 왔다면 리스트 페이지를 생성하기 위한 모든 데이터들의 준비가 끝났으므로, result에 전부 추가
		result.put("list", list);
		result.put("replyCntList", replyCntList);
		result.put("pageMaker",new PageDTO(total, cri));
		
		return result;
	}

	@Override
	public long modify(BoardDTO board, MultipartFile[] files, String[] deleteFiles) throws Exception {
		//실패시 원래대로 돌아가기 위한 이전의 정보 검색
		BoardDTO orgBoard = bmapper.getBoardByNum(board.getBoardnum());
		//데이터베이스의 데이터 수정 실패시 그냥 실패
		if(bmapper.updateBoard(board) != 1) {
			return -1;
		}
		//수정을 성공했다면 현재 수정된 게시글의 번호 받기
		long boardnum = board.getBoardnum();
		//그 번호로 등록되어 있던 모든 파일의 정보 검색
		List<FileDTO> orgFileList = fmapper.getFiles(boardnum);
		//만약 등록되어 있던 파일이 없었고, 새롭게 전달된 파일도 없다면 그대로 수정은 성공으로 종료
		if(orgFileList.size() == 0 && (files == null || files.length == 0)) {
			return boardnum;
		}
		//만약 등록되어 있던 파일이 있었거나, 새롭게 전달된 파일이 있다면 파일에 관련된 처리 진행
		else {
			boolean flag = true;
			//등록되어 있던 파일들을 다 지운 상태
			//regist와 동일한 이유로 sysnames 리스트 생성
			ArrayList<String> sysnames = new ArrayList<>();
			if(files != null) {
				flag = false;
				for(int i=0;i<files.length-1;i++) {
					MultipartFile file = files[i];
					String orgname = file.getOriginalFilename();
					//수정의 경우 중간에 있는 파일이 수정되지 않은 경우도 있다.
					//그런 경우의 file의 orgname은 null이거나 "" 이다.
					//따라서 파일 처리를 할 필요가 없으므로 반복문을 넘어간다.
					if(orgname == null || orgname.equals("")) {
						continue;
					}
					//파일 업로드 과정(regist와 동일)
					int lastIdx = orgname.lastIndexOf(".");
					String extension = orgname.substring(lastIdx);
					LocalDateTime now = LocalDateTime.now();
					String time = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
					String systemname = time+UUID.randomUUID().toString()+extension;
					
					String path = saveFolder+systemname;
					
					//File DB 저장
					FileDTO fdto = new FileDTO();
					fdto.setBoardnum(boardnum);
					fdto.setOrgname(orgname);
					fdto.setSystemname(systemname);
					flag = fmapper.insertFile(fdto) == 1;
					
					//실제 파일 업로드
					file.transferTo(new File(path));
					//업로드 성공한 파일의 이름을 sysnames에 추가
					sysnames.add(systemname);
					
					if(!flag) {
						break;
					}
				}
			}
			//강제탈출 한 경우
			if(!flag){
				//업로드했던 파일과 그 파일의 데이터 삭제, 게시글 데이터 복구, ...
				//아까 추가했던 systemname들(업로드 성공한 파일의 이름)을 꺼내오면서
				for(String systemname : sysnames){
					//실제 파일이 존재한다면 삭제
					File file = new File(saveFolder,systemname);
					if(file.exists()) {
						file.delete();
					}
					//DB에서도 삭제
					fmapper.deleteFileBySystemname(systemname);
				}
				//bmapper.updateBoard(orgBoard);
				return -1;
			}
			//정상적으로 로직을 완료하고 정상적으로 탈출한 경우
			else {
				//지워져야 할 파일(기존에 있었던 파일들 중 수정된 파일)들의 이름 추출
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

	@Override
	public long remove(long boardnum) {
		if(bmapper.deleteBoard(boardnum) == 1) {
			rmapper.deleteRepliesByBoardnum(boardnum);
			List<FileDTO> files = fmapper.getFiles(boardnum);
			for(FileDTO fdto : files){
				String systemname = fdto.getSystemname();
				//실제 파일이 존재한다면 삭제
				File file = new File(saveFolder,systemname);
				if(file.exists()) {
					file.delete();
				}
				//DB에서도 삭제
				fmapper.deleteFileBySystemname(systemname);
			}
			return boardnum;
		}
		return -1;
	}
	
}
