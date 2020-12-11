package kr.spring.message.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;


import kr.spring.message.vo.MessageVO;

public interface MessageService {
	public List<MessageVO> selectList(Map<String, Object> map);
	public int selectRowCount(Map<String, Object> map);//목록에 관련됨
	public List<MessageVO> selectReplyList(Integer msg_num);
	public List<MessageVO> selectSendList(Map<String, Object> map);
	public int selectSendRowCount(Map<String, Object> map);	
	public void insertBoard(MessageVO message);
	public void deleteBoard(Integer msg_num);
	public MessageVO selectMessage(int msg_num);
	public void updateMsg_check(Integer msg_num);
}
