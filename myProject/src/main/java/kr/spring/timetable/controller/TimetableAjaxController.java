package kr.spring.timetable.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.member.vo.MemberVO;
import kr.spring.timetable.service.TimetableService;
import kr.spring.timetable.vo.TimetableVO;

@Controller
public class TimetableAjaxController {
	
	//프로퍼티
	@Resource
	private TimetableService timetableService;
	
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
		
		//시간표 생성, DB에 추가
		timetable.setMem_num(member.getMem_num());	
		timetable.setSemester(semester);
		
		int t_num = timetableService.insertTimetable(timetable);	//시간표 추가
		
		map.put("t_num", Integer.toString(t_num));
		map.put("semester", timetable.getSemester());
		
		return map;
	}
	
	//새로 추가된 시간표 보여주기
	@RequestMapping("/timetable/createdTimetableView.do")
	@ResponseBody
	public Map<String, String> getList(@RequestParam("t_num") String t_num, HttpSession session) {
		Map<String, String> map = new HashMap<String, String>();
		
		TimetableVO timetable = new TimetableVO();
		timetable.setT_num(Integer.parseInt(t_num));
		
		//시간표 불러오기
		TimetableVO vo = timetableService.selectTimetable(timetable);
		if(log.isDebugEnabled()) {
			log.debug("<<생성된 TimetableVO>> : "+ vo);	
		}
		
		//데이터 저장
		map.put("t_num", Integer.toString(vo.getT_num()));
		map.put("t_name", vo.getT_name());
		
		return map;
	}
}
