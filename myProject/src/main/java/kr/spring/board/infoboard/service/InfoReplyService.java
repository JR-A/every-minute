package kr.spring.board.infoboard.service;

import java.util.List;
import java.util.Map;

import kr.spring.board.infoboard.vo.InfoReplyVO;

public interface InfoReplyService {
	public List<InfoReplyVO> selectListReply(Map<String,Object> map);
	public void insertReply(InfoReplyVO boardReply);
	public void updateReply(InfoReplyVO boardReply);
	//댓글 개수
	public int selectRowCountReply(Integer post_num);
	//댓글 삭제
	public void deleteReply(Integer comment_num);
	//댓글 번호
	public List<Integer> selectReplyNum(int post_num);
}
