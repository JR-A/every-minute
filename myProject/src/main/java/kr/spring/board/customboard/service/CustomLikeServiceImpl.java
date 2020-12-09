package kr.spring.board.customboard.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.board.customboard.dao.CustomLikeMapper;
import kr.spring.board.customboard.vo.CustomLikeVO;

@Service("customLikeService")
public class CustomLikeServiceImpl implements CustomLikeService{

	@Resource
	CustomLikeMapper customLikeMapper;
	
	@Override
	public void insertLike(CustomLikeVO customLikeVO) {
		customLikeMapper.insertLike(customLikeVO);
	}

	@Override
	public int selectRowCountLike(Map<String, Object> map) {
		return customLikeMapper.selectRowCountLike(map);
	}

	@Override
	public int likeCount_user(Map<String, Object> map) {
		return customLikeMapper.likeCount_user(map);
	}

	@Override
	public int selectPostWriter(Map<String, Object> map) {
		return customLikeMapper.selectPostWriter(map);
	}

	@Override
	public void delete_like(Integer post_num) {
		customLikeMapper.delete_like(post_num);
	}

}
