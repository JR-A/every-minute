package kr.spring.board.infoboard.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import kr.spring.board.customboard.vo.CustomPostVO;
import kr.spring.board.infoboard.vo.InfoBoardVO;

public interface InfoBoardMapper { //추상메소드 처리
	//최근 작성된 게시글 3개
	@Select(" SELECT * FROM (SELECT * FROM infoboard ORDER BY TO_NUMBER(post_num) DESC ) WHERE ROWNUM <= 3")
	public List<InfoBoardVO> selectTop3InfoList();
	//추천 10개 이상인 게시글 top2 목록
	public List<CustomPostVO> info_hotPostTop2();
	//하나의 메소드에 하나의 sql문만 처리해야합니다.
	public List<InfoBoardVO> selectList(Map<String, Object> map);
	public List<InfoBoardVO> selectTagList(Map<String, Integer> map);
	public int selectRowCount(Map<String, Object> map);//목록에 관련됨 
	public int selectTagCount(Integer tag_num);//태그목록에 관련됨
	@Insert("INSERT INTO infoboard (post_num, mem_num, tag_num, title, content, uploadfile, filename, anonymous)VALUES(INFOBOARD_POST_SEQ.nextval, #{mem_num}, #{tag_num}, #{title}, #{content}, #{uploadfile}, #{filename}, #{anonymous})") //tag_num은 fk무결성 제약조건에 위배되어 제외시킴, anonymous기능은 아직 구현하지 않아서 제외시킴(NOY NULL이라 NullPointerException 발생)
	public void insertBoard(InfoBoardVO board);
	//b.mem_num추가 테이블하였음, join시 칼럼이 2개이므로 어떤 칼럼의 데이터를 참조하는지 애매하니 확실하게 b.mem_num을 참조한다고 알려주면 오류가 안남! ORA-00918 열의 정의가 애매합니다 해결법!!!!
	@Select("SELECT post_num, b.mem_num, tag_num, title, content, TO_CHAR(modify_date,'MM-DD HH24:MI') modify_date, id, anonymous, uploadfile, filename FROM infoboard b JOIN member m ON b.mem_num=m.mem_num WHERE b.post_num=#{post_num}")
	public InfoBoardVO selectBoard(Integer post_num);
	@Select("SELECT * FROM INFOBOARD i JOIN member_detail d ON i.mem_num=d.mem_num WHERE i.mem_num=#{mem_num}")
	public InfoBoardVO selectMember(Integer mem_num);//마이바티스는 객체로 인식하기때문에 int 보다는 integet로 사용하는게 좋음!
	public void updateBoard(InfoBoardVO board);
	@Delete("DELETE FROM infoboard WHERE post_num = #{post_num}")
	public void deleteBoard(Integer post_num);
	//신고하기
	@Insert("INSERT INTO infoboard_blame_post (blame_num, post_num, mem_num) VALUES (info_blame_post_SEQ.nextval, #{post_num}, #{mem_num})")
	public void insertBlameBoard(InfoBoardVO infoBoardVO);
	//부모글 삭제시 댓글이 존재하면 부모글 삭제전 댓글 삭제
	@Delete("DELETE FROM infoboard_comment WHERE post_num=#{post_num}")
	public void deleteReplyByPostNum(Integer post_num);
	//신고하기 삭제
	@Delete("DELETE FROM infoboard_blame_post WHERE post_num=#{post_num}")
	public void deleteBlamePostByPostNum(Integer post_num);
	//게시판 좋아요 삭제
	@Delete("DELETE FROM infoboard_like_post WHERE post_num=#{post_num}")
	public void deleteLikePostByPostNum(Integer post_num);
	//댓글 좋아요 삭제
	@Delete("DELETE FROM infoboard_like_comment WHERE comment_num=#{comment_num}")
	public void deleteLikeCommentByCommentNum(Integer comment_num);

	
}