package kr.spring.board.customboard.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import kr.spring.board.customboard.vo.CustomBlameVO;


public interface CustomBlameMapper {

	//중복 신고여부 확인
	@Select("SELECT COUNT(*) FROM customboard_blame_post WHERE post_num = #{post_num} AND mem_num = #{mem_num}")
	public int blameCount_user (Map<String,Object> map);
	//게시글 신고 접수
	@Insert ("INSERT INTO customboard_blame_post (blame_num, post_num, mem_num) VALUES(custom_blame_post_seq.NEXTVAL, #{post_num}, #{mem_num})")
	public void insertBlame (CustomBlameVO customBlameVO);
	//댓글 신고 접수
	@Insert ("INSERT INTO customboard_blame_comment (blame_num, comment_num, mem_num) VALUES(custom_blame_comment_seq.NEXTVAL, #{comment_num}, #{mem_num})")
	public void insertCommentBlame(CustomBlameVO customBlameVO);
	
}
