package kr.spring.board.freeboard.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.board.freeboard.vo.FreeLikeVO;

public interface FreeLikeMapper {
	//게시글 추천 수
	@Select("SELECT COUNT(*) FROM freeboard_like_post WHERE post_num = #{post_num}")
	public int selectRowCountLike (Map<String,Object> map);
	@Select("SELECT COUNT(*) FROM freeboard_like_post WHERE post_num = #{post_num} AND mem_num = #{mem_num}")
	public int selectRowCountLikeByMem_num (Map<String,Object> map);
	//게시글 추천 insert
    @Insert ("INSERT INTO freeboard_like_post (like_num,post_num,mem_num) VALUES(free_post_like_SEQ.NEXTVAL,#{post_num},#{mem_num})")
	public void insertLike (FreeLikeVO freeLikeVO);
	//게시글 추천 추가
	public void count_update(FreeLikeVO freeLikeVO);
	//게시글 추천 삭제
	@Delete("DELETE FROM freeboard_like_post WHERE post_num = #{post_num}")
	public void delete_like(Integer post_num);

}