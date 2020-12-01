/*package kr.spring.board.freeboard.service;

import java.util.HashMap;
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
	public int count_check(Map<String, Object> map) {
		return freeLikeMapper.count_check(map);
	}

	@Override
	public void count_update(Map<String, Object> map) {
		freeLikeMapper.count_update(map);
		
	}

	@Override
	public void delete_like(Integer like_num) {
		freeLikeMapper.delete_like(like_num);
		
	}

	@Override
	public int select_count(Map<String, Object> map) {
		return freeLikeMapper.select_count(map);
	}
	
	

}*/