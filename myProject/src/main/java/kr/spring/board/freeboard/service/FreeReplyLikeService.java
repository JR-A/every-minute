package kr.spring.board.freeboard.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import kr.spring.board.freeboard.vo.FreeReplyLikeVO;

public interface FreeReplyLikeService {
	//게시글 추천 수
			public int selectRowCountLike_R (Map<String,Object> map);
			public int selectRowCountLike_RByMem_num (Map<String,Object> map);
			public int selectSameMember_R(Map<String,Object> map);
			//게시글 추천 insert
			public void insertLike_R (FreeReplyLikeVO FreeReplyLikeVO);
}
