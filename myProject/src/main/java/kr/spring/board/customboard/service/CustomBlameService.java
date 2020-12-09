package kr.spring.board.customboard.service;

import java.util.Map;

import kr.spring.board.customboard.vo.CustomBlameVO;

public interface CustomBlameService {
	//중복 신고여부 확인
	public int blameCount_user(Map<String,Object> map);
	//게시글 신고 접수
	public void insertPostBlame(CustomBlameVO customBlameVO);
	//댓글 신고 접수
	public void insertCommentBlame(CustomBlameVO customBlameVO);
}
