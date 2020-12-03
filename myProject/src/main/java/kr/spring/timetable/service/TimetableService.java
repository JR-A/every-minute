package kr.spring.timetable.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import kr.spring.timetable.vo.CustomSubjectVO;
import kr.spring.timetable.vo.SubjectVO;
import kr.spring.timetable.vo.TimetableVO;

public interface TimetableService {
	public int insertTimetable(TimetableVO timetable);
	public int selectTimetableCountOfUser(TimetableVO timetable);
	public List<TimetableVO> selectList(TimetableVO timetable);
	public TimetableVO selectTimetable(int t_num);
	public int selectSubjectCountOfTimetable(int t_num);
	public List<SubjectVO> selectSubjectOfTimetable(int t_num);
	public TimetableVO selectPrimaryTimetable(TimetableVO timetable);
	public List<SubjectVO> selectSubjectList();
	public SubjectVO selectSubject(int sub_num);
	public void insertSubject(int t_num, int sub_num);
	public void updateModifyDate(int t_num);
	public void deleteSubject(@Param("t_num") int t_num, @Param("sub_num") int sub_num);
	
	public void insertCustomSubject(CustomSubjectVO customSubjectVO);
	public int selectCustomSubjectCountOfTimetable(int t_num);
	public List<CustomSubjectVO> selectCustomSubjectList(int t_num);
	
	public void setIsPrimary(TimetableVO timetable);
	public void updateTimetableName(TimetableVO timetable);
	public void deleteTimetable(int t_num);
}
