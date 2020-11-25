package kr.spring.board.infoboard.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.board.infoboard.dao.InfoBoardMapper;
import kr.spring.board.infoboard.vo.InfoBoardVO;

@Service("infoBoardService")
public class InfoBoardServiceImpl implements InfoBoardService {

	@Resource //     ┌ InfoBoardMapper를 주입받음
	InfoBoardMapper InfoBoardMapper;
	
	@Override
	public List<InfoBoardVO> selectList(Map<String, Object> map) {
		return InfoBoardMapper.selectList(map);
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		return InfoBoardMapper.selectRowCount(map);
	}

	@Override
	public void insertBoard(InfoBoardVO board) {
		InfoBoardMapper.insertBoard(board);
	}

	@Override
	public InfoBoardVO selectBoard(Integer post_num) {
		return InfoBoardMapper.selectBoard(post_num);
	}

	@Override
	public void updateBoard(InfoBoardVO board) {
		InfoBoardMapper.updateBoard(board);
	}

	@Override
	public void deleteBoard(Integer post_num) {
		InfoBoardMapper.deleteBoard(post_num);
		
	}

}