package kr.spring.timetable.service;

import java.util.List;
import org.apache.ibatis.annotations.Select;

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
}
