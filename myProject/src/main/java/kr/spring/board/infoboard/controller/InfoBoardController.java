package kr.spring.board.infoboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InfoBoardController {
	
	//정보게시판 글 목록
	@RequestMapping("/infoBoard/infoBoardList.do")
	public String getMain() {
		return "infoBoardList";
	}
}
