package kr.spring.board.customboard.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import kr.spring.board.customboard.vo.CustomFavoriteVO;

public interface CustomFavoriteMapper {

	//게시글 즐겨찾기 수
	@Select("SELECT COUNT(*) FROM customboard_favorite WHERE post_num = #{post_num}")
	public int selectRowCountFav (Map<String,Object> map);
	//중복 즐겨찾기 여부 확인
	@Select("SELECT COUNT(*) FROM customboard_favorite WHERE post_num = #{post_num} AND mem_num = #{mem_num}")
	public int favoriteCount_user (Map<String,Object> map);
	//게시글 즐겨찾기 추가
	@Insert("INSERT INTO customboard_favorite(fav_num, post_num, mem_num) VALUES(custom_favorite_seq.NEXTVAL, #{post_num}, #{mem_num})")
	public void insertFavorite (CustomFavoriteVO customfavoriteVO);
	
	//즐겨찾기 삭제_게시글 (게시글에 존재하는 모든 즐겨찾기 삭제)
	@Delete("DELETE FROM customboard_favorite WHERE post_num=#{post_num}")
	public void deleteFavorite (int post_num);
	//즐겨찾기 삭제_회원 (회원이 게시글에 추가한 즐겨찾기만 삭제)
	@Delete("DELETE FROM customboard_favorite WHERE post_num=#{post_num} AND mem_num=#{mem_num}")
	public void deleteFavorite_mem (@Param("post_num")int post_num, @Param("mem_num")int mem_num);
	
}
