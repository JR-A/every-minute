package kr.spring.board.infoboard.service;

import java.util.Map;

import kr.spring.board.infoboard.vo.InfoBoardVO;
import kr.spring.board.infoboard.vo.InfoLikeVO;

public interface InfoLikeService {
	public InfoBoardVO info_bestLikePost();
	public int selectCountLike(int post_num);
	public void insertLike(InfoLikeVO infoLikeVO);

	//게시글 추천 수
	public int selectRowCountLike (Map<String,Object> map);
	public int selectRowCountLikeByMem_num (Map<String,Object> map);
	public int selectSameMember(Map<String,Object> map);
}
