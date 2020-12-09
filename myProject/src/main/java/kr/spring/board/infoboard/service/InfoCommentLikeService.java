package kr.spring.board.infoboard.service;

import kr.spring.board.infoboard.vo.InfoCommentLikeVO;

public interface InfoCommentLikeService {
	public int selectCountLike(int comment_num);
	public void insertLike(InfoCommentLikeVO infoCommentLikeVO);
	public InfoCommentLikeVO selectCheckLike(InfoCommentLikeVO infoCommentLikeVO);
	
}
