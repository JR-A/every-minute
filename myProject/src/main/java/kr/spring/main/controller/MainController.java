package kr.spring.main.controller;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.spring.board.customboard.service.CustomBoardService;
import kr.spring.board.customboard.service.CustomLikeService;
import kr.spring.board.customboard.service.CustomPostService;
import kr.spring.board.customboard.vo.CustomPostVO;
import kr.spring.board.freeboard.service.FreeBoardService;
import kr.spring.board.freeboard.service.FreeLikeService;
import kr.spring.board.freeboard.vo.FreeBoardVO;
import kr.spring.board.infoboard.service.InfoBoardService;
import kr.spring.board.infoboard.service.InfoLikeService;
import kr.spring.board.infoboard.vo.InfoBoardVO;
import kr.spring.member.service.MemberService;
import kr.spring.util.StringUtil;

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
	
		for(CustomPostVO post : postTop3List) {
		   post.setTitle(StringUtil.useNoHtml(post.getTitle()));
		   post.setContent(StringUtil.useBrNoHtml(post.getContent()));
		}
		for(InfoBoardVO post : infoTop3List) {
		   post.setTitle(StringUtil.useNoHtml(post.getTitle()));
		   post.setContent(StringUtil.useBrNoHtml(post.getContent()));
		}
		for(FreeBoardVO post : freePostTop3List) {
		   post.setTitle(StringUtil.useNoHtml(post.getTitle()));
		   post.setContent(StringUtil.useBrNoHtml(post.getContent()));
		}


		if(log.isDebugEnabled()) {
			log.debug("<<Custom게시판-최근 게시글 top3 목록>> : " + postTop3List);
			log.debug("<<infoTop3List게시판-최근 게시글 top3 목록>> : " + infoTop3List);
			log.debug("<<Free게시판-최근 게시글 top3 목록>>:"+freePostTop3List);
		}
		
		//HOT게시물 목록  - 게시판 별 추천수가 5개 이상인 top2 
		List<CustomPostVO> c_hotPostList =customPostService.custom_hotPostTop2(); 
		List<FreeBoardVO> f_hotPostList =freeBoardService.free_hotPostTop2(); 
		List<InfoBoardVO> i_hotPostList =InfoBoardService.info_hotPostTop2(); 
		
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
		int c_likeNum = 0, f_likeNum = 0, i_likeNum = 0;
		
        if(customPostVO!=null) { c_likeNum = customPostVO.getLike_cnt(); }      
        if(freeBoardVO!=null) { f_likeNum = freeBoardVO.getLike_cnt(); }
        if(infoBoardVO!=null) { i_likeNum = infoBoardVO.getLike_cnt(); }


		int[] likeArr = new int[] {c_likeNum, f_likeNum, i_likeNum};

		if(log.isDebugEnabled()) {
			log.debug("<<BEST게시물_게시판 별 최고 추천 수>> : " + Arrays.toString(likeArr));
		}
		
		for(int num : likeArr) {
			if(num > bestLikeNum) { 
				bestLikeNum = num; 
			}
		}

		//데이터를 session에 저장
		session.setAttribute("c_hotPostList", c_hotPostList);
		session.setAttribute("f_hotPostList", f_hotPostList);
		session.setAttribute("i_hotPostList", i_hotPostList);
		if(bestLikeNum == c_likeNum) {
			session.setAttribute("best_customPostVO", customPostVO);
		} else if(bestLikeNum == f_likeNum) {
			session.setAttribute("best_freeBoardVO", freeBoardVO);
		} else if(bestLikeNum == i_likeNum) {
			session.setAttribute("best_infoBoardVO", infoBoardVO);
		} else {
			session.setAttribute("best_customPostVO", null);
			session.setAttribute("best_freeBoardVO", null);
			session.setAttribute("best_infoBoardVO", null);
		}

		model.addAttribute("postTop3List", postTop3List);
		model.addAttribute("infoTop3List", infoTop3List);
		model.addAttribute("freePostTop3List", freePostTop3List);
		

		return "main_board";
	}
	
}





