package kr.spring.board.customboard.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.board.customboard.dao.CustomFavoriteMapper;
import kr.spring.board.customboard.vo.CustomFavoriteVO;

@Service("customFavoriteService")
public class CustomFavoriteServiceImpl implements CustomFavoriteService{

	@Override
	public int selectRowCountFav(Map<String, Object> map) {
		return customFavoriteMapper.selectRowCountFav(map);
	}
	
	@Resource
	CustomFavoriteMapper customFavoriteMapper;

	@Override
	public int favoriteCount_user(Map<String, Object> map) {
		return customFavoriteMapper.favoriteCount_user(map);
	}

	@Override
	public void insertFavorite(CustomFavoriteVO customfavoriteVO) {
		customFavoriteMapper.insertFavorite(customfavoriteVO);
	}

	@Override
	public void deleteFavorite(int post_num) {
		customFavoriteMapper.deleteFavorite(post_num);
	}

	@Override
	public void deleteFavorite_mem(int post_num, int mem_num) {
		customFavoriteMapper.deleteFavorite_mem(post_num, mem_num);
	}

	
}
