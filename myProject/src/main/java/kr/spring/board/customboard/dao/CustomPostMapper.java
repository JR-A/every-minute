package kr.spring.board.customboard.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import kr.spring.board.customboard.vo.CustomBoardVO;
import kr.spring.board.customboard.vo.CustomPostVO;

public interface CustomPostMapper {
	
	//게시판 정보
	public CustomBoardVO selectBoardInfo(int board_num);
	
	//게시글 목록
	public List<CustomPostVO> selectPostList(Map<String, Object> map); 

	//페이징처리를 위한 글 count
	public int selectRowCount(Map<String, Object> map);
	
	//자바빈 얻기
	@Select("SELECT * FROM customPost p JOIN member m ON p.mem_num=m.mem_num WHERE p.board_num=#{board_num}")
	public CustomPostVO selectCustomPost(Integer board_num);

	//게시글 삭제
	@Delete("DELETE FROM CustomPost WHERE post_num=#{post_num}")
	public void deletePost(Integer post_num);
	
	//작성자 아이디 얻기_글 상세
	@Select("SELECT id FROM member JOIN customPost USING(mem_num) WHERE post_num=#{post_num}")
	public String selectPostWriter(Integer post_num);
	
	//게시글 작성
	public void insertPost(CustomPostVO postVO);
}
