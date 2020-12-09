package kr.spring.board.freeboard.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import kr.spring.board.freeboard.vo.FreeReplyLikeVO;

public interface FreeReplyLikeMapper {
	@Select("SELECT COUNT(*) FROM freeboard_like_comment WHERE comment_num = #{comment_num}")
	public int selectRowCountLike_R (Map<String,Object> map);
	@Select("SELECT COUNT(*) FROM freeboard_like_comment WHERE comment_num = #{comment_num} AND mem_num = #{mem_num}")
	public int selectRowCountLike_RByMem_num (Map<String,Object> map);
	@Select("SELECT COUNT(*) FROM freeboard_like_comment l JOIN freeboard_comment f ON l.mem_num=f.mem_num WHERE f.mem_num=#{mem_num} and f.comment_num=#{comment_num}")
	public int selectSameMember_R(Map<String,Object> map);
	//게시글 추천 insert
	@Insert ("INSERT INTO freeboard_like_comment (like_num,comment_num,mem_num) VALUES(free_like_comment_SEQ.NEXTVAL,#{comment_num},#{mem_num})")
	public void insertLike_R (FreeReplyLikeVO FreeReplyLikeVO);
	//게시글 추천 삭제
	@Delete("DELETE FROM freeboard_like_comment WHERE comment_num = #{comment_num}")
	public void delete_like(Integer comment_num);

}
