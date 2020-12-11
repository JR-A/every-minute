package kr.spring.board.customboard.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.board.customboard.dao.CustomBlameMapper;
import kr.spring.board.customboard.dao.CustomFavoriteMapper;
import kr.spring.board.customboard.dao.CustomLikeMapper;
import kr.spring.board.customboard.dao.CustomPostMapper;
import kr.spring.board.customboard.vo.CustomPostVO;

@Service("customPostService")
public class CustomPostServiceImpl implements CustomPostService {

	@Resource
	CustomPostMapper customPostMapper;	
	@Resource
	CustomBlameMapper customBlameMapper;	
	@Resource
	CustomLikeMapper customLikeMapper;	
	@Resource
	CustomFavoriteMapper customFavoriteMapper;	
	/*@Resource
	CustomMessageMapper CustommessageMapper;*/	

	//최근 게시글 top3 목록
	@Override
	public List<CustomPostVO> selectTop3PostList() {
		return customPostMapper.selectTop3PostList();
	}
	//추천 10개 이상인 게시글 top2 목록
	@Override
	public List<CustomPostVO> custom_hotPostTop2() {
		return customPostMapper.custom_hotPostTop2();
	}
	//게시글 목록
	@Override 
	public List<CustomPostVO> selectPostList(Map<String, Object> map) {
		return customPostMapper.selectPostList(map);
	}
	//페이징처리를 위한 글 count
	@Override
	public int selectRowCount(Integer board_num) {
		return customPostMapper.selectRowCount(board_num);
	}
	//자바빈 얻기
	@Override
	public CustomPostVO selectCustomPost(Integer post_num) {
		return customPostMapper.selectCustomPost(post_num);
	}
	//게시글 삭제_댓글 없음
	@Override
	public void deletePost(Integer post_num) {
		customLikeMapper.deletePostLike(post_num);
		customFavoriteMapper.deleteFavorite(post_num);
		customBlameMapper.deletePostBlame(post_num);
		//custmoMessageMapper.deletePostMessage(post_num);
		customPostMapper.deletePost(post_num);
	}
	//게시글 작성
	@Override
	public void insertPost(CustomPostVO postVO) {
		customPostMapper.insertPost(postVO);
	}
	//게시글 수정
	@Override
	public void customPostUpdate(CustomPostVO postVO) {
		customPostMapper.customPostUpdate(postVO);
	}
	//게시판에 달린 게시글 번호 
	@Override
	public List<Integer> selectPostNum(int board_num) {
		return customPostMapper.selectPostNum(board_num);
	}
	
}
