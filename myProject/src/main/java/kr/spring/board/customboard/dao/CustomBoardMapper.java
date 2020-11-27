package kr.spring.board.customboard.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import kr.spring.board.customboard.vo.CustomBoardVO;

public interface CustomBoardMapper {	

	//게시판 목록 가져오기
	@Select("SELECT board_num, title FROM customBoard ORDER BY board_num desc")
	public List<CustomBoardVO> selectBoardList();
	
	//게시판 추가 
	public void insertCustomBoard(CustomBoardVO customBoardVO);
	
	//자바빈 얻기
	@Select("SELECT * FROM CustomBoard WHERE board_num=#{board_num}")
	public CustomBoardVO selectCustomBoard(Integer board_num);
}
