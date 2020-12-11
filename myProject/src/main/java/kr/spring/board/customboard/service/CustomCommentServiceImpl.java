package kr.spring.board.customboard.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.board.customboard.dao.CustomBlameMapper;
import kr.spring.board.customboard.dao.CustomCommentMapper;
import kr.spring.board.customboard.dao.CustomLikeMapper;
import kr.spring.board.customboard.vo.CustomCommentVO;

@Service("customCommentService")
public class CustomCommentServiceImpl implements CustomCommentService{

	@Resource
	CustomCommentMapper customCommentMapper;
	@Resource
	CustomBlameMapper customBlameMapper;	
	@Resource
	CustomLikeMapper customLikeMapper;	
	/*@Resource
	CustomMessageMapper CustommessageMapper;*/	
	
	//댓글 목록
	@Override
	public List<CustomCommentVO> selectListComment(Map<String, Object> map) {
		return customCommentMapper.selectListComment(map);
	}
	//댓글 개수
	@Override
	public int selectRowCountComment(Integer post_num) {
		return customCommentMapper.selectRowCountComment(post_num);
	}
	//댓글 입력
	@Override
	public void insertComment(CustomCommentVO customCommentVO) {
		customCommentMapper.insertComment(customCommentVO);
	}
	//댓글 수정
	@Override
	public void updateComment(CustomCommentVO customCommentVO) {
		customCommentMapper.updateComment(customCommentVO);
	}
	//댓글 삭제
	@Override
	public void deleteComment(Integer comment_num) {
		customLikeMapper.deleteCommLike(comment_num);
		customBlameMapper.deleteCommBlame(comment_num);
		//customMessageMapper.deleteCommMessage(comment_num);
		customCommentMapper.deleteComment(comment_num);
	}
	//게시글에 달린 댓글 번호
	@Override
	public List<Integer> selectCommNum(int post_num) {
		return customCommentMapper.selectCommNum(post_num);
	}
	
}
