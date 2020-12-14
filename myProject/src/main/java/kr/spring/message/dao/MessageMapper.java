package kr.spring.message.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.message.vo.MessageVO;

public interface MessageMapper {
	public List<MessageVO> selectList(Map<String, Object> map);
	public int selectRowCount(Map<String, Object> map);
	public List<MessageVO> selectReplyList(Integer msg_num);
	public List<MessageVO> selectSendList(Map<String, Object> map);
	public int selectSendRowCount(Map<String, Object> map);
	@Insert("INSERT INTO message_post (msg_num, mem_num, target_mem_num, content,parent_msg_num,anonymous) "
			+ "VALUES(MESSAGEPOST_SEQ.nextval, #{mem_num}, #{target_mem_num}, #{content}, #{parent_msg_num}, #{anonymous})")
	public void insertBoard(MessageVO message);
	@Select("SELECT * FROM message_post b JOIN member m ON b.mem_num=m.mem_num WHERE b.msg_num=#{msg_num}")
	public MessageVO selectMessage(Integer msg_num);
	@Delete("DELETE FROM message_post WHERE msg_num = #{msg_num}")
	public void deleteBoard(Integer msg_num);
	@Update("UPDATE message_post SET msg_check=1 WHERE msg_num=#{msg_num}")
	public void updateMsg_check(Integer msg_num);
}
