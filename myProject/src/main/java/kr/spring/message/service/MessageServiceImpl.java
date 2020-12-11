package kr.spring.message.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.message.dao.MessageMapper;
import kr.spring.message.vo.MessageVO;

@Service("messageService")
public class MessageServiceImpl implements MessageService {

	@Resource
	MessageMapper messageMapper;
	
	@Override
	public List<MessageVO> selectList(Map<String, Object> map) {
		return messageMapper.selectList(map);
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		return messageMapper.selectRowCount(map);
	}

	@Override
	public void insertBoard(MessageVO message) {
		messageMapper.insertBoard(message);
	}

	@Override
	public void deleteBoard(Integer msg_num) {
		messageMapper.deleteBoard(msg_num);
	}

	@Override
	public MessageVO selectMessage(int msg_num) {
		return messageMapper.selectMessage(msg_num);
	}

	@Override
	public void updateMsg_check(Integer msg_num) {
		messageMapper.updateMsg_check(msg_num);
	}

	@Override
	public List<MessageVO> selectReplyList(Integer msg_num) {
		return messageMapper.selectReplyList(msg_num);
	}

	@Override
	public List<MessageVO> selectSendList(Map<String, Object> map) {
		return messageMapper.selectSendList(map);
	}

	@Override
	public int selectSendRowCount(Map<String, Object> map) {
		return messageMapper.selectSendRowCount(map);
	}
}
