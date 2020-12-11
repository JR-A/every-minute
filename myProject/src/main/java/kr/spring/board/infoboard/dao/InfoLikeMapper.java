package kr.spring.board.infoboard.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import kr.spring.board.infoboard.vo.InfoBoardVO;
import kr.spring.board.infoboard.vo.InfoLikeVO;

public interface InfoLikeMapper {
	public InfoBoardVO info_bestLikePost();
	
	//게시글 추천 수
	@Select("SELECT COUNT(*) FROM infoboard_like_post WHERE post_num = #{post_num}")
	public int selectRowCountLike (Map<String,Object> map);
	@Select("SELECT COUNT(*) FROM infoboard_like_post WHERE post_num = #{post_num} AND mem_num = #{mem_num}")
	public int selectRowCountLikeByMem_num (Map<String,Object> map);
	@Select("SELECT COUNT(*) FROM infoboard_like_post l JOIN infoboard f ON l.mem_num=f.mem_num WHERE f.mem_num=#{mem_num} and f.post_num=#{post_num}")
	public int selectSameMember(Map<String,Object> map);
	@Select("SELECT COUNT(*) FROM infoboard_like_post f JOIN member_detail d ON f.mem_num=d.mem_num WHERE post_num=#{post_num}")
	public int selectCountLike(Integer post_num);
	//게시글 추천 insert
	@Insert("INSERT INTO infoboard_like_post (like_num, post_num, mem_num) VALUES (info_like_post_SEQ.nextval, #{post_num}, #{mem_num})")
	public void insertLike(InfoLikeVO boardLike);
	//게시글 추천 삭제
	@Delete("DELETE FROM infoboard_like_post WHERE post_num=#{post_num}")
	public void deleteLikeByPostNum(Integer post_num);
}
