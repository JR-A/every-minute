package kr.spring.board.customboard.service;

import java.util.Map;

import kr.spring.board.customboard.vo.CustomLikeVO;

public interface CustomLikeService {
	//게시글 추천 수
	public int selectRowCountLike (Map<String,Object> map);
	public int likeCount_user (Map<String,Object> map);
	public int selectPostWriter(Map<String,Object> map);
	//게시글 추천 insert
	public void insertLike (CustomLikeVO customLikeVO);
	//게시글 추천 삭제
	public void delete_like(Integer post_num);
}
