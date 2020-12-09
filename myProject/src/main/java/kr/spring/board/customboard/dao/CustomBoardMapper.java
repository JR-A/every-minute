package kr.spring.board.customboard.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import kr.spring.board.customboard.vo.CustomBoardVO;

public interface CustomBoardMapper {	

	//게시판 목록 가져오기
	@Select("SELECT board_num, title, anonymous FROM customBoard ORDER BY board_num desc")
	public List<CustomBoardVO> selectBoardList();
	//게시판 추가 
	public void insertCustomBoard(CustomBoardVO customBoardVO);
	//게시판 정보
	@Select("SELECT * FROM CustomBoard WHERE board_num=#{board_num}")
	public CustomBoardVO selectCustomBoard(Integer board_num);
	//게시판 수정
	public void updateCustomBoard(CustomBoardVO customBoardVO);
	//게시판 삭제
	@Delete("DELETE FROM CustomBoard WHERE board_num=#{board_num}")
	public void deleteCustomBoard(Integer board_num);
	//게시판 하위 게시글 전체 삭제
	@Delete("DELETE CustomPost WHERE board_num=#{board_num}")
	public void deleteCustomPost(Integer board_num);
	//게시판의 게시글 개수
	@Select("SELECT COUNT(*) FROM CustomPost WHERE board_num = #{board_num}")
	public int hasPostCount(int board_num);
}
