package kr.spring.board.customboard.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.board.customboard.dao.CustomBoardMapper;
import kr.spring.board.customboard.vo.CustomBoardVO;

@Service("customBoardService")
public class CustomBoardServiceImpl implements CustomBoardService {


	@Resource //Mapper 주입받기
	private CustomBoardMapper customBoardMapper;
	
	//게시판 목록
	@Override
	public List<CustomBoardVO> selectBoardList() {
		return customBoardMapper.selectBoardList();
	} 
	//게시판 등록
	@Override 
	public void insertCustomBoard(CustomBoardVO customBoardVO) {
		customBoardMapper.insertCustomBoard(customBoardVO);
	}
	//자바빈 얻기
	@Override
	public CustomBoardVO selectCustomBoard(Integer board_num) {
		return customBoardMapper.selectCustomBoard(board_num);
	}
	//게시판 수정
	@Override
	public void updateCustomBoard(CustomBoardVO customBoardVO) {
		customBoardMapper.updateCustomBoard(customBoardVO);
	}
	//게시판 삭제
	@Override
	public void deleteCustomBoard(Integer board_num) {
		customBoardMapper.deleteCustomPost(board_num);
		customBoardMapper.deleteCustomBoard(board_num);
	}
	//게시판의 게시글 개수
	@Override
	public int hasPostCount(int board_num) {
		return customBoardMapper.hasPostCount(board_num);
	}

}
