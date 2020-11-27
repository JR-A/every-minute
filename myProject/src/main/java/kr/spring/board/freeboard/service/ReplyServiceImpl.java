package kr.spring.board.freeboard.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.board.freeboard.dao.ReplyMapper;
import kr.spring.board.freeboard.vo.ReplyVO;


@Service("replyService")
public class ReplyServiceImpl implements ReplyService {

	@Resource
	ReplyMapper replyMapper;
	
	
	@Override
	public void create(ReplyVO reply) {
		 replyMapper.create(reply);
		
	}

	@Override
	public void update(ReplyVO reply) {
		replyMapper.update(reply);
		
	}

	@Override
	public void delete(Integer comment_num) {
		replyMapper.delete(comment_num);
		
	}

	@Override
	public List<ReplyVO> selectReply(int post_num) {
		return replyMapper.selectReply(post_num);
	}


}
