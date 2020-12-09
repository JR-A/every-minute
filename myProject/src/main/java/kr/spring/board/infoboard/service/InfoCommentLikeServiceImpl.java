package kr.spring.board.infoboard.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.board.infoboard.dao.InfoCommentLikeMapper;
import kr.spring.board.infoboard.vo.InfoCommentLikeVO;

@Service("infoCommentLikeService")
public class InfoCommentLikeServiceImpl implements InfoCommentLikeService {

	@Resource
	InfoCommentLikeMapper infoCommentLikeMapper;
	
	//댓글의 추천수는 ajax를 사용해서 다이렉트로 추천수를 다이렉트로 보여줄 수 없음
	//댓글목록 ajax에서 보여주기 위해 InfoReplayController listReply.do list(comment_num)를 받아와서 작업함.
	
	@Override
	public int selectCountLike(int comment_num) {
		return infoCommentLikeMapper.selectCountLike(comment_num);
	}
	
	@Override
	public void insertLike(InfoCommentLikeVO infoCommentLikeVO) {
		infoCommentLikeMapper.insertLike(infoCommentLikeVO);
	}

	@Override
	public InfoCommentLikeVO selectCheckLike(InfoCommentLikeVO infoCommentLikeVO) {
		return infoCommentLikeMapper.selectCheckLike(infoCommentLikeVO);
	}

}
