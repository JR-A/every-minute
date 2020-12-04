package kr.spring.board.customboard.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

	//게시판 삭제
	@RequestMapping("/customBoard/deleteCustomBoard.do")
	public String deleteBoard(@RequestParam int board_num, Model model, HttpServletRequest request) {
		
		if(log.isDebugEnabled()) {
			log.debug("<<사용자 게시판 삭제>> : " + board_num);
		}
		
		//게시판 삭제
		customBoardService.deleteCustomBoard(board_num);
		
		model.addAttribute("message", "게시판 삭제 완료!!");
		model.addAttribute("url", request.getContextPath()+"/main/main_board.do");
		
		return "common/result";
	}
	
	//게시판 수정 폼 호출
	@RequestMapping(value="/customBoard/updateCustomBoard.do", method=RequestMethod.GET)
	public String form(@RequestParam int board_num, Model model) {
		
		CustomBoardVO customBoardVO = customBoardService.selectCustomBoard(board_num);
		
		model.addAttribute("customBoardVO", customBoardVO);
		
		return "customBoardModify";
	}
	
	//게시판 수정 처리
	@RequestMapping(value="/customBoard/updateCustomBoard.do", method=RequestMethod.POST)
	public String submitUpdate(@Valid CustomBoardVO customBoardVO, BindingResult result, HttpServletRequest request, HttpSession session,  Model model) {
		
		if(log.isDebugEnabled()) {
			log.debug("<<CustomBoard 게시판 수정>> : " + customBoardVO);
		}
		//유효성 체크 결과 오류가 있으면 폼 호출
		if(result.hasErrors()) {
			return "customBoardModify";
		}
		
		//회원 번호 셋팅
		MemberVO user = (MemberVO)session.getAttribute("user");
		customBoardVO.setMem_num(user.getMem_num());

		//게시판 수정
		customBoardService.updateCustomBoard(customBoardVO);
		
		//게시판 자바빈 반환
		CustomBoardVO boardInfo = customBoardService.selectCustomBoard(customBoardVO.getBoard_num()); //게시판 정보
		
		if(log.isDebugEnabled()) {
			log.debug("<<Custom게시판 수정 처리>> : " + boardInfo);
		}
		
		//View에 표시할 메시지
		model.addAttribute("boardInfo", boardInfo);
		model.addAttribute("message", "게시판 수정 완료!!");
		model.addAttribute("url", request.getContextPath()+"/customBoard/customPostList.do?board_num="+customBoardVO.getBoard_num());
		
		return "common/result";
	}
	
}





















