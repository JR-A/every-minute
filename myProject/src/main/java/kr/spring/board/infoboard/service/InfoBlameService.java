package kr.spring.board.infoboard.service;

import java.util.Map;

import kr.spring.board.infoboard.vo.InfoBlameVO;

public interface InfoBlameService {
	//중복 신고여부 확인
	public int blameCount_user(Map<String,Object> map);
	//댓글 중복 신고여부 확인
	public int blameCommCount_user (Map<String,Object> map);
	//댓글 신고 접수
	public void insertCommentBlame(InfoBlameVO infoBlameVO);
}
 