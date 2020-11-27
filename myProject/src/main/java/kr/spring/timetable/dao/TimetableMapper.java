package kr.spring.timetable.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

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
}
