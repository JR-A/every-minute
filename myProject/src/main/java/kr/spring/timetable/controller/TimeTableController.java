package kr.spring.timetable.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TimeTableController {
	
	//게시판 폼 호출
	@RequestMapping("/timetable/timetableView.do")
	public String getMain() {
		return "timetableView";
	}
}
