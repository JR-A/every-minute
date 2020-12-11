package kr.spring.board.infoboard.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.board.infoboard.dao.InfoLikeMapper;
import kr.spring.board.infoboard.vo.InfoBoardVO;
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
	public InfoBoardVO info_bestLikePost() {
		return infoLikeMapper.info_bestLikePost();
	}

	@Override
	public int selectRowCountLike(Map<String, Object> map) {
		return infoLikeMapper.selectRowCountLike(map);
	}

	@Override
	public int selectRowCountLikeByMem_num(Map<String, Object> map) {
		return infoLikeMapper.selectRowCountLikeByMem_num(map);
	}

	@Override
	public int selectSameMember(Map<String, Object> map) {
		return infoLikeMapper.selectSameMember(map);
	}



}
