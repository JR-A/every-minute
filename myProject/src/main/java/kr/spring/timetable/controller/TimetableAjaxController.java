package kr.spring.timetable.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.member.vo.MemberVO;
import kr.spring.timetable.common.TimesMaker;
import kr.spring.timetable.service.TimetableService;
import kr.spring.timetable.vo.SubjectVO;
import kr.spring.timetable.vo.TimesVO;
import kr.spring.timetable.vo.TimetableVO;

@Controller
public class TimetableAjaxController {
	
	//프로퍼티
	@Resource
	private TimetableService timetableService;
	@Resource
	private TimesMaker timesMaker;
	
	//로그 처리(로그 대상 지정)
	private Logger log = Logger.getLogger(this.getClass());
	
	//시간표 생성
	@RequestMapping("/timetable/createTimetable.do")
	@ResponseBody
	public Map<String, String> createTimetable(@RequestParam("semester") String semester, HttpSession session) {
		
		Map<String, String> map = new HashMap<String, String>();
		TimetableVO timetable = new TimetableVO();
		
		//세션에 저장된 회원 정보 반환
		MemberVO member = (MemberVO)session.getAttribute("user");
	
		//로그처리
		if(log.isDebugEnabled()) {
			log.debug("<<시간표 생성>> : " + member.getId() + ", " + semester);
		}
		
		//시간표 생성
		timetable.setMem_num(member.getMem_num());	
		timetable.setSemester(semester);
		timetable.setIsPrimary(0);
		
		int count = timetableService.selectTimetableCountOfUser(timetable);
		
		timetable.setT_name("시간표"+(count + 1));	//기본 시간표이름 (더 생각해보기)
		int t_num = timetableService.insertTimetable(timetable);	//시간표 추가
		
		map.put("t_num", Integer.toString(t_num));
		map.put("semester", timetable.getSemester());
		
		return map;
	}
	
	//과목 목록 가져오기
	@RequestMapping("/timetable/loadSubjects.do")
	@ResponseBody
	public List<SubjectVO> loadSubjects() {
		
		List<SubjectVO> subjectList = timetableService.selectSubjectList();
		
		return subjectList;
	}
	
	//과목 미리보기
	@RequestMapping("/timetable/previewSubject.do")
	@ResponseBody
	public List<TimesVO> makeTimesVO(@RequestParam(value="sub_num") String sub_num){
		List<TimesVO> timesList = new ArrayList<TimesVO>();
		List<SubjectVO> subjectList = new ArrayList<SubjectVO>();
		
		SubjectVO subject = timetableService.selectSubject(Integer.parseInt(sub_num));
		subjectList.add(subject);
		
		timesList = timesMaker.makeTimesVO(subjectList);
		
		return timesList;
	}
}
