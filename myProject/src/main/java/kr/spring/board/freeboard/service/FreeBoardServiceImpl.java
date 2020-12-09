package kr.spring.board.freeboard.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.board.freeboard.dao.FreeBoardMapper;
import kr.spring.board.freeboard.dao.FreeLikeMapper;
import kr.spring.board.freeboard.dao.FreeReplyMapper;
import kr.spring.board.freeboard.vo.FreeBoardVO;

@Service("freeBoardService")
public class FreeBoardServiceImpl implements FreeBoardService {

	@Resource
	FreeBoardMapper freeBoardMapper;
	@Resource
	FreeReplyMapper freeReplyMapper;
	@Resource
	FreeLikeMapper freeLikeMapper;
	
	@Override
	   public List<FreeBoardVO> freeSelectTop3PostList() {
	      return freeBoardMapper.freeSelectTop3PostList();
	   }
	
	@Override
	public List<FreeBoardVO> selectList(Map<String, Object> map) {
		return freeBoardMapper.selectList(map);
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		return freeBoardMapper.selectRowCount(map);
	}

	@Override
	public void insertBoard(FreeBoardVO board) {
		freeBoardMapper.insertBoard(board);
		
	}

	@Override
	public FreeBoardVO selectBoard(Integer post_num) {
		return  freeBoardMapper.selectBoard(post_num);
	}

	@Override
	public void updateBoard(FreeBoardVO board) {
		freeBoardMapper.updateBoard(board);
		
	}

	@Override
	public void deleteBoard(Integer post_num) {
		//추천이 있으면 추천을 먼제 삭제
		freeLikeMapper.delete_like(post_num);
		//댓글이 존재하면 댓글을 우선 삭제하고 부모글을 삭제
		freeReplyMapper.deleteReplyByPostNum(post_num);
		freeBoardMapper.deleteBoard(post_num);
		
	}

	
		
	}
