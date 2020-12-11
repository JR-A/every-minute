package kr.spring.board.infoboard.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.board.infoboard.service.InfoCommentLikeService;
import kr.spring.board.infoboard.service.InfoReplyService;
import kr.spring.board.infoboard.vo.InfoReplyVO;
import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.PagingUtil;

@Controller
public class InfoReplyController {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	InfoReplyService infoReplyService;
	@Resource
	MemberService memberService;
	@Resource
	InfoCommentLikeService infoCommentLikeService;

	private int rowCount = 10;

	private int pageCount = 10;

	//댓글 등록
	@RequestMapping("/infoBoard/writeReply.do")
	@ResponseBody
	public Map<String,String> writeReply(InfoReplyVO boardReplyVO, HttpSession session, HttpServletRequest request){

		if(log.isDebugEnabled()) {
			log.debug("<<InfoReplyVO>> : " + boardReplyVO);
		}

		Map<String,String> map = new HashMap<String,String>();

		MemberVO user = (MemberVO)session.getAttribute("user");
		
		if(user==null) {
			//로그인 안 됨
			map.put("result", "logout");
		}else {
			
			//댓글 등록
			infoReplyService.insertReply(boardReplyVO);
			map.put("result", "success");
		}
  
		return map;
	}  
	//댓글 목록
		@RequestMapping("/infoBoard/listReply.do")
		@ResponseBody
		public Map<String,Object> getList(@RequestParam(value="pageNum",defaultValue="1")int currentPage, @RequestParam("post_num") int post_num, HttpSession session){
			if(log.isDebugEnabled()) {
				log.debug("<<currentPage>> : " + currentPage);
				log.debug("<<post_num>> : " + post_num);
			}

			Map<String,Object> map = new HashMap<String,Object>();
			map.put("post_num", post_num);

			//총 댓글의 갯수
			int count = infoReplyService.selectRowCountReply(map);

			PagingUtil page = new PagingUtil(currentPage,count,rowCount,pageCount,null);
			map.put("start", page.getStartCount());
			map.put("end", page.getEndCount());
			
			MemberVO memberVO = (MemberVO)session.getAttribute("user");
			if(memberVO!=null) {
				map.put("mem_num", memberVO.getMem_num());
			}else {
				map.put("mem_num", 0); 
			}
			
			
			List<InfoReplyVO> list = null;
			if(count > 0) {
				list = infoReplyService.selectListReply(map);
			
			}else {
				list = Collections.emptyList();
			}

			Map<String,Object> mapJson = 
					new HashMap<String,Object>();
			log.debug("<<list>> : " + list);
			mapJson.put("count", count);
			mapJson.put("rowCount", rowCount);
			mapJson.put("list", list);
			log.debug("<<mapJson>> : " + mapJson);
			return mapJson;
		}
		//댓글 삭제
		@RequestMapping("/infoBoard/deleteReply.do")
		@ResponseBody
		public Map<String,String> deleteReply(
				@RequestParam("comment_num") int comment_num,
				@RequestParam("mem_num") int mem_num,
				HttpSession session){
	   
			if(log.isDebugEnabled()) {
				log.debug("<<comment_num>> : " + comment_num);
				log.debug("<<mem_num>> : " + mem_num);
			}

			Map<String,String> map = 
					new HashMap<String,String>();

			MemberVO user = 
					(MemberVO)session.getAttribute("user");
			if(log.isDebugEnabled()) {
				log.debug("<<user.getMem_num()>> : " + user.getMem_num());
			}
			if(user==null) {
				//로그인이 되어있지 않음
				map.put("result", "logout");
			}else if(user!=null && user.getMem_num()==mem_num) {
				//로그인 되어 있고 로그인한 아이디와 작성자 아이디 일치
				infoReplyService.deleteReply(comment_num);
				
				map.put("result", "success");
			}else {
				//로그인 아이디와 작성자 아이디 불일치
				map.put("result", "wrongAccess");
			}		
			return map;
		}
		//댓글 수정
		@RequestMapping("/infoBoard/updateReply.do")
		@ResponseBody
		public Map<String,String> modifyReply(
				InfoReplyVO boardReplyVO,
				HttpSession session,
				HttpServletRequest request){

			if(log.isDebugEnabled()) {
				log.debug("<<boardReplyVO>> : " + 
						boardReplyVO);
			}

			Map<String,String> map = 
					new HashMap<String,String>();

			MemberVO user = 
					(MemberVO)session.getAttribute("user");
			if(user==null) {
				//로그인이 안 되어있는 경우
				map.put("result", "logout");
			}else if(user!=null && user.getMem_num()==boardReplyVO.getMem_num()){
				//로그인 회원 번호와 작성자 회원번호 일치
				
				//댓글 수정
				infoReplyService.updateReply(boardReplyVO);
				map.put("result", "success");
			}else {
				//로그인 아이디와 작성자 아이디 불일치
				map.put("result", "wrongAccess");
			}

			return map;
		}
		
		@RequestMapping("/infoBoard/replayImageView.do")
		public ModelAndView viewImage(@RequestParam int mem_num) {
			
			MemberVO vo = memberService.selectMember(mem_num);
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName("imageView");
										//byte[]타입의 데이터
			mav.addObject("imageFile",vo.getPhoto());
			mav.addObject("filename",vo.getPhotoname());
			
			return mav;
		}
}
