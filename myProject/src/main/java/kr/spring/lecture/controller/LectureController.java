package kr.spring.lecture.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LectureController {
	
	@RequestMapping("/lecture/lectureView.do")
	public ModelAndView form() {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("lectureView");

		
		return mav;
	}
}
