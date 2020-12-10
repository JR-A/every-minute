package kr.spring.board.infoboard.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import kr.spring.board.infoboard.vo.InfoBoardVO;
import kr.spring.board.infoboard.vo.InfoLikeVO;

public interface InfoLikeMapper {
	public InfoBoardVO info_bestLikePost();
	@Select("SELECT COUNT(*) FROM infoboard_like_post f JOIN member_detail d ON f.mem_num=d.mem_num WHERE post_num=#{post_num}")
	public int selectCountLike(Integer post_num);
	@Insert("INSERT INTO infoboard_like_post (like_num, post_num, mem_num) VALUES (info_like_post_SEQ.nextval, #{post_num}, #{mem_num})")
	public void insertLike(InfoLikeVO boardLike);
	@Select("SELECT * FROM infoboard_like_post WHERE mem_num=#{mem_num} AND post_num=#{post_num}")
	public InfoLikeVO selectCheckLike(InfoLikeVO infoLikeVO);
	@Delete("DELETE FROM infoboard_like_post WHERE post_num=#{post_num}")
	public void deleteLikeByPostNum(Integer post_num);
}
