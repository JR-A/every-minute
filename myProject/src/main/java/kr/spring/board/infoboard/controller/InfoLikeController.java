package kr.spring.board.infoboard.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.board.infoboard.service.InfoCommentLikeService;
import kr.spring.board.infoboard.service.InfoLikeService;
import kr.spring.board.infoboard.vo.InfoCommentLikeVO;
import kr.spring.board.infoboard.vo.InfoLikeVO;

@Controller
public class InfoLikeController {
	//로그처리를 위해 생성
	private Logger log = Logger.getLogger(this.getClass());
		
	@Resource//       ┌ InfoLikeService를 주입받음
	InfoLikeService infoLikeService;
	
	@Resource//       ┌ InfoLikeService를 주입받음
	InfoCommentLikeService infoCommentLikeService;
	
	//자바빈(VO) 초기화
	@ModelAttribute//@ModelAttribute는 controller에 있는 메서드 중에서 가장 먼저 실행이 된다.
	public InfoLikeVO initCommand() {//속성명을 정해주지 않음  (소문자)infoBoardVO로 정해짐 (commandName)
		return new InfoLikeVO();//form에서 전달된 데이터를 InfoBoardVO로 보냄 (초기화 작업)
	}
	
	//자바빈(VO) 초기화
		@ModelAttribute//@ModelAttribute는 controller에 있는 메서드 중에서 가장 먼저 실행이 된다.
		public InfoCommentLikeVO initCommand2() {//속성명을 정해주지 않음  (소문자)infoBoardVO로 정해짐 (commandName)
			return new InfoCommentLikeVO();//form에서 전달된 데이터를 InfoBoardVO로 보냄 (초기화 작업)
		}
		
	//게시글 추천하기
	@RequestMapping(value="/infoBoard/view_like.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> postLike(InfoLikeVO infoLikeVO, HttpServletRequest httpRequest) {
		int post_num = Integer.parseInt(httpRequest.getParameter("post_num"));
		int board_mem_num = Integer.parseInt(httpRequest.getParameter("board_mem_num"));
		
		Map<String, Object> map = new HashMap<String, Object>();
		
			log.debug("<<post_num>> : " + post_num);
			log.debug("<<board_mem_num>> : " + board_mem_num);

			infoLikeVO.setPost_num(post_num);
			infoLikeVO.setMem_num(board_mem_num);

			//추천  중복방지 SQL
			InfoLikeVO check = infoLikeService.selectCheckLike(infoLikeVO);
			
			//추천 중복클릭인지 확인하는 영역!
			if (check != null) {
				//추천 중복
				log.debug("<<infoLikeVO_추천 중복>> : " + check);
				map.put("result", "Duplicated");
			} else {
				//추천 미중복시 실행!
				log.debug("<<infoLikeVO_추천 통과>> : " + infoLikeVO);
				infoLikeService.insertLike(infoLikeVO);
				map.put("result", "success");
			}

			return map;
		}
	//댓글 추천하기
		@RequestMapping(value="/infoBoard/comment_like.do", method=RequestMethod.POST)
		@ResponseBody// 자바 객체를 HTTP 응답 본문의 객체로 변환
		public Map<String,Object> commentLike(InfoCommentLikeVO infoCommentLikeVO, HttpServletRequest httpRequest) {
			//댓글의 댓글 번호
			int comment_num = Integer.parseInt(httpRequest.getParameter("comment_num"));
			//댓글 작성자 아이디
			int mem_num = Integer.parseInt(httpRequest.getParameter("mem_num"));
			
			Map<String, Object> map = new HashMap<String, Object>();
			
				log.debug("<<comment_num>> : " + comment_num);
				log.debug("<<mem_num>> : " + mem_num);

				infoCommentLikeVO.setComment_num(comment_num);
				infoCommentLikeVO.setMem_num(mem_num);
				
				//추천  중복방지 SQL
				InfoCommentLikeVO check = infoCommentLikeService.selectCheckLike(infoCommentLikeVO);
				
				//추천 중복클릭인지 확인하는 영역!
				if (check != null) {
					//추천 중복
					log.debug("<<infoCommentLikeVO추천 중복>> : " + check);
					map.put("result", "Duplicated");
				} else {
					//추천 미중복시 실행!
					log.debug("<<infoCommentLikeVO추천 통과>> : " + infoCommentLikeVO);
					infoCommentLikeService.insertLike(infoCommentLikeVO);
					map.put("result", "success");
				}

				return map;
			}
}
