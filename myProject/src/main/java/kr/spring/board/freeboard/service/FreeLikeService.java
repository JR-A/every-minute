package kr.spring.board.freeboard.service;

import java.util.Map;

import kr.spring.board.freeboard.vo.FreeBoardVO;
import kr.spring.board.freeboard.vo.FreeLikeVO;

public interface FreeLikeService {
	
	//가장 많은 추천 수를 가진 게시글
	public FreeBoardVO free_bestLikePost();
	
	//게시글 추천 수
	public int selectRowCountLike (Map<String,Object> map);
	public int selectRowCountLikeByMem_num (Map<String,Object> map);
	public int selectSameMember(Map<String,Object> map);
	//게시글 추천 insert
	public void insertLike (FreeLikeVO freeLikeVO);

}