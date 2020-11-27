package kr.spring.board.customboard.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.spring.board.customboard.service.CustomBoardService;
import kr.spring.board.customboard.vo.CustomBoardVO;
import kr.spring.member.vo.MemberVO;

@Controller
public class CustomBoardController {

	private Logger log = Logger.getLogger(this.getClass());

	@Resource
	CustomBoardService customBoardService;

	//자바빈 초기화
	@ModelAttribute
	public CustomBoardVO initCommand() {
		return new CustomBoardVO();
	}

	//게시판 등록 폼
	@RequestMapping(value="/customBoard/createCustomBoard.do", method=RequestMethod.GET)
	public String boardForm() {
		return "createCustomBoard"; 
	} 	

	//게시판 등록 처리
	@RequestMapping(value="/customBoard/createCustomBoard.do", method=RequestMethod.POST)
	public String BoardSubmit(@Valid CustomBoardVO customBoardVO, BindingResult result, HttpServletRequest request, HttpSession session) {

		if(log.isDebugEnabled()) {
			log.debug("<<게시판 등록>> : " + customBoardVO);
		}
		
		if(result.hasErrors()) {
			return boardForm(); 
		}

		//회원 번호 세팅
		MemberVO vo = (MemberVO)session.getAttribute("user");
		customBoardVO.setMem_num(vo.getMem_num());
		
		
		//게시판 유형과 익명여부 세팅
		int type = Integer.parseInt(request.getParameter("type"));
		customBoardVO.setType(type);

		int anonymous = Integer.parseInt(request.getParameter("anonymous"));
		customBoardVO.setAnonymous(anonymous);


		//게시판 등록
		customBoardService.insertCustomBoard(customBoardVO);

		if(log.isDebugEnabled()) {
			log.debug("<<게시판 생성>> : " + customBoardVO);
		}

		//View에 표시할 메시지
		/*model.addAttribute("message", "게시판 생성 완료!");
		model.addAttribute("url",  request.getContextPath() + "main/mainboard.do");*/
		
		return "redirect:/main/main_board.do";
	}


}
