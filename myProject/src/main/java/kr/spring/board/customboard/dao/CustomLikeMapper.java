package kr.spring.board.customboard.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import kr.spring.board.customboard.vo.CustomLikeVO;

public interface CustomLikeMapper {
	
	//게시글 추천 수
	@Select("SELECT COUNT(*) FROM customboard_like_post WHERE post_num = #{post_num}")
	public int selectRowCountLike (Map<String,Object> map);
	//중복 추천여부 확인
	@Select("SELECT COUNT(*) FROM customboard_like_post WHERE post_num = #{post_num} AND mem_num = #{mem_num}")
	public int likeCount_user (Map<String,Object> map);
	//글쓴이인지 확인
    @Select("SELECT count(*) FROM (SELECT mem_num FROM custompost WHERE post_num=#{post_num}) WHERE mem_num=#{mem_num}")
	public int selectPostWriter(Map<String,Object> map);
	//게시글 추천 등록
	@Insert ("INSERT INTO customboard_like_post (like_num,post_num,mem_num) VALUES(custom_like_post_seq.NEXTVAL,#{post_num},#{mem_num})")
	public void insertLike (CustomLikeVO customLikeVO);
	//게시글 추천 삭제
	@Delete("DELETE FROM customboard_like_post WHERE post_num = #{post_num}")
	public void delete_like(Integer post_num);

}
