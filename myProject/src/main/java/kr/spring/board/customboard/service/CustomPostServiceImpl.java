package kr.spring.board.customboard.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.board.customboard.dao.CustomPostMapper;
import kr.spring.board.customboard.vo.CustomBoardVO;
import kr.spring.board.customboard.vo.CustomPostVO;

@Service("customPostService")
public class CustomPostServiceImpl implements CustomPostService {

	@Resource
	CustomPostMapper customPostMapper;	

	@Override
	public CustomBoardVO selectBoardInfo(int board_num) {
		return customPostMapper.selectBoardInfo(board_num);
	}
	//게시글 목록
	@Override 
	public List<CustomPostVO> selectPostList(Map<String, Object> map) {
		return customPostMapper.selectPostList(map);
	}
	//페이징처리를 위한 글 count
	@Override
	public int selectRowCount(Map<String, Object> map) {
		return customPostMapper.selectRowCount(map);
	}
	//자바빈 얻기
	@Override
	public CustomPostVO selectCustomPost(Integer board_num) {
		return customPostMapper.selectCustomPost(board_num);
	}
	//게시글 삭제
	@Override
	public void deletePost(Integer post_num) {
		customPostMapper.deletePost(post_num);
	}
	//작성자 아이디
	@Override
	public String selectPostWriter(Integer post_num) {
		return customPostMapper.selectPostWriter(post_num);
	}
	//게시글 작성
	@Override
	public void insertPost(CustomPostVO postVO) {
		customPostMapper.insertPost(postVO);
	}
	
}
