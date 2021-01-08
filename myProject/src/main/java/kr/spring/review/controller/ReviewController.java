package kr.spring.review.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.member.vo.MemberVO;
import kr.spring.review.service.ReviewService;
import kr.spring.review.vo.ReviewVO;
import kr.spring.review.vo.SubjectRateVO;
import kr.spring.timetable.service.TimetableService;
import kr.spring.timetable.vo.SubjectVO;
import kr.spring.timetable.vo.TimetableVO;

import kr.spring.util.*;

@Controller
public class ReviewController {
	
	//프로퍼티
	final String CURRENT_SEMESTER = "2020-2";
	static final String[] HOMEWORK_VAL = {"많음", "보통", "없음"};
	static final String[] TEAM_VAL = {"많음", "보통", "없음"};
	static final String[] GRADE_VAL = {"F폭격기", "매우깐깐함", "비율채워줌", "학점느님"};
	static final String[] ATTENDANCE_VAL = {"매우엄격", "항상체크", "가끔체크", "반영안함"};
	static final String[] EXAM_VAL = {"네번이상", "세번", "두번", "한번", "없음"};
	
	@Resource
	private ReviewService reviewService;
	@Resource
	private TimetableService timetableService;
	
	//로그 처리(로그 대상 지정)
	private Logger log = Logger.getLogger(this.getClass());
	
	//강의평가 폼 호출
	@RequestMapping("/review/reviewList.do")
	public ModelAndView form(HttpSession session) {
		
		List<SubjectVO> subjectList = null; //현재 학기의 기본시간표에 저장된 과목목록
		List<ReviewVO> reviewList = null;	//전체 수강평 목록
		
		//세션에 저장된 회원 정보 반환
		MemberVO member = (MemberVO)session.getAttribute("user");
		
		TimetableVO timetable = new TimetableVO();
		timetable.setMem_num(member.getMem_num());
		timetable.setSemester(CURRENT_SEMESTER);
		
		//유저의 현재 학기의 기본시간표
		TimetableVO primaryTimetable = timetableService.selectPrimaryTimetable(timetable);
		//기본시간표의 Subject 목록
		subjectList = timetableService.selectSubjectOfTimetable(primaryTimetable.getT_num());
		
		//전체 수강평 목록
		reviewList = reviewService.selectList();
		
		//수강평 목록 - HTML태그 불허, 줄바꿈 허용
		for(ReviewVO review : reviewList) {
			review.setContent(StringUtil.useBrHtml(review.getContent()));
		}
		
		ModelAndView mav = new ModelAndView();
		//뷰이름설정
		mav.setViewName("reviewList");
		//데이터 저장
		mav.addObject("subjectList", subjectList);
		mav.addObject("reviewList", reviewList);
		
		return mav;
	}
	
	//특정 과목의 강의평 상세보기
	@RequestMapping("/review/reviewDetail.do")
	public ModelAndView detailView(@RequestParam(value="sub_num") int sub_num, 
								   HttpSession session) {
		
		SubjectVO subject = null; 			//선택한 과목
		List<ReviewVO> reviewList = null;	//해당 과목의 수강평 목록
		SubjectRateVO subjectRate = null;	//해당 과목의 총평가
		
		double avgRate = 0.0;
		int intHomework = 0;
		int intTeam = 0;
		int intGrade = 0;
		int intAttendance = 0;
		int intExam = 0;
		
		subject = timetableService.selectSubject(sub_num);
		reviewList = reviewService.selectListBySubnum(sub_num);
		
		//수강평이 존재하는 경우
		if(reviewList != null && reviewList.size() > 0) {
			//평균 점수 구하기 (누적 점수를 강의평가 수로 나누기)
			subjectRate = reviewService.selectSubjectRate(sub_num);
			
			avgRate = subjectRate.getTotalRate() / (double)subjectRate.getTotalCount();
			intHomework = subjectRate.getHomework() / subjectRate.getTotalCount();
			intTeam = subjectRate.getTeam() / subjectRate.getTotalCount();
			intGrade = subjectRate.getGrade() / subjectRate.getTotalCount();			
			intAttendance = subjectRate.getAttendance() / subjectRate.getTotalCount();
			intExam = subjectRate.getExam() / subjectRate.getTotalCount();
		}
		
		//수강평 목록 - HTML태그 불허, 줄바꿈 허용
		for(ReviewVO review : reviewList) {
			review.setContent(StringUtil.useBrHtml(review.getContent()));
		}
			
		ModelAndView mav = new ModelAndView();
		//뷰이름설정
		mav.setViewName("reviewDetail");
		//데이터 저장
		mav.addObject("subject", subject);
		mav.addObject("reviewList", reviewList);
		
		mav.addObject("avgRate", avgRate);
		mav.addObject("homework", HOMEWORK_VAL[intHomework]);
		mav.addObject("team", TEAM_VAL[intTeam]);
		mav.addObject("grade", GRADE_VAL[intGrade]);
		mav.addObject("attendance", ATTENDANCE_VAL[intAttendance]);
		mav.addObject("exam", EXAM_VAL[intExam]);
		
		return mav;
	}
}
