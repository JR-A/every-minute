package kr.spring.board.customboard.service;

import java.util.Map;

import kr.spring.board.customboard.vo.CustomBlameVO;

public interface CustomBlameService {

	//게시글 중복 신고여부 확인
	public int blamePostCount_user (Map<String,Object> map);
	//게시글 신고 접수
	public void insertPostBlame (CustomBlameVO customBlameVO);
	
	//댓글 중복 신고여부 확인
	public int blameCommCount_user (Map<String,Object> map);
	//댓글 신고 접수
	public void insertCommBlame(CustomBlameVO customBlameVO);
	
	//게시글 신고 삭제
	public void deletePostBlame(int post_num);
	//댓글 신고 삭제
	public void deleteCommBlame(int comment_num);
}
