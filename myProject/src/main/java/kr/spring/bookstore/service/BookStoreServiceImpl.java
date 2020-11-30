package kr.spring.bookstore.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.bookstore.dao.BookStoreMapper;
import kr.spring.bookstore.vo.BookStoreVO;

@Service("bookStoreService")
public class BookStoreServiceImpl implements BookStoreService {
	@Resource
	BookStoreMapper bookStoreMapper;
	
	@Override
	public List<BookStoreVO> selectList(Map<String, Object> map) {
		return bookStoreMapper.selectList(map);
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		return bookStoreMapper.selectRowCount(map);
	}

	@Override
	public void insertBoard(BookStoreVO vo) {
		bookStoreMapper.insertBoard(vo);
	}

	@Override
	public BookStoreVO selectBoard(Integer bs_num) {
		return bookStoreMapper.selectBoard(bs_num);
	}

	@Override
	public void updateBoard(BookStoreVO vo) {
		bookStoreMapper.updateBoard(vo);
	}

	@Override
	public void deleteBoard(Integer bs_num) {
		bookStoreMapper.deleteBoard(bs_num);
	}

}
