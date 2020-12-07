package kr.spring.board.freeboard.service;

import java.util.List;
import java.util.Map;

import kr.spring.board.freeboard.vo.FreeLikeVO;

public interface FreeLikeService {
	//게시글 추천 수
		public int selectRowCountLike (Map<String,Object> map);
		public int selectRowCountLikeByMem_num (Map<String,Object> map);
		//게시글 추천 insert
		public void insertLike (FreeLikeVO freeLikeVO);
		//게시글 추천 추가
		public void count_update(FreeLikeVO freeLikeVO);

}