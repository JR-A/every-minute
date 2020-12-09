package kr.spring.board.infoboard.service;

import java.util.List;
import java.util.Map;

import kr.spring.board.infoboard.vo.InfoReplyVO;

public interface InfoReplyService {
	public List<InfoReplyVO> selectListReply(Map<String,Object> map);
	public int selectRowCountReply(Map<String,Object> map);
	public void insertReply(InfoReplyVO boardReply);
	public void updateReply(InfoReplyVO boardReply);
	public void deleteReply(Integer re_num);
}
