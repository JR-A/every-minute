package kr.spring.board.freeboard.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.board.freeboard.dao.FreeBlameMapper;
import kr.spring.board.freeboard.dao.FreeReplyLikeMapper;
import kr.spring.board.freeboard.dao.FreeReplyMapper;
import kr.spring.board.freeboard.vo.FreeReplyVO;


@Service("freeReplyService")
public class FreeReplyServiceImpl implements FreeReplyService {

	@Resource
	FreeBlameMapper freeBlameMapper;
	
	@Resource
	FreeReplyMapper freeReplyMapper;
	@Resource
	FreeReplyLikeMapper freeReplyLikeMapper;
	@Override
	public List<FreeReplyVO> selectListReply(Map<String, Object> map) {
		
		return freeReplyMapper.selectListReply(map);
	}

	@Override
	public int selectRowCountReply(Map<String, Object> map) {
		return freeReplyMapper.selectRowCountReply(map);
	}

	@Override
	public void insertReply(FreeReplyVO boardReply) {
		freeReplyMapper.insertReply(boardReply);
		
	}

	@Override
	public void updateReply(FreeReplyVO boardReply) {
		freeReplyMapper.updateReply(boardReply);
	}

	@Override
	public void deleteReply(Integer re_num) {
		freeBlameMapper.deleteBlamePostByCommentNum(re_num);
		freeReplyLikeMapper.delete_like(re_num);
		freeReplyMapper.deleteReply(re_num);
	}

	@Override
	public List<Integer> selectCommNum(int post_num) {
		return freeReplyMapper.selectCommNum(post_num);
	}


	}





