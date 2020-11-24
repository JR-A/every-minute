package kr.spring.timetable.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.member.vo.MemberVO;
import kr.spring.timetable.service.TimetableService;
import kr.spring.timetable.vo.TimetableVO;

@Controller
public class TimetableController {
	//프로퍼티
	@Resource
	private TimetableService timetableService;
	
	//로그 처리(로그 대상 지정)
	private Logger log = Logger.getLogger(this.getClass());
	
	//게시판 폼 호출
	@RequestMapping("/timetable/timetableView.do")
	public ModelAndView getList(@RequestParam(value="semester", defaultValue="2020-2") String semester, HttpSession session) {
		//세션에 저장된 회원 정보 반환
		MemberVO member = (MemberVO)session.getAttribute("user");
		
		TimetableVO timetable = new TimetableVO();
		timetable.setMem_num(member.getMem_num());
		timetable.setSemester(semester);
		
		//시간표 개수
		int count = timetableService.selectRowCount(timetable);
		if(log.isDebugEnabled()) {
			log.debug("<<timetableCount>> : "+ count);	
		}
		//시간표 목록
		List<TimetableVO> list = null;
		if(count > 0) {
			list = timetableService.selectList(timetable);
			if(log.isDebugEnabled()) {
				log.debug("<<timetableList>> : "+ list);	
			}
		}
		
		ModelAndView mav = new ModelAndView();
		//뷰이름설정
		mav.setViewName("timetableView");
		//데이터 저장
		mav.addObject("timetableCount", count);
		mav.addObject("timetableList", list);
		
		return mav;
	}
}
