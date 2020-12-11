package kr.spring.board.customboard.service;

import java.util.Map;

import kr.spring.board.customboard.vo.CustomLikeVO;
import kr.spring.board.customboard.vo.CustomPostVO;

public interface CustomLikeService {
	
	//가장 많은 추천 수를 가진 게시글
	public CustomPostVO custom_bestLikePost();
	
	//게시글 추천 수
	public int selectRowCount_postLike (Map<String,Object> map);
	//게시글 중복 추천여부 확인
	public int likePostCount_user (Map<String,Object> map);
	//게시글 작성자인지 확인
	public int selectPostWriter(Map<String,Object> map);
	//게시글 추천 등록
	public void insertPostLike (CustomLikeVO customLikeVO);
	
	//게시글 추천 삭제_게시글 (게시글에 존재하는 모든 즐겨찾기 삭제)
	public void deletePostLike(Integer post_num);
	//게시글 추천 삭제_회원 (회원이 게시글에 추가한 즐겨찾기만 삭제)
	public void deletePostLike_mem(int post_num, int mem_num);
	
	//댓글 추천 수
	public int selectRowCount_commLike (Map<String,Object> map);
	//댓글 중복 추천여부 확인
	public int likeCommCount_user (Map<String,Object> map);
	//댓글 작성자인지 확인
	public int selectCommWriter(Map<String,Object> map);
	//댓글 추천 등록
	public void insertCommLike (CustomLikeVO customLikeVO);
	
	//댓글 추천 삭제_게시글 (게시글에 존재하는 모든 즐겨찾기 삭제)
	public void deleteCommLike(Integer comment_num);
	//댓글 추천 삭제_게시글 _회원 (회원이 게시글에 추가한 즐겨찾기만 삭제)
	public void deleteCommLike_mem(Integer comment_num, int mem_num);
	
}
