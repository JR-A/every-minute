package kr.spring.timetable.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.timetable.dao.TimetableMapper;
import kr.spring.timetable.vo.SubjectVO;
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
	public List<TimetableVO> selectList(TimetableVO timetable) {
		return timetableMapper.selectList(timetable);
	}

	@Override
	public TimetableVO selectTimetable(int t_num) {
		return timetableMapper.selectTimetable(t_num);
	}
	
	@Override
	public int selectSubjectCountOfTimetable(int t_num) {
		return timetableMapper.selectSubjectCountOfTimetable(t_num);
	}
	
	@Override
	public List<SubjectVO> selectSubjectOfTimetable(int t_num) {
		return timetableMapper.selectSubjectOfTimetable(t_num);
	}

	@Override
	public TimetableVO selectPrimaryTimetable(TimetableVO timetable) {
		return timetableMapper.selectPrimaryTimetable(timetable);
	}

	@Override
	public List<SubjectVO> selectSubjectList() {
		return timetableMapper.selectSubjectList();
	}

	@Override
	public SubjectVO selectSubject(int sub_num) {
		return timetableMapper.selectSubject(sub_num);
	}

	@Override
	public void insertSubject(int t_num, int sub_num) {
		timetableMapper.insertSubject(t_num, sub_num);
	}

	@Override
	public void updateModifyDate(int t_num) {
		timetableMapper.updateModifyDate(t_num);
	}

	@Override
	public void deleteSubject(int t_num, int sub_num) {
		timetableMapper.deleteSubject(t_num, sub_num);
	}

}
