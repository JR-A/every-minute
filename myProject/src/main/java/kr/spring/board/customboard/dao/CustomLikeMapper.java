package kr.spring.board.customboard.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import kr.spring.board.customboard.vo.CustomLikeVO;
import kr.spring.board.customboard.vo.CustomPostVO;

public interface CustomLikeMapper {
	
	//가장 많은 추천 수를 가진 게시글
	public CustomPostVO custom_bestLikePost();
	
	//게시글 추천 수
	@Select("SELECT COUNT(*) FROM customboard_like_post WHERE post_num = #{post_num}")
	public int selectRowCount_postLike (Map<String,Object> map);
	//게시글 중복 추천여부 확인
	@Select("SELECT COUNT(*) FROM customboard_like_post WHERE post_num = #{post_num} AND mem_num = #{mem_num}")
	public int likePostCount_user (Map<String,Object> map);
	//게시글 작성자인지 확인
    @Select("SELECT count(*) FROM (SELECT mem_num FROM custompost WHERE post_num=#{post_num}) WHERE mem_num=#{mem_num}")
	public int selectPostWriter(Map<String,Object> map);
	//게시글 추천 등록
	@Insert ("INSERT INTO customboard_like_post (like_num,post_num,mem_num) VALUES(custom_like_post_seq.NEXTVAL,#{post_num},#{mem_num})")
	public void insertPostLike (CustomLikeVO customLikeVO);
	//게시글 추천 삭제
	@Delete("DELETE FROM customboard_like_post WHERE post_num = #{post_num}")
	public void deletePostLike(Integer post_num);
	
	//댓글 추천 수
	@Select("SELECT COUNT(*) FROM customboard_like_comment WHERE comment_num = #{comment_num}")
	public int selectRowCount_commLike (Map<String,Object> map);
	//댓글 중복 추천여부 확인
	@Select("SELECT COUNT(*) FROM customboard_like_comment WHERE comment_num = #{comment_num} AND mem_num = #{mem_num}")
	public int likeCommCount_user (Map<String,Object> map);
	//댓글 작성자인지 확인
    @Select("SELECT count(*) FROM (SELECT mem_num FROM customboard_comment WHERE comment_num=#{comment_num}) WHERE mem_num=#{mem_num}")
	public int selectCommWriter(Map<String,Object> map);
	//댓글 추천 등록
	@Insert ("INSERT INTO customboard_like_comment (like_num, comment_num, mem_num) VALUES(custom_like_post_seq.NEXTVAL, #{comment_num}, #{mem_num})")
	public void insertCommLike (CustomLikeVO customLikeVO);
	//댓글 추천 삭제
	@Delete("DELETE FROM customboard_like_comment WHERE comment_num = #{comment_num}")
	public void deleteCommLike(Integer comment_num);

}
