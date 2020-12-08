package kr.spring.main.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.board.customboard.service.CustomBoardService;
import kr.spring.board.customboard.service.CustomPostService;
import kr.spring.board.customboard.vo.CustomBoardVO;
import kr.spring.board.customboard.vo.CustomPostVO;
import kr.spring.board.freeboard.service.FreeBoardService;
import kr.spring.board.infoboard.service.InfoBoardService;
import kr.spring.member.service.MemberService;

@Controller
public class MainController {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	MemberService memberService;
	@Resource
	FreeBoardService freeBoardService;
	@Resource
	InfoBoardService InfoBoardService;
	@Resource
	CustomBoardService customBoardService;
	@Resource
	CustomPostService customPostService;
	 
	@RequestMapping("/main/main_board.do")
	public String getMain(Model model) {
		
		//사용자 게시판 최근 top3 게시글  목록 (글번호, 게시판 번호, 내용, 게시판 제목)
		//List<Map<String, Object>> postTop3List =customPostService.selectTop3PostList(); 
		List<CustomPostVO> postTop3List =customPostService.selectTop3PostList(); 

		if(log.isDebugEnabled()) {
			log.debug("<<Custom게시판-최근 게시글 top3 목록>> : " + postTop3List);
		}
		
		model.addAttribute("postTop3List", postTop3List);
		
		return "main_board";
	}
	
	//CustomBoard_게시글 상세 (게시글VO 하나만 반환)
	@RequestMapping("/main/customPostDetail.do")
	public ModelAndView boardDetail(
			@RequestParam(value="post_num") int post_num, 
			@RequestParam(value="board_num") int board_num) {
		
		if(log.isDebugEnabled()) {
			log.debug("<<사용자 생성 게시글 상세>> post_num:" + post_num + " board_num:" +board_num);
		}

		//게시판 title, subtitle
		CustomBoardVO boardInfo = customBoardService.selectCustomBoard(board_num); //게시판 정보
		if(log.isDebugEnabled()) {
			log.debug("<<Custom게시판-글 상세>> : " + boardInfo);
		}

		//게시글 자바빈 반환
		CustomPostVO customPost = customPostService.selectCustomPost(post_num); //게시글 정보
		
		ModelAndView mav = new ModelAndView();
		// view 이름
		mav.setViewName("customPostView");
		mav.addObject("customPost", customPost);
		mav.addObject("boardInfo", boardInfo);
		return mav;
	}
	
	//CustomBoard_이미지 출력
	@RequestMapping("/main/customPostImageView.do")
	public ModelAndView viewImage(@RequestParam int post_num, @RequestParam int board_num) {
		
		CustomPostVO customPost = customPostService.selectCustomPost(post_num);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		
		//byte[]타입의 데이터
		mav.addObject("imageFile", customPost.getUploadfile());
		mav.addObject("filename", customPost.getFilename());
		
		return mav;
	}
}





