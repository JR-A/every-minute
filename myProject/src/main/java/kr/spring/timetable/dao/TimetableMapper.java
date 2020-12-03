package kr.spring.timetable.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.timetable.vo.CustomSubjectVO;
import kr.spring.timetable.vo.SubjectVO;
import kr.spring.timetable.vo.TimetableVO;

public interface TimetableMapper {
	@Select("SELECT timetable_seq.nextval FROM dual")
	public int selectT_num();
	
	//유저의 해당학기의 시간표개수
	@Select("SELECT COUNT(*) FROM Timetable WHERE mem_num=#{mem_num} AND semester LIKE #{semester}")
	public int selectTimetableCountOfUser(TimetableVO timetable);
	
	//유저의 해당학기에 시간표 추가하기
	@Insert("INSERT INTO Timetable (t_num, mem_num, semester, t_name, isPrimary) VALUES (#{t_num}, #{mem_num}, #{semester}, #{t_name}, #{isPrimary})")
	public void insertTimetable(TimetableVO timetable);
	
	//유저의 해당학기의 시간표들의 정보
	@Select("SELECT * FROM Timetable WHERE mem_num=#{mem_num} and semester LIKE #{semester} ORDER BY t_num")
	public List<TimetableVO> selectList(TimetableVO timetable);
	
	//시간표 번호로 시간표 정보 가져오기
	@Select("SELECT * FROM Timetable WHERE t_num=#{t_num}")
	public TimetableVO selectTimetable(int t_num);
	
	//해당 시간표가 가진 Subject 개수
	@Select("SELECT COUNT(*) FROM Timetable t, Timetable_Subject ts, Subject s\r\n" + 
			"WHERE t.t_num=ts.t_num and ts.sub_num=s.sub_num and t.t_num=#{t_num}")
	public int selectSubjectCountOfTimetable(int t_num);
	
	//시간표 번호로 해당시간표가 가진 Subject 목록 가져오기
	@Select("SELECT s.* FROM Timetable t, Timetable_Subject ts, Subject s\r\n" + 
			"WHERE t.t_num=ts.t_num and ts.sub_num=s.sub_num and t.t_num=#{t_num}")
	public List<SubjectVO> selectSubjectOfTimetable(int t_num);
	
	//유저의 해당학기의 기본시간표 정보
	@Select("SELECT * FROM Timetable t WHERE mem_num=#{mem_num} AND semester LIKE #{semester} and isPrimary=1")
	public TimetableVO selectPrimaryTimetable(TimetableVO timetable);
	
	//과목 목록 가져오기
	@Select("SELECT * FROM Subject ORDER BY sub_num")
	public List<SubjectVO> selectSubjectList();
	
	//과목 번호로 과목 정보 가져오기
	@Select("SELECT * FROM Subject WHERE sub_num=#{sub_num}")
	public SubjectVO selectSubject(int sub_num);
	
	//시간표에 과목 추가
	@Insert("INSERT INTO Timetable_Subject (ts_num, t_num, sub_num) VALUES(timetableSubject_seq.nextval, #{t_num}, #{sub_num})")
	public void insertSubject(@Param("t_num") int t_num, @Param("sub_num") int sub_num);

	//수정시간 반영
	@Update("UPDATE Timetable SET modify_date=SYSDATE WHERE t_num=#{t_num}")
	public void updateModifyDate(int t_num);
	
	//시간표에서 과목 삭제
	@Delete("DELETE FROM Timetable_Subject WHERE t_num=#{t_num} and sub_num=#{sub_num}")
	public void deleteSubject(@Param("t_num") int t_num, @Param("sub_num") int sub_num);
	
	//시간표에 커스텀과목 추가
	@Insert("INSERT INTO CustomSubject (csub_num, t_num, csub_name, prof_name, csub_time, csub_classRoom) VALUES(csub_seq.nextval, #{t_num}, #{csub_name}, #{prof_name}, #{csub_time}, #{csub_classRoom})")
	public void insertCustomSubject(CustomSubjectVO customSubjectVO);
	
	//해당 시간표가 가지는 CustomSubject 개수
	@Select("SELECT COUNT(*) FROM CustomSubject WHERE t_num=#{t_num}")
	public int selectCustomSubjectCountOfTimetable(int t_num);
	
	//해당 시간표의 커스텀 과목 가져오기
	@Select("SELECT * FROM CustomSubject WHERE t_num=#{t_num}")
	public List<CustomSubjectVO> selectCustomSubjectList(int t_num);
	
	//기본시간표 변경
	//해당 학기의 모든 시간표의 isPrimary값을 0으로 변경하기
	@Update("UPDATE Timetable SET isPrimary=0 WHERE mem_num=#{mem_num} AND semester LIKE #{semester}")
	public void setIsPrimaryAllFalse(TimetableVO timetable);
	
	//기본시간표 변경
	//해당 시간표의 isPrimary 값을 1로 변경하기
	@Update("UPDATE Timetable SET isPrimary=1 WHERE t_num=#{t_num}")
	public void setIsPrimaryTrue(TimetableVO timetable);
	
	//시간표 설정 변경
	//시간표 이름 수정
	@Update("UPDATE Timetable SET t_name=#{t_name}, modify_date=SYSDATE WHERE t_num=#{t_num}")
	public void updateTimetableName(TimetableVO timetable);
	
	//시간표 설정 변경
	//시간표 삭제
	@Delete("DELETE FROM Timetable WHERE t_num=#{t_num}")
	public void deleteTimetable(int t_num);
	
	//시간표 설정 변경
	//시간표_과목 삭제
	@Delete("DELETE FROM Timetable_Subject WHERE t_num=#{t_num}")
	public void deleteTimetable_Subject(int t_num);
	
	//시간표 설정 변경
	//커스텀과목 삭제
	@Delete("DELETE FROM CustomSubject WHERE t_num=#{t_num}")
	public void deleteCustomSubject(int t_num);
}
