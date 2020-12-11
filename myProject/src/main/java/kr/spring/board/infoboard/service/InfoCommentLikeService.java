package kr.spring.board.infoboard.service;

import java.util.Map;

import kr.spring.board.infoboard.vo.InfoCommentLikeVO;

public interface InfoCommentLikeService {
	//게시글 추천 수
	public int selectRowCountLike_R (Map<String,Object> map);
	public int selectRowCountLike_RByMem_num (Map<String,Object> map);
	public int selectSameMember_R(Map<String,Object> map);
	//게시글 추천 insert
	public void insertLike_R (InfoCommentLikeVO infoCommentLikeVO);
	
}
