package kr.spring.board.customboard.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import kr.spring.board.customboard.vo.CustomPostVO;

public interface CustomPostMapper {
	
	//게시글 목록
	public List<CustomPostVO> selectPostList(Map<String, Object> map); 
	//페이징처리를 위한 글 count
	public int selectRowCount(Integer board_num);
	//자바빈 얻기
	@Select("SELECT * FROM (SELECT * FROM CustomPost p JOIN member m ON p.mem_num=m.mem_num) WHERE post_num=#{post_num}")
	public CustomPostVO selectCustomPost(Integer post_num);
	//게시글 작성
	public void insertPost(CustomPostVO postVO);
	//게시글 수정
	public void customPostUpdate(CustomPostVO postVO);
	//게시글 삭제
	@Delete("DELETE FROM CustomPost WHERE post_num=#{post_num}")
	public void deletePost(Integer post_num);
}