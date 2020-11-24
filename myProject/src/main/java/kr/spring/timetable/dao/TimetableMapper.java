package kr.spring.timetable.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import kr.spring.timetable.vo.TimetableVO;

public interface TimetableMapper {
	@Select("SELECT timetable_seq.nextval FROM dual")
	public int selectT_num();
	
	@Select("SELECT COUNT(*) FROM Timetable WHERE mem_num=#{mem_num} and semester LIKE #{semester}")
	public int selectTimetableCountOfUser(TimetableVO timetable);
	
	@Insert("INSERT INTO Timetable (t_num, mem_num, semester, isPrimary) VALUES (#{t_num}, #{mem_num}, #{semester}, #{isPrimary})")
	public void insertTimetable(TimetableVO timetable);
	
	@Select("SELECT COUNT(*) FROM Timetable WHERE mem_num=#{mem_num} and semester LIKE #{semester}")
	public int selectRowCount(TimetableVO timetable);
	
	@Select("SELECT * FROM Timetable WHERE mem_num=#{mem_num} and semester LIKE #{semester}")
	public List<TimetableVO> selectList(TimetableVO timetable);
	
	@Select("SELECT * FROM Timetable WHERE t_num=#{t_num}")
	public TimetableVO selectTimetable(TimetableVO timetable);
	

}
