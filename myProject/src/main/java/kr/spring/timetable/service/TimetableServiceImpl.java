package kr.spring.timetable.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.timetable.dao.TimetableMapper;
import kr.spring.timetable.vo.TimetableVO;

@Service("timetableService")
public class TimetableServiceImpl implements TimetableService {
	//프로퍼티
	@Resource
	private TimetableMapper timetableMapper;
	
	@Override
	public int insertTimetable(TimetableVO timetable) {
		int t_num = timetableMapper.selectT_num();
		timetable.setT_num(t_num);
		timetableMapper.insertTimetable(timetable);
		return t_num;
	}
	
	@Override
	public int selectTimetableCountOfUser(TimetableVO timetable) {
		return timetableMapper.selectTimetableCountOfUser(timetable);
	}
	
	@Override
	public int selectRowCount(TimetableVO timetable) {
		return timetableMapper.selectRowCount(timetable);
	}

	@Override
	public List<TimetableVO> selectList(TimetableVO timetable) {
		return timetableMapper.selectList(timetable);
	}

	@Override
	public TimetableVO selectTimetable(TimetableVO timetable) {
		return timetableMapper.selectTimetable(timetable);
	}



}
