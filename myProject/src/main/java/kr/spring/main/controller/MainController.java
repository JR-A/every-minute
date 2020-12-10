package kr.spring.main.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.board.customboard.service.CustomBoardService;
import kr.spring.board.customboard.service.CustomLikeService;
import kr.spring.board.customboard.service.CustomPostService;
import kr.spring.board.customboard.vo.CustomBoardVO;
import kr.spring.board.customboard.vo.CustomPostVO;
import kr.spring.board.freeboard.service.FreeBoardService;
import kr.spring.board.freeboard.service.FreeLikeService;
import kr.spring.board.freeboard.vo.FreeBoardVO;
import kr.spring.board.infoboard.service.InfoBoardService;
import kr.spring.board.infoboard.service.InfoLikeService;
import kr.spring.board.infoboard.vo.InfoBoardVO;
import kr.spring.member.service.MemberService;

@Controller
public class MainController {

	private Logger log = Logger.getLogger(this.getClass());

	@Resource
	MemberService memberService;
	@Resource
	FreeBoardService freeBoardService;
	@Resource
	FreeLikeService freeLikeService;
	@Resource
	InfoBoardService InfoBoardService;
	@Resource
	InfoLikeService infoLikeService;
	@Resource
	CustomBoardService customBoardService;
	@Resource
	CustomPostService customPostService;
	@Resource
	CustomLikeService customLikeService;

	@RequestMapping("/main/main_board.do")
	public String getMain(Model model, HttpSession session) {

		//최근  게시글 중 top3 목록 
		List<CustomPostVO> postTop3List =customPostService.selectTop3PostList(); 
		List<InfoBoardVO> infoTop3List =InfoBoardService.selectTop3InfoList();
		List<FreeBoardVO> freePostTop3List =freeBoardService.freeSelectTop3PostList();


		if(log.isDebugEnabled()) {
			log.debug("<<Custom게시판-최근 게시글 top3 목록>> : " + postTop3List);
			log.debug("<<infoTop3List게시판-최근 게시글 top3 목록>> : " + infoTop3List);
			log.debug("<<Free게시판-최근 게시글 top3 목록>>:"+freePostTop3List);
		}
		
		//HOT게시물 목록  - 게시판 별 추천수가 5개 이상인 top2 
		List<CustomPostVO> c_hotPostList =customPostService.custom_hotPostTop2(); 
		List<CustomPostVO> f_hotPostList =freeBoardService.free_hotPostTop2(); 
		List<CustomPostVO> i_hotPostList =InfoBoardService.info_hotPostTop2(); 
		
		if(log.isDebugEnabled()) {
			log.debug("<<HOT게시판 목록_customBoard>> : " + c_hotPostList);
			log.debug("<<HOT게시판 목록_freeBoard>> : " + f_hotPostList);
			log.debug("<<HOT게시판 목록_infoBoard>> : " + i_hotPostList);
		}
		
		//오늘의 BEST게시물  - 게시판 중 가장 추천 수가 많은 게시글
		CustomPostVO customPostVO = customLikeService.custom_bestLikePost();
		FreeBoardVO freeBoardVO = freeLikeService.free_bestLikePost();
		InfoBoardVO infoBoardVO = infoLikeService.info_bestLikePost();
		
		if(log.isDebugEnabled()) {
			log.debug("<<BEST게시물_customBoard>> : " + customPostVO);
			log.debug("<<BEST게시물_freeBoard>> : " + freeBoardVO);
			log.debug("<<BEST게시물_infoBoard>> : " + infoBoardVO);
		}
		
		int bestLikeNum = 0;
		int c_likeNum = customPostVO.getLike_cnt();
		int f_likeNum = freeBoardVO.getLike_cnt();
		int i_likeNum = infoBoardVO.getLike_cnt();
		
		int[] likeArr = new int[] {c_likeNum, f_likeNum, i_likeNum};
		
		for(int num : likeArr) {
			if(num > bestLikeNum) { bestLikeNum = num; }
		}
		
		//데이터를 session에 저장
		session.setAttribute("c_hotPostList", c_hotPostList);
		session.setAttribute("f_hotPostList", f_hotPostList);
		session.setAttribute("i_hotPostList", i_hotPostList);
		if(bestLikeNum == c_likeNum) {
			session.setAttribute("bestPost", customPostVO);
		} else if(bestLikeNum == f_likeNum) {
			session.setAttribute("bestPost", freeBoardVO);
		} else if(bestLikeNum == i_likeNum) {
			session.setAttribute("bestPost", infoBoardVO);
		}
		
		model.addAttribute("postTop3List", postTop3List);
		model.addAttribute("infoTop3List", infoTop3List);
		model.addAttribute("freePostTop3List", freePostTop3List);
		model.addAttribute("c_hotPostList", c_hotPostList);
		model.addAttribute("f_hotPostList", f_hotPostList);
		model.addAttribute("i_hotPostList", i_hotPostList);
		if(bestLikeNum == c_likeNum) {
			model.addAttribute("bestPost", customPostVO);
		} else if(bestLikeNum == f_likeNum) {
			model.addAttribute("bestPost", freeBoardVO);
		} else if(bestLikeNum == i_likeNum) {
			model.addAttribute("bestPost", infoBoardVO);
		}

		return "main_board";
	}

	//InfoBoard_이미지 출력
	@RequestMapping("/main/infoPostImageView.do")
	public ModelAndView viewInfoImage(@RequestParam int post_num) {

		log.debug("========================post_num : " + post_num);
		InfoBoardVO infoPost = InfoBoardService.selectBoard(post_num);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");

		//byte[]타입의 데이터
		mav.addObject("imageFile", infoPost.getUploadfile());
		mav.addObject("filename", infoPost.getFilename());

		return mav;
	}

	//FreeBoard_게시글 상세 (게시글VO 하나만 반환)
	@RequestMapping("/main/freeBoardDetail.do")
	public ModelAndView freeBoardDetail(
			@RequestParam(value="post_num") int post_num) {

		if(log.isDebugEnabled()) {
			log.debug("<<사용자 생성 게시글 상세>> post_num:" + post_num);
		}

		//게시글 자바빈 반환
		FreeBoardVO freeBoardVO  = freeBoardService.selectBoard(post_num);

		ModelAndView mav = new ModelAndView();
		// view 이름
		mav.setViewName("freeBoardView");
		mav.addObject("freeBoard", freeBoardVO);
		return mav;
	}

	//FreeBoard_이미지 출력
	@RequestMapping("/main/ImageView.do")
	public ModelAndView freeViewImage(@RequestParam int post_num) {

		FreeBoardVO freeBoardVO  = freeBoardService.selectBoard(post_num);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");

		//byte[]타입의 데이터
		mav.addObject("imageFile",freeBoardVO.getUploadfile());
		mav.addObject("filename",freeBoardVO.getFilename());

		return mav;
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





