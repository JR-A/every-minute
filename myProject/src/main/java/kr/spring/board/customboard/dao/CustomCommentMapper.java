package kr.spring.board.customboard.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.board.customboard.vo.CustomCommentVO;

public interface CustomCommentMapper {
	//댓글
	public List<CustomCommentVO> selectListComment(Map<String,Object> map);
	@Select("SELECT COUNT(*) FROM customboard_comment f JOIN member_detail d ON f.mem_num=d.mem_num WHERE post_num=#{post_num}")
	public int selectRowCountComment(Map<String,Object> map);
	@Insert("INSERT INTO customboard_comment (comment_num,content,post_num,mem_num,anonymous) VALUES (CUSTOMBOARD_COMMENT_SEQ.nextval,#{content},#{post_num},#{mem_num},#{anonymous})")
	public void insertComment(CustomCommentVO customCommentVO);
	@Update("UPDATE customboard_comment SET content=#{content},reg_date=SYSDATE WHERE comment_num=#{comment_num}")
	public void updateComment(CustomCommentVO customCommentVO);
	@Delete("DELETE FROM customboard_comment WHERE comment_num=#{comment_num}")
	public void deleteComment(Integer comment_num);
	//부모글 삭제시 댓글이 존재하면 부모글 삭제전 댓글 삭제
	@Delete("DELETE FROM customboard_comment WHERE post_num=#{post_num}")
	public void deleteCommentByPostNum(Integer post_num);
	public void countComment(Map<String,Object> map);
}
