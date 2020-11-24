package kr.spring.timetable.service;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import kr.spring.timetable.vo.TimetableVO;

public interface TimetableService {
	public int insertTimetable(TimetableVO timetable);
	public int selectRowCount(TimetableVO timetable);
	public List<TimetableVO> selectList(TimetableVO timetable);
	public TimetableVO selectTimetable(TimetableVO timetable);
}
