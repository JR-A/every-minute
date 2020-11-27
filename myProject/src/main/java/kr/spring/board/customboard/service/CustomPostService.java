package kr.spring.board.customboard.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import kr.spring.board.customboard.vo.CustomBoardVO;
import kr.spring.board.customboard.vo.CustomPostVO;

public interface CustomPostService {
	
	//게시판 정보
	public CustomBoardVO selectBoardInfo(int board_num);
	
	//게시글 목록
	public List<CustomPostVO> selectPostList(Map<String, Object> map); 

	//페이징처리를 위한 글 count
	public int selectRowCount(Map<String, Object> map);

	//자바빈 얻기
	public CustomPostVO selectCustomPost(Integer post_num);

	//게시글 삭제
	public void deletePost(Integer post_num);
	
	//작성자 아이디 얻기
	public String selectPostWriter(Integer post_num);

	//게시글 작성
	public void insertPost(CustomPostVO postVO);
}
