package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.dto.Criteria;
import com.example.demo.domain.dto.ReplyDTO;
import com.example.demo.domain.dto.ReplyPageDTO;
import com.example.demo.mapper.ReplyMapper;

@Service
public class ReplyServiceImpl implements ReplyService{
	@Autowired
	private ReplyMapper rmapper;

	@Override
	public ReplyDTO regist(ReplyDTO reply) {
		//등록 데이터 로직
		if(rmapper.insertReply(reply) == 1) {
			//검색 데이터 로직
			return rmapper.getLastReply(reply.getUserid());
		}
		return null;
	}

	@Override
	public ReplyDTO getDetail(long replynum) {
		return null;
	}
	
	@Override
	public ReplyPageDTO getList(Criteria cri, long boardnum) {
		return new ReplyPageDTO(rmapper.getTotal(boardnum), rmapper.getList(cri, boardnum));
	}

	@Override
	public boolean modify(ReplyDTO reply) {
		return rmapper.updateReply(reply) == 1;
	}

	@Override
	public boolean remove(long replynum) {
		return rmapper.deleteReply(replynum) == 1;
	}
	
}