package kr.spring.board.freeboard.service;

import java.util.List;
import java.util.Map;

import kr.spring.board.freeboard.vo.FreeReplyVO;

public interface FreeReplyService {
	public List<FreeReplyVO> selectListReply(Map<String,Object> map);
	public int selectRowCountReply(Map<String,Object> map);
	public void insertReply(FreeReplyVO boardReply);
	public void updateReply(FreeReplyVO boardReply);
	public void deleteReply(Integer re_num);
	}
