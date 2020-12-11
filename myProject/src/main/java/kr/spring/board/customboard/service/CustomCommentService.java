package kr.spring.board.customboard.service;

import java.util.List;
import java.util.Map;

import kr.spring.board.customboard.vo.CustomCommentVO;

public interface CustomCommentService {
	
	//댓글 목록
	public List<CustomCommentVO> selectListComment(Map<String,Object> map);
	//댓글 개수
	public int selectRowCountComment(Integer post_num);
	//댓글 입력
	public void insertComment(CustomCommentVO customCommentVO);
	//댓글 수정
	public void updateComment(CustomCommentVO customCommentVO);
	//댓글 삭제
	public void deleteComment(Integer comment_num);
	//게시글에 달린 댓글 번호
	public List<Integer> selectCommNum(int post_num);
	
}
