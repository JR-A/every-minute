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
	public void insertReply(InfoReplyVO boardReply) {
		infoReplyMapper.insertReply(boardReply);
		
	}

	@Override
	public void updateReply(InfoReplyVO boardReply) {
		infoReplyMapper.updateReply(boardReply);
	}
	//댓글 삭제
	@Override
	public void deleteReply(Integer comment_num) {
		infoCommentLikeMapper.deleteReplyLike(comment_num);
		infoReplyMapper.deleteReply(comment_num);
	}
	//게시글에 달린 댓글 번호
	@Override
	public List<Integer> selectReplyNum(int post_num) {
		return infoReplyMapper.selectReplyNum(post_num);
	}
	//댓글 개수
	@Override
	public int selectRowCountReply(Integer post_num) {
		return infoReplyMapper.selectRowCountReply(post_num);
	}

	
}
