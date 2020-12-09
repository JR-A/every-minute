package kr.spring.board.freeboard.service;

import java.util.List;
import java.util.Map;

import kr.spring.board.freeboard.vo.FreeBoardVO;

public interface FreeBoardService {
	public List<FreeBoardVO> selectList(Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);
	public void insertBoard (FreeBoardVO board);
	public FreeBoardVO selectBoard(Integer post_num);
	public void updateBoard (FreeBoardVO board);
	public void deleteBoard(Integer post_num);
	//최근 게시글 top3
	public List<FreeBoardVO> freeSelectTop3PostList();

}
