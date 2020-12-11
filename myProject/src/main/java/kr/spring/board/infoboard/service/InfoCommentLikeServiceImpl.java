package kr.spring.board.infoboard.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.board.infoboard.dao.InfoCommentLikeMapper;
import kr.spring.board.infoboard.vo.InfoCommentLikeVO;

@Service("infoCommentLikeService")
public class InfoCommentLikeServiceImpl implements InfoCommentLikeService {

	@Resource
	InfoCommentLikeMapper infoCommentLikeMapper;

	@Override
	public int selectRowCountLike_R(Map<String, Object> map) {
		return infoCommentLikeMapper.selectRowCountLike_R(map);
	}

	@Override
	public int selectRowCountLike_RByMem_num(Map<String, Object> map) {
		return infoCommentLikeMapper.selectRowCountLike_RByMem_num(map);
	}

	@Override
	public int selectSameMember_R(Map<String, Object> map) {
		return infoCommentLikeMapper.selectSameMember_R(map);
	}

	@Override
	public void insertLike_R(InfoCommentLikeVO infoCommentLikeVO) {
		infoCommentLikeMapper.insertLike_R(infoCommentLikeVO);
	}


}
