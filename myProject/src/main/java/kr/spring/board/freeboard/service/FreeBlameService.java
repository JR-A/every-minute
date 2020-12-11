package kr.spring.board.freeboard.service;

import java.util.Map;

import kr.spring.board.freeboard.vo.FreeBlameVO;

public interface FreeBlameService {
		//중복 신고여부 확인
		public int blameCount_user(Map<String,Object> map);
		//게시글 신고 접수
		public void insertPostBlame(FreeBlameVO freeBlameVO);
		//댓글 중복 신고여부 확인
		public int blameCommCount_user (Map<String,Object> map);
		//댓글 신고 접수
		public void insertCommentBlame(FreeBlameVO freeBlameVO);
}
