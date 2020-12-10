package kr.spring.board.customboard.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.board.customboard.vo.CustomCommentVO;

public interface CustomCommentMapper {
	//댓글 목록
	public List<CustomCommentVO> selectListComment(Map<String,Object> map);
	//댓글 개수
	@Select("SELECT COUNT(*) FROM customboard_comment f JOIN member m ON f.mem_num=m.mem_num WHERE post_num=#{post_num}")
	public int selectRowCountComment(Integer post_num);
	//댓글 입력
	@Insert("INSERT INTO customboard_comment (comment_num,content,post_num,mem_num,anonymous) VALUES (CUSTOMBOARD_COMMENT_SEQ.nextval,#{content},#{post_num},#{mem_num},#{anonymous})")
	public void insertComment(CustomCommentVO customCommentVO);
	//댓글 수정
	@Update("UPDATE customboard_comment SET content=#{content},reg_date=SYSDATE WHERE comment_num=#{comment_num}")
	public void updateComment(CustomCommentVO customCommentVO);
	//댓글 삭제
	@Delete("DELETE FROM customboard_comment WHERE comment_num=#{comment_num}")
	public void deleteComment(Integer comment_num);
	//게시글 삭제시 댓글이 존재하면 게시글에 속한 댓글 삭제
	@Delete("DELETE FROM customboard_comment WHERE post_num=#{post_num}")
	public void deleteCommentByPostNum(Integer post_num);
}
