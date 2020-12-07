package kr.spring.board.freeboard.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.board.freeboard.dao.FreeLikeMapper;
import kr.spring.board.freeboard.vo.FreeBoardVO;
import kr.spring.board.freeboard.vo.FreeLikeVO;

@Service("freeLikeService")
public class FreeLikeServiceImpl implements FreeLikeService {

	@Resource
	FreeLikeMapper freeLikeMapper;

	@Override
	public void insertLike(FreeLikeVO freeLikeVO) {
		freeLikeMapper.insertLike(freeLikeVO);
		
	}

	@Override
	public void count_update(FreeLikeVO freeLikeVO) {
		freeLikeMapper.count_update(freeLikeVO);
		
	}

	@Override
	public int selectRowCountLike(Map<String, Object> map) {
		return freeLikeMapper.selectRowCountLike(map);
	}

	@Override
	public int selectRowCountLikeByMem_num(Map<String, Object> map) {
		return freeLikeMapper.selectRowCountLikeByMem_num(map);
	}

}