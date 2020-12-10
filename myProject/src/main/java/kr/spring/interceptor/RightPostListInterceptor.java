/*package kr.spring.interceptor;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

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

public class RightPostListInterceptor extends HandlerInterceptorAdapter{

	private Logger log = Logger.getLogger(this.getClass());

	@Resource
	FreeBoardService freeBoardService;
	@Resource
	FreeLikeService freeLikeService;
	@Resource
	InfoBoardService InfoBoardService;
	@Resource
	InfoLikeService infoLikeService;
	@Resource
	CustomPostService customPostService;
	@Resource
	CustomLikeService customLikeService;
	@Override
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
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

		request.setAttribute("c_hotPostList", c_hotPostList);
		request.setAttribute("f_hotPostList", f_hotPostList);
		request.setAttribute("i_hotPostList", i_hotPostList);
		
		if(bestLikeNum == c_likeNum) {
			request.setAttribute("bestPost", customPostVO);
		} else if(bestLikeNum == f_likeNum) {
			request.setAttribute("bestPost", freeBoardVO);
		} else if(bestLikeNum == i_likeNum) {
			request.setAttribute("bestPost", infoBoardVO);
		}
		
		
		//Hot게시물, Best게시물 정보를 List 타입으로 받아서 request에 저장
		
		return true;
	}
	
	
}



























*/