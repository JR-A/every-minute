package kr.spring.board.customboard.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.board.customboard.dao.CustomCommentMapper;
import kr.spring.board.customboard.vo.CustomCommentVO;

@Service("customCommentService")
public class CustomCommentServiceImpl implements CustomCommentService{

	@Resource
	CustomCommentMapper customCommentMapper;
	
	@Override
	public List<CustomCommentVO> selectListComment(Map<String, Object> map) {
		return customCommentMapper.selectListComment(map);
	}

	@Override
	public int selectRowCountComment(Integer post_num) {
		return customCommentMapper.selectRowCountComment(post_num);
	}

	@Override
	public void insertComment(CustomCommentVO customCommentVO) {
		customCommentMapper.insertComment(customCommentVO);
	}

	@Override
	public void updateComment(CustomCommentVO customCommentVO) {
		customCommentMapper.updateComment(customCommentVO);
	}

	@Override
	public void deleteComment(Integer comment_num) {
		customCommentMapper.deleteComment(comment_num);
	}

}
