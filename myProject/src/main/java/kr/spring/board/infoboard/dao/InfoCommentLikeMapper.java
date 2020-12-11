package kr.spring.board.infoboard.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import kr.spring.board.infoboard.vo.InfoCommentLikeVO;

public interface InfoCommentLikeMapper {
	@Select("SELECT COUNT(*) FROM infoboard_like_comment WHERE comment_num = #{comment_num}")
	public int selectRowCountLike_R (Map<String,Object> map);
	@Select("SELECT COUNT(*) FROM infoboard_like_comment WHERE comment_num = #{comment_num} AND mem_num = #{mem_num}")
	public int selectRowCountLike_RByMem_num (Map<String,Object> map);
	@Select("SELECT COUNT(*) FROM infoboard_like_comment l JOIN infoboard_comment f ON l.mem_num=f.mem_num WHERE f.mem_num=#{mem_num} and f.comment_num=#{comment_num}")
	public int selectSameMember_R(Map<String,Object> map);
	//게시글 추천 insert
	@Insert ("INSERT INTO infoboard_like_comment (like_num,comment_num,mem_num) VALUES(info_like_comment_SEQ.NEXTVAL,#{comment_num},#{mem_num})")
	public void insertLike_R (InfoCommentLikeVO infoCommentLikeVO);
	//댓글 추천 삭제_게시글 (게시글에 존재하는 모든 댓글 삭제)
	@Delete("DELETE FROM infoboard_like_comment WHERE comment_num = #{comment_num}")
	public void deleteReplyLike(Integer comment_num);
		
} 
