package kr.spring.board.freeboard.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import kr.spring.board.freeboard.vo.FreeBoardVO;

public interface FreeBoardMapper {
	public List<FreeBoardVO> selectList(Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);
	@Insert("INSERT INTO freeboard(post_num,title,content,uploadfile,filename,mem_num,anonymous) VALUES(freeboard_post_seq.nextval,#{title},#{content},#{uploadfile},#{filename},#{mem_num},#{anonymous})")
	public void insertBoard (FreeBoardVO board);
	@Select("SELECT * FROM freeboard f JOIN member m ON f.mem_num=m.mem_num JOIN member_detail d ON m.mem_num=d.mem_num WHERE f.post_num=#{post_num}")
	public FreeBoardVO selectBoard(Integer post_num);
	public void updateBoard (FreeBoardVO board);
	@Delete("DELETE FROM freeboard WHERE post_num=#{post_num}")
	public void deleteBoard(Integer post_num);
	//최근 게시글 top3
	public List<FreeBoardVO> freeSelectTop3PostList();
	//추천 10개 이상인 게시글 top2 목록
	public List<FreeBoardVO> free_hotPostTop2();

}
