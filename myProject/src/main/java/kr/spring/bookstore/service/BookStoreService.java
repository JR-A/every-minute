package kr.spring.bookstore.service;

import java.util.List;
import java.util.Map;

import kr.spring.bookstore.vo.BookStoreVO;

public interface BookStoreService {
	public List<BookStoreVO> selectList(Map<String, Object> map);
	public int selectRowCount(Map<String, Object> map);
	public void insertBoard(BookStoreVO vo);
	public BookStoreVO selectBoard(Integer bs_num);
	public void updateBoard(BookStoreVO vo);
	public void deleteBoard(Integer bs_num);
}
