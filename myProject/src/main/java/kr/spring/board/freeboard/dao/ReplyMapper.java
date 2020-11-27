package kr.spring.board.freeboard.dao;

import java.util.List;
import java.util.Map;

import kr.spring.board.freeboard.vo.ReplyVO;

public interface ReplyMapper {
	public void create(ReplyVO reply);
	public void update(ReplyVO reply);
	public void delete(Integer comment_num);
	public List<ReplyVO> selectReply(int post_num);
}
