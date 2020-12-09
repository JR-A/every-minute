package kr.spring.board.infoboard.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.board.infoboard.dao.InfoBoardMapper;
import kr.spring.board.infoboard.dao.InfoLikeMapper;
import kr.spring.board.infoboard.vo.InfoBoardVO;

@Service("infoBoardService")
public class InfoBoardServiceImpl implements InfoBoardService {

	@Resource //     ┌ InfoBoardMapper를 주입받음
	InfoBoardMapper infoBoardMapper;
	
	@Resource //     ┌ InfoBoardMapper를 주입받음
	InfoLikeMapper infoLikeMapper;
	
	@Override
	public List<InfoBoardVO> selectList(Map<String, Object> map) {
		return infoBoardMapper.selectList(map);
	}
	@Override
	public List<InfoBoardVO> selectTagList(Map<String, Integer> map) {
		return infoBoardMapper.selectTagList(map);
	}
	@Override
	public int selectRowCount(Map<String, Object> map) {
		return infoBoardMapper.selectRowCount(map);
	}
	
	@Override
	public int selectTagCount(Integer tag_num) {
		return infoBoardMapper.selectTagCount(tag_num);
	}

	@Override
	public void insertBoard(InfoBoardVO board) {
		infoBoardMapper.insertBoard(board);
	}

	@Override
	public InfoBoardVO selectBoard(Integer post_num) {
		return infoBoardMapper.selectBoard(post_num);
	}

	@Override
	public void updateBoard(InfoBoardVO board) {
		infoBoardMapper.updateBoard(board);
	}

	@Override
	public void deleteBoard(Integer post_num) {
		infoBoardMapper.deleteBlamePostByPostNum(post_num);
		infoLikeMapper.deleteLikeByPostNum(post_num);
		infoBoardMapper.deleteReplyByPostNum(post_num);
		infoBoardMapper.deleteBoard(post_num);
	}
	@Override
	public void insertBlameBoard(InfoBoardVO infoBoardVO) {
		infoBoardMapper.insertBlameBoard(infoBoardVO);
		
	}



}