package kr.spring.board.infoboard.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import kr.spring.board.infoboard.vo.InfoBoardVO;

public interface InfoBoardMapper { //추상메소드 처리
	//하나의 메소드에 하나의 sql문만 처리해야합니다.
	public List<InfoBoardVO> selectList(Map<String, Object> map);
	public int selectRowCount(Map<String, Object> map);//목록에 관련됨
	@Insert("INSERT INTO infoboard (post_num, mem_num, title, content, uploadfile, filename)VALUES(INFOBOARD_POST_SEQ.nextval, #{mem_num}, #{title}, #{content}, #{uploadfile}, #{filename})") //tag_num은 fk무결성 제약조건에 위배되어 제외시킴, anonymous기능은 아직 구현하지 않아서 제외시킴(NOY NULL이라 NullPointerException 발생)
	public void insertBoard(InfoBoardVO board);
	@Select("SELECT * FROM infoboard b JOIN member m ON b.mem_num=m.mem_num WHERE b.post_num=#{post_num}")
	public InfoBoardVO selectBoard(Integer post_num);
	public void updateBoard(InfoBoardVO board);
	@Delete("DELETE FROM infoboard WHERE post_num = #{post_num}")
	public void deleteBoard(Integer post_num);
}