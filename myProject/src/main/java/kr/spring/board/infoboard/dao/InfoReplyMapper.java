package kr.spring.board.infoboard.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.board.infoboard.vo.InfoReplyVO;

public interface InfoReplyMapper {
	//댓글
		public List<InfoReplyVO> selectListReply(Map<String,Object> map);
		@Select("SELECT COUNT(*) FROM infoboard_comment f JOIN member_detail d ON f.mem_num=d.mem_num WHERE post_num=#{post_num}")
		public int selectRowCountReply(Map<String,Object> map);
		@Insert("INSERT INTO infoboard_comment (comment_num,content,post_num,mem_num,anonymous) VALUES (info_comment_seq.nextval,#{content},#{post_num},#{mem_num},#{anonymous})")
		public void insertReply(InfoReplyVO boardReply);
		@Update("UPDATE infoboard_comment SET content=#{content} WHERE comment_num=#{comment_num}")
		public void updateReply(InfoReplyVO boardReply);
		@Delete("DELETE FROM infoboard_comment WHERE comment_num=#{comment_num}")
		public void deleteReply(Integer re_num);
		//부모글 삭제시 댓글이 존재하면 부모글 삭제전 댓글 삭제
		@Delete("DELETE FROM infoboard_comment WHERE post_num=#{post_num}")
		public void deleteReplyByPostNum(Integer post_num);
}