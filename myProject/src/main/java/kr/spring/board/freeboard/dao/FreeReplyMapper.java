package kr.spring.board.freeboard.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.board.freeboard.vo.FreeReplyVO;

public interface FreeReplyMapper {
	//댓글
		public List<FreeReplyVO> selectListReply(Map<String,Object> map);
		@Select("SELECT COUNT(*) FROM freeboard_comment f JOIN member_detail d ON f.mem_num=d.mem_num WHERE post_num=#{post_num}")
		public int selectRowCountReply(Map<String,Object> map);
		@Insert("INSERT INTO freeboard_comment (comment_num,content,post_num,mem_num,anonymous) VALUES (FREE_REPLY_SEQ.nextval,#{content},#{post_num},#{mem_num},#{anonymous})")
		public void insertReply(FreeReplyVO boardReply);
		@Update("UPDATE freeboard_comment SET content=#{content},reg_date=SYSDATE WHERE comment_num=#{comment_num}")
		public void updateReply(FreeReplyVO boardReply);
		@Delete("DELETE FROM freeboard_comment WHERE comment_num=#{comment_num}")
		public void deleteReply(Integer re_num);
		//부모글 삭제시 댓글이 존재하면 부모글 삭제전 댓글 삭제
		@Delete("DELETE FROM freeboard_comment WHERE post_num=#{post_num}")
		public void deleteReplyByPostNum(Integer post_num);
		public void countReply(Map<String,Object> map);
}