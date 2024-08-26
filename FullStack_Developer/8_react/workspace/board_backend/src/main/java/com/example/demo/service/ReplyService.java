package com.example.demo.service;

import com.example.demo.domain.dto.Criteria;
import com.example.demo.domain.dto.ReplyDTO;
import com.example.demo.domain.dto.ReplyPageDTO;

public interface ReplyService {
	ReplyDTO regist(ReplyDTO reply);
	
	ReplyDTO getDetail(long replynum);
	ReplyPageDTO getList(Criteria cri, long boardnum);
	
	boolean modify(ReplyDTO reply);
	
	boolean remove(long replynum);
}
