package kr.spring.board.infoboard.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.board.infoboard.dao.InfoLikeMapper;
import kr.spring.board.infoboard.vo.InfoLikeVO;

@Service("infoLikeService")
public class InfoLikeServiceImpl implements InfoLikeService{

	@Resource
	InfoLikeMapper infoLikeMapper;
	
	@Override
	public int selectCountLike(int post_num) {
		return infoLikeMapper.selectCountLike(post_num);
	}

	@Override
	public void insertLike(InfoLikeVO infoLikeVO) {
		infoLikeMapper.insertLike(infoLikeVO);
		
	}

	@Override
	public InfoLikeVO selectCheckLike(InfoLikeVO infoLikeVO) {
		return infoLikeMapper.selectCheckLike(infoLikeVO);
	}



}
