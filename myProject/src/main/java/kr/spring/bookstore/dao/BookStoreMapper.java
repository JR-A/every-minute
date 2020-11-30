package kr.spring.bookstore.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import kr.spring.bookstore.vo.BookStoreVO;

public interface BookStoreMapper {
	public List<BookStoreVO> selectList(Map<String, Object> map);
	public int selectRowCount(Map<String, Object> map);
	@Insert("INSERT INTO bookstoreboard(bs_num,mem_num,bs_selling_price,bs_comment,bs_condition,bs_method,bs_address,uploadfile,filename) VALUES(bookstore_seq.nextval,#{mem_num},#{bs_selling_price},#{bs_comment},#{bs_condition},#{bs_method},#{bs_address},#{uploadfile},#{filename})")
	public void insertBoard(BookStoreVO vo);
	@Select("SELECT * FROM bookstoreboard b JOIN member m ON b.mem_num = m.mem_num WHERE b.bs_num = #{bs_num}")
	public BookStoreVO selectBoard(Integer bs_num);
	public void updateBoard(BookStoreVO vo);
	@Delete("DELETE FROM bookstoreboard WHERE bs_num = #{bs_num}")
	public void deleteBoard(Integer bs_num);
}
