package kr.spring.review.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.member.vo.MemberVO;
import kr.spring.review.service.ReviewService;
import kr.spring.review.vo.ReviewVO;
import kr.spring.review.vo.SubjectRateVO;
import kr.spring.timetable.service.TimetableService;

@Controller
public class ReviewAjaxController {
	//프로퍼티
	@Resource
	private ReviewService reviewService;
	@Resource
	private TimetableService timetableService;
	
	//로그 처리(로그 대상 지정)
	private Logger log = Logger.getLogger(this.getClass());
		
	
	//강의평 추가
	@RequestMapping("/review/insertReview.do")
	@ResponseBody
	public Map<String, String> insertReview(@ModelAttribute ReviewVO review,
											   @ModelAttribute SubjectRateVO subjectRate,
											   HttpSession session) {
		
		Map<String, String> map = new HashMap<String, String>();
		
		//세션에 저장된 회원 정보 반환
		MemberVO member = (MemberVO)session.getAttribute("user");
		review.setMem_num(member.getMem_num());
		
		subjectRate.setTotalRate(review.getRate());	//점수 반영위해 rate세팅
		
		//수강평 등록, 총점수 반영
		reviewService.insertReview(review, subjectRate);
		
		map.put("result", "success");
		
		return map;
	}
}
