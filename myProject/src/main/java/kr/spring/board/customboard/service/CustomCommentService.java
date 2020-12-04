package kr.spring.board.customboard.service;

import java.util.List;
import java.util.Map;

import kr.spring.board.customboard.vo.CustomCommentVO;

public interface CustomCommentService {

	public List<CustomCommentVO> selectListComment(Map<String,Object> map);
	public int selectRowCountComment(Map<String,Object> map);
	public void insertComment(CustomCommentVO customCommentVO);
	public void updateComment(CustomCommentVO customCommentVO);
	public void deleteComment(Integer comment_num);
	public void countComment(Map<String,Object> map);
	
}
