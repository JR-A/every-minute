package kr.spring.board.customboard.service;

import java.util.Map;

import kr.spring.board.customboard.vo.CustomFavoriteVO;

public interface CustomFavoriteService {
	//게시글 즐겨찾기 수
	public int selectRowCountFav (Map<String,Object> map);
	//중복 즐겨찾기 여부 확인
	public int favoriteCount_user (Map<String,Object> map);
	//게시글 즐겨찾기 추가
	public void insertFavorite (CustomFavoriteVO customfavoriteVO);
	//즐겨찾기 삭제
	public void deleteFavorite (int post_num);
	
}
