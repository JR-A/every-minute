package kr.spring.board.infoboard.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import kr.spring.board.infoboard.vo.InfoBlameVO;

public interface InfoBlameMapper {
	//댓글 중복 신고여부 확인 - 댓글 신고 테이블에 해당 회원 번호,해당 댓글이 있는지
	@Select("SELECT COUNT(*) FROM infoboard_blame_comment WHERE comment_num = #{comment_num} AND mem_num = #{mem_num}")
	public int blameCommCount_user (Map<String,Object> map);
	//댓글 신고 접수
	@Insert ("INSERT INTO infoboard_blame_comment (blame_num, comment_num, mem_num) VALUES(info_blame_comment_seq.NEXTVAL, #{comment_num}, #{mem_num})")
	public void insertCommBlame(InfoBlameVO infoBlameVO);
	
	//신고하기 삭제
	@Delete("DELETE FROM infoboard_blame_post WHERE post_num=#{post_num}")
	public void deleteBlamePostByPostNum(Integer post_num);
	
	//댓글 신고하기 삭제
	@Delete("DELETE FROM infoboard_blame_comment WHERE comment_num=#{comment_num}")
	public void deleteBlamePostByCommentNum(Integer comment_num);
}
 