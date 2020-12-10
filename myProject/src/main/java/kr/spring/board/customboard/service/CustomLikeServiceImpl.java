package kr.spring.board.customboard.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.board.customboard.dao.CustomLikeMapper;
import kr.spring.board.customboard.vo.CustomLikeVO;
import kr.spring.board.customboard.vo.CustomPostVO;

@Service("customLikeService")
public class CustomLikeServiceImpl implements CustomLikeService{

	@Resource
	CustomLikeMapper customLikeMapper;
	
	@Override
	public void insertPostLike(CustomLikeVO customLikeVO) {
		customLikeMapper.insertPostLike(customLikeVO);
	}

	@Override
	public int selectRowCount_postLike(Map<String, Object> map) {
		return customLikeMapper.selectRowCount_postLike(map);
	}

	@Override
	public int likePostCount_user(Map<String, Object> map) {
		return customLikeMapper.likePostCount_user(map);
	}

	@Override
	public int selectPostWriter(Map<String, Object> map) {
		return customLikeMapper.selectPostWriter(map);
	}

	@Override
	public void deletePostLike(Integer post_num) {
		customLikeMapper.deletePostLike(post_num);
	}

	@Override
	public int selectRowCount_commLike(Map<String, Object> map) {
		return customLikeMapper.selectRowCount_commLike(map);
	}

	@Override
	public int likeCommCount_user(Map<String, Object> map) {
		return customLikeMapper.likeCommCount_user(map);
	}

	@Override
	public int selectCommWriter(Map<String, Object> map) {
		return customLikeMapper.selectCommWriter(map);
	}

	@Override
	public void insertCommLike(CustomLikeVO customLikeVO) {
		customLikeMapper.insertCommLike(customLikeVO);
	}

	@Override
	public void deleteCommLike(Integer comment_num) {
		customLikeMapper.deleteCommLike(comment_num);
	}

	@Override
	public CustomPostVO custom_bestLikePost() {
		return customLikeMapper.custom_bestLikePost();
	}
	
	
}
