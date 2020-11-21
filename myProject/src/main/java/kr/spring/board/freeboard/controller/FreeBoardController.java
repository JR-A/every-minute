package kr.spring.board.freeboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FreeBoardController {
	
	//자유게시판 글 목록
	@RequestMapping("/freeBoard/freeBoardList.do")
	public String getMain() {
		return "freeBoardList";
	}
}
