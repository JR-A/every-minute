package kr.spring.board.infoboard.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.board.infoboard.service.InfoCommentLikeService;
import kr.spring.board.infoboard.service.InfoLikeService;
import kr.spring.board.infoboard.vo.InfoCommentLikeVO;
import kr.spring.board.infoboard.vo.InfoLikeVO;
import kr.spring.member.vo.MemberVO;

@Controller
public class InfoLikeController {
	//로그처리를 위해 생성
	private Logger log = Logger.getLogger(this.getClass());
		
	@Resource//       ┌ InfoLikeService를 주입받음
	InfoLikeService infoLikeService;
	
	@Resource//       ┌ infoCommentLikeService를 주입받음 
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
	public Map<String,Object> postLike(InfoLikeVO infoLikeVO, HttpSession session) {
		if(log.isDebugEnabled()) {
			log.debug("<<InfoLikeVO>> :" + infoLikeVO);
		}
		Map<String,Object> map = new HashMap<String,Object>();
		MemberVO user= (MemberVO)session.getAttribute("user");
		Map<String,Object> mapAjax = new HashMap<String,Object>();
		if(user==null) {
			//로그인 안 됨
			mapAjax.put("result", "logout");
		}else{

			//총 추천의 갯수
			map.put("post_num", infoLikeVO.getPost_num());
			map.put("mem_num", user.getMem_num());
			int myCount = infoLikeService.selectRowCountLikeByMem_num(map);
			int myPost = infoLikeService.selectSameMember(map);
			log.debug("<<myCount>>:"+myCount);
			
			
			if(myCount > 0) {
				
				mapAjax.put("result", "LikeFound");
			
			}else if(myPost > 0){
			
				mapAjax.put("result", "SameID");
			}else{
				//추천	
				infoLikeVO.setMem_num(user.getMem_num());
				infoLikeService.insertLike(infoLikeVO);
				mapAjax.put("result", "success");
				
			}
			
		}
		
		return mapAjax;
		}
	//좋아요 select
		@RequestMapping("/infoBoard/getLikeCount.do")
		@ResponseBody
		public Map<String,Object> getList(
				@RequestParam("post_num") int post_num){

			Map<String,Object> map = 
					new HashMap<String,Object>();
			map.put("post_num", post_num);

			//총 추천의 갯수
			int like_check = infoLikeService.selectRowCountLike(map);

			Map<String,Object> mapAjax = 
					new HashMap<String,Object>();
			mapAjax.put("like_cnt", like_check);

			return mapAjax;
		}
}
