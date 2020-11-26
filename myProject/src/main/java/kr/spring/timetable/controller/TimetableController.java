package kr.spring.timetable.controller;

import java.util.ArrayList;
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
import kr.spring.timetable.vo.SubjectVO;
import kr.spring.timetable.vo.TimesVO;
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
	public ModelAndView getList(@RequestParam(value="semester", defaultValue="2020-2") String semester,
								@RequestParam(value="t_num", defaultValue="") String t_num, HttpSession session) {

		TimetableVO vo = null;
		List<SubjectVO> subjectList = null;
		int tableCredit = 0;
		int subjectCnt = 0;
		int selectedT_num = 0;
		
		//세션에 저장된 회원 정보 반환
		MemberVO member = (MemberVO)session.getAttribute("user");
		
		TimetableVO timetable = new TimetableVO();
		timetable.setMem_num(member.getMem_num());
		timetable.setSemester(semester);
		
		//해당 학기의 시간표 개수
		int count = timetableService.selectTimetableCountOfUser(timetable);
		if(log.isDebugEnabled()) {
			log.debug("<<timetableCount>> : "+ count);	
		}
		//해당 학기의 시간표 목록
		List<TimetableVO> timetableList = null;
		List<TimesVO> timesList = null;
		if(count > 0) {
			timetableList = timetableService.selectList(timetable);
			
			if(log.isDebugEnabled()) {
				log.debug("<<timetableList>> : "+ timetableList);	
			}
			//t_num 정보가 넘어왔다면 시간표에 저장된 과목(+커스텀추가과목) 가져오기
			//일단 과목만 가져오기
			if(!t_num.equals("")) { //t_num정보가 넘어왔다면 해당 시간표의 정보, 해당 시간표의 과목
				vo = timetableService.selectTimetable(Integer.parseInt(t_num));
				subjectCnt = timetableService.selectSubjectCountOfTimetable(Integer.parseInt(t_num));
				if(subjectCnt > 0) {
					subjectList = timetableService.selectSubjectOfTimetable(Integer.parseInt(t_num));
					timesList = makeTimesVO(subjectList);
				}
			}else {	// t_num 정보가 존재하지 않으면 기본시간표정보, 기본시간표의 과목
				vo = timetableService.selectPrimaryTimetable(timetable);
				subjectCnt = timetableService.selectSubjectCountOfTimetable(vo.getT_num());
				if(subjectCnt > 0) {
					subjectList = timetableService.selectSubjectOfTimetable(vo.getT_num());
					timesList = makeTimesVO(subjectList);
				}
			}
			selectedT_num = vo.getT_num();
			
		}
		
		if(subjectList != null) {
			for(int i=0; i<subjectList.size(); i++) {
				tableCredit += subjectList.get(i).getSub_credit();
			}
		}
		
		ModelAndView mav = new ModelAndView();
		//뷰이름설정
		mav.setViewName("timetableView");
		//데이터 저장
		mav.addObject("timetableCount", count);
		mav.addObject("timetableList", timetableList);
		mav.addObject("timetable", vo);
		mav.addObject("timetableSubjectCount", subjectCnt);
		mav.addObject("timetableSubjectList", subjectList);
		mav.addObject("timetableCredit", tableCredit);
		mav.addObject("semester", semester);
		
		mav.addObject("timesList", timesList);	//시간표매핑
		mav.addObject("selectedT_num", selectedT_num);	//선택된 시간표번호

		return mav;
	}
	
	//시간표에 보여주기 위해 subjectList로 시작시간, 끝나는시간, 교실 구하기
	public List<TimesVO> makeTimesVO(List<SubjectVO> subjectList){
		List<TimesVO> timesList = new ArrayList<TimesVO>();
		
		//for(SubjectVO subject:subjectList) {	 //index가 필요해서 확장for문 대신 그냥 for문
		for(int s=0; s<subjectList.size(); s++) {
			
			String[] sub_time = subjectList.get(s).getSub_time().split(","); //ex. 월12,화23 ','기준 쪼개기
			String[] sub_class = subjectList.get(s).getSub_classRoom().split(","); //ex. T701,T702 ','기준 쪼개기		
			
			for(int i=0; i<sub_time.length; i++) {
				TimesVO times = new TimesVO();				//TimesVO 객체 생성
				times.setSub_num(subjectList.get(s).getSub_num());		//sub_num 저장
				times.setSub_name(subjectList.get(s).getSub_name());	//sub_name저장
				times.setColor(s+1);
				
				switch(sub_time[i].charAt(0)) {				//월12 에서 '월'을 요일로 설정
					case '월': times.setDay(0); break;
					case '화': times.setDay(1); break;
					case '수': times.setDay(2); break;
					case '목': times.setDay(3); break;
					case '금': times.setDay(4); break;
				}
				//char -> int 위해  -'0' 해줌
				times.setStarttime(sub_time[i].charAt(1) - '0');	//월12에서 '1'을 시작교시로 설정
				times.setEndtime(sub_time[i].charAt(sub_time[i].length()-1) - '0');	//월12에서 '2'를 끝나는교시로 설정
				times.setClassRoom(sub_class[i]);	//강의실을 T701로 설정
				times.setProf_name(subjectList.get(s).getProf_name());
				
				timesList.add(times);
			}
			
		}
		
		return timesList;
	}
	
}
