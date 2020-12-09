package kr.spring.board.infoboard.service;

import java.util.List;
import java.util.Map;

import kr.spring.board.infoboard.vo.InfoBoardVO;

public interface InfoBoardService {
	//하나의 메소드에 하나의 sql문만 처리해야함.
	public List<InfoBoardVO> selectList(Map<String, Object> map);
	public List<InfoBoardVO> selectTagList(Map<String, Integer> map);
	public int selectRowCount(Map<String, Object> map);//목록에 관련됨
	public int selectTagCount(Integer tag_num);//태그 목록에 관련되어있음.
	public void insertBoard(InfoBoardVO board);
	public InfoBoardVO selectBoard(Integer post_num);
	public void updateBoard(InfoBoardVO board);
	public void deleteBoard(Integer post_num);
	//댓글 기능 보류 public List<InfoBoardVO> readReply(Integer post_num);
	//신고기능 
	public void insertBlameBoard(InfoBoardVO infoBoardVO);
}