package com.example.demo.service;

import java.io.File;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
	public List<BoardDTO> getList(Criteria cri) {
		List<BoardDTO> list = bmapper.getList(cri);
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
		return list;
	}

	@Override
	public long getTotal(Criteria cri) {
		return bmapper.getTotal(cri);
	}

	@Override
	public boolean regist(BoardDTO board, MultipartFile[] files) throws Exception{
		if(bmapper.insertBoard(board) != 1) {
			return false;
		}
		if(files == null || files.length == 0) {
			return true;
		}
		else {
			//방금 등록한 게시글 번호
			long boardnum = bmapper.getLastNum(board.getUserid());
			System.out.println("파일 개수 : "+files.length);
			
			for(int i=0;i<files.length-1;i++) {
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
			return true;
		}
	}
	
	@Override
	public BoardDTO getDetail(long boardnum) {
		return bmapper.getBoardByBoardnum(boardnum);
	}
	
	@Override
	public long getLastNum(String userid) {
		return bmapper.getLastNum(userid);
	}
	
	@Override
	public void increaseReadCount(long boardnum) {
		BoardDTO board = bmapper.getBoardByBoardnum(boardnum);
		//bmapper.updateReadCount(boardnum,board.getReadcount()+1) : 기존 게시글의 readcount 보다 1 증가시켜서 업데이트
		//bmapper.updateReadCount(boardnum,board.getReadcount()-1) : 기존 게시글의 readcount 보다 1 감소시켜서 업데이트
		//bmapper.updateReadCount(10,7) : 10번 게시글의 readcount를 7로 업데이트
		bmapper.updateReadCount(boardnum,board.getReadcount()+1);
	}

	@Override
	public boolean modify(BoardDTO board, MultipartFile[] files, String updateCnt) throws Exception {
		System.out.println(files);
		if(bmapper.updateBoard(board) != 1) {
			return false;
		}
		List<FileDTO> orgFileList = fmapper.getFiles(board.getBoardnum());
		if(orgFileList.size() == 0 && (files == null || files.length == 0)) {
			return true;
		}
		else {
			if(files != null && files.length != 0) {
				boolean flag = false;
				//후에 비즈니스 로직 실패 시 원래대로 복구하기 위해 업로드 성공했던 파일들도 삭제해 주어야 한다.
				//업로드 성공한 파일들의 이름을 해당 리스트에 추가하면서 로직을 진행한다.
				ArrayList<String> sysnames = new ArrayList<>();
				for (int i = 0; i < files.length-1; i++) {
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
					//board 원래대로 돌리기
				}
			}
			//지워져야 할 파일(기존에 있었던 파일들 중 수정된 파일)들의 이름 추출
			String[] deleteNames = updateCnt.split("\\\\");
			for(String systemname : deleteNames) {
				File file = new File(saveFolder,systemname);
				if(file.exists()) {
					file.delete();
				}
				fmapper.deleteFileBySystemname(systemname);
			}
			return true;
		}
	}
	
	@Override
	public boolean remove(long boardnum) {
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
			return true;
		}
		return false;
	}
	
	@Override
	public List<FileDTO> getFiles(long boardnum) {
		return fmapper.getFiles(boardnum);
	}
	
}

