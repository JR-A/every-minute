package kr.spring.board.freeboard.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.board.freeboard.dao.FreeReplyLikeMapper;
import kr.spring.board.freeboard.vo.FreeReplyLikeVO;
@Service("freeReplyLikeService")
public class FreeReplyLikeServiceImpl implements FreeReplyLikeService {
	@Resource
	FreeReplyLikeMapper freeReplyLikeMapper;
	@Override
	public int selectRowCountLike_R(Map<String, Object> map) {
		return freeReplyLikeMapper.selectRowCountLike_R(map);
	}

	@Override
	public int selectRowCountLike_RByMem_num(Map<String, Object> map) {
		return freeReplyLikeMapper.selectRowCountLike_RByMem_num(map);
	}

	@Override
	public int selectSameMember_R(Map<String, Object> map) {
		return freeReplyLikeMapper.selectSameMember_R(map);
	}

	@Override
	public void insertLike_R(FreeReplyLikeVO FreeReplyLikeVO) {
		freeReplyLikeMapper.insertLike_R(FreeReplyLikeVO);
	}


}
