package kr.spring.board.freeboard.service;

import java.util.List;
import java.util.Map;

import kr.spring.board.freeboard.vo.ReplyVO;

public interface ReplyService {
	public List<ReplyVO> selectReply (int post_num);
	public void create(ReplyVO reply);
	public void update(ReplyVO reply);
	public void delete(Integer comment_num);
}
