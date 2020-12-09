package kr.spring.board.infoboard.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import kr.spring.board.infoboard.vo.InfoCommentLikeVO;

public interface InfoCommentLikeMapper {
	@Select("SELECT COUNT(*) FROM infoboard_like_comment f JOIN member_detail d ON f.mem_num=d.mem_num WHERE comment_num=#{comment_num}")
	public int selectCountLike(int comment_num);
	@Insert("INSERT INTO infoboard_like_comment (like_num, comment_num, mem_num) VALUES (like_num_SEQ.nextval, #{comment_num}, #{mem_num})")
	public void insertLike(InfoCommentLikeVO infoCommentLikeVO);
	@Select("SELECT * FROM infoboard_like_comment WHERE mem_num=#{mem_num} AND comment_num=#{comment_num}")
	public InfoCommentLikeVO selectCheckLike(InfoCommentLikeVO infoCommentLikeVO);
	@Delete("DELETE FROM infoboard_like_comment WHERE comment_num=#{comment_num}")
	public void deleteLikeByPostNum(Integer post_num);
}
