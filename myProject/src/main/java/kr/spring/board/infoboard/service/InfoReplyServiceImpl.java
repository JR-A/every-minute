package kr.spring.board.infoboard.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.board.infoboard.dao.InfoCommentLikeMapper;
import kr.spring.board.infoboard.dao.InfoReplyMapper;
import kr.spring.board.infoboard.vo.InfoReplyVO;


@Service("infoReplyService")
public class InfoReplyServiceImpl implements InfoReplyService {

	@Resource
	InfoReplyMapper infoReplyMapper;
	@Resource
	InfoCommentLikeMapper infoCommentLikeMapper;
	
	@Override 
	public List<InfoReplyVO> selectListReply(Map<String, Object> map) {
		return infoReplyMapper.selectListReply(map);
	}

	@Override
	public int selectRowCountReply(Map<String, Object> map) {
		return infoReplyMapper.selectRowCountReply(map);
	}

	@Override
	public void insertReply(InfoReplyVO boardReply) {
		infoReplyMapper.insertReply(boardReply);
		
	}

	@Override
	public void updateReply(InfoReplyVO boardReply) {
		infoReplyMapper.updateReply(boardReply);
	}

	@Override
	public void deleteReply(Integer comment_num) {
		infoReplyMapper.deleteReply(comment_num);
	}
}
