package kr.spring.review.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.review.vo.ReviewVO;
import kr.spring.review.vo.SubjectRateVO;
import kr.spring.timetable.vo.SubjectVO;

public interface ReviewMapper {
	
	//전체 수강평 목록(최신글 상위)
	@Select("SELECT * FROM Review JOIN Subject USING(sub_num) ORDER BY post_num DESC")
	public List<ReviewVO> selectList();
	
	//교수명, 과목명으로 검색한 과목목록
	//아직 수강평이 존재하지 않는 과목도 누락되지 않도록 OUTER JOIN
	@Select("SELECT sub_num, sub_name, prof_name, totalRate FROM Subject s LEFT OUTER JOIN SubjectRate USING(sub_num) WHERE s.sub_name LIKE '%' || #{keyword} || '%' OR prof_name LIKE '%' || #{keyword} || '%'")
	public List<SubjectRateVO> searchedSubjectsList(String keyword);
	
	//특정 과목의 수강평 목록(최신글 상위)
	@Select("SELECT * FROM Review WHERE sub_num=#{sub_num} ORDER BY post_num DESC")
	public List<ReviewVO> selectListBySubnum(int sub_num);
	
	//특정 과목의 평가 가져오기
	@Select("SELECT * FROM SubjectRate WHERE sub_num=#{sub_num}")
	public SubjectRateVO selectSubjectRate(int sub_num);
	
	//수강평 등록
	@Insert("INSERT INTO Review (post_num, mem_num, sub_num, content, rate, reg_date) VALUES(review_seq.nextval, #{mem_num}, #{sub_num}, #{content}, #{rate}, SYSDATE)")
	public void insertReview(ReviewVO review);
	
	//과목번호로 과목의 총 평점레코드 개수 반환(레코드 존재하는지)
	@Select("SELECT COUNT(*) FROM SubjectRate WHERE sub_num=#{sub_num}")
	public int selectCountOfSubjectRateRecord(ReviewVO review);
	
	//수강평 등록
	//(첫 수강평 등록시)과목의 총평점 레코드 생성
	@Insert("INSERT INTO SubjectRate (sub_num, totalCount, totalRate, homework, team, grade, attendance, exam) VALUES(#{sub_num}, 1, #{totalRate}, #{homework}, #{team}, #{grade}, #{attendance}, #{exam})")
	public void insertNewSubjectRate(SubjectRateVO subjectRate);
	
	//수강평 등록
	//과목의 총평점에 반영
	@Update("UPDATE SubjectRate SET totalCount=totalCount+1, totalRate=totalRate+#{totalRate}, homework=homework+#{homework}, team=team+#{team}, grade=grade+#{grade}, attendance=attendance+#{attendance}, exam=exam+#{exam} WHERE sub_num=#{sub_num}")
	public void updateSubjectRate(SubjectRateVO subjectRate);
	
}