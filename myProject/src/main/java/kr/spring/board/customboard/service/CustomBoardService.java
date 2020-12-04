package kr.spring.board.customboard.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;

import kr.spring.board.customboard.vo.CustomBoardVO;

public interface CustomBoardService {

	//사용자 게시판 이름 목록
	public List<CustomBoardVO> selectBoardList(); 
	//게시판 등록
	public void insertCustomBoard(CustomBoardVO customBoardVO);
	//자바빈 얻기
	public CustomBoardVO selectCustomBoard(Integer board_num);
	//게시판 수정
	public void updateCustomBoard(CustomBoardVO customBoardVO);
	//게시판 삭제
	public void deleteCustomBoard(Integer board_num);
	//게시판의 게시글 개수
	public int hasPostCount(int board_num);
}
