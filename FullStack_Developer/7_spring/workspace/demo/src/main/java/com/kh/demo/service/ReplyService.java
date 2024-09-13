package com.kh.demo.service;

import com.kh.demo.domain.dto.Criteria;
import com.kh.demo.domain.dto.ReplyDTO;
import com.kh.demo.domain.dto.ReplyPageDTO;

public interface ReplyService {
	public ReplyDTO regist(ReplyDTO reply);

	public ReplyPageDTO getList(Criteria cri, long boardnum);

	public boolean remove(long replynum);

	public boolean modify(ReplyDTO reply);

}
