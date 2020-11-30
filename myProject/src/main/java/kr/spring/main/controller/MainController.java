package kr.spring.main.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.spring.board.customboard.service.CustomBoardService;
import kr.spring.board.freeboard.service.FreeBoardService;
import kr.spring.board.infoboard.service.InfoBoardService;
import kr.spring.member.service.MemberService;

@Controller
public class MainController {
	
	@Resource
	MemberService memberService;
	@Resource
	FreeBoardService freeBoardService;
	@Resource
	InfoBoardService InfoBoardService;
	@Resource
	CustomBoardService customBoardService;
	 
	@RequestMapping("/main/main_board.do")
	public String getMain() {
		return "main_board";
	}
	
}





