package kr.spring.board.infoboard.service;

import kr.spring.board.infoboard.vo.InfoBoardVO;
import kr.spring.board.infoboard.vo.InfoLikeVO;

public interface InfoLikeService {
	public InfoBoardVO info_bestLikePost();
	public int selectCountLike(int post_num);
	public void insertLike(InfoLikeVO infoLikeVO);
	public InfoLikeVO selectCheckLike(InfoLikeVO infoLikeVO);
}
