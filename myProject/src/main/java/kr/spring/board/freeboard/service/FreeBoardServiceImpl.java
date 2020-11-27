package kr.spring.board.freeboard.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.board.freeboard.dao.FreeBoardMapper;
import kr.spring.board.freeboard.vo.FreeBoardVO;

@Service("freeBoardService")
public class FreeBoardServiceImpl implements FreeBoardService {

	@Resource
	FreeBoardMapper freeBoardMapper;
	
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
		freeBoardMapper.deleteBoard(post_num);
		
	}

	
		
	}
