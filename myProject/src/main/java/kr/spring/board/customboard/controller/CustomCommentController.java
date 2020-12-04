package kr.spring.board.customboard.controller;

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

import kr.spring.board.customboard.service.CustomCommentService;
import kr.spring.board.customboard.service.CustomPostService;
import kr.spring.board.customboard.vo.CustomCommentVO;
import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.PagingUtil;

@Controller
public class CustomCommentController {
	
	private Logger log = Logger.getLogger(this.getClass());
		
		@Resource
		CustomCommentService customCommentService;
		
		@Resource
		CustomPostService customPostService;
		
		@Resource
		MemberService memberService;
	
		private int rowCount = 10;
		private int pageCount = 10;
	
		//댓글 등록
		@RequestMapping("/customBoard/writeComment.do")
		@ResponseBody
		public Map<String,String> writeComment(
				CustomCommentVO customCommentVO,
				HttpSession session,
				HttpServletRequest request){
	
			if(log.isDebugEnabled()) {
				log.debug("<<CustomCommentVO>> : " +  customCommentVO);
			}
	
			Map<String,String> map = new HashMap<String,String>();
	
			MemberVO user =  (MemberVO)session.getAttribute("user");
			
			if(user==null) {
				//로그인 안 됨
				map.put("result", "logout");
			}else {
				//댓글 등록
				customCommentService.insertComment(customCommentVO);
				map.put("result", "success");
			}
	
			return map;
		}
		//댓글 목록
		@RequestMapping("/customBoard/listComment.do")
		@ResponseBody
		public Map<String,Object> getList(
				@RequestParam(value="pageNum",defaultValue="1") int currentPage,
				@RequestParam("post_num") int post_num,
				HttpSession session) {
			//(******주의)댓글 좋아요 처리시만 HttpSession 넣을 것
			if(log.isDebugEnabled()) {
				log.debug("<<currentPage>> : " + currentPage);
				log.debug("<<post_num>> : " + post_num);
			}

			Map<String,Object> map = new HashMap<String,Object>();
			map.put("post_num", post_num);

			//총 댓글의 갯수
			int count = customCommentService.selectRowCountComment(map);
			log.debug("<<<<count>>>>>>>>:"+count);
			PagingUtil page = new PagingUtil(currentPage,count, rowCount,pageCount,null);
			map.put("start", page.getStartCount());
			map.put("end", page.getEndCount());
			MemberVO memberVO = (MemberVO)session.getAttribute("user");
			if(memberVO!=null) {
				map.put("mem_num", memberVO.getMem_num());
			}else {
				map.put("mem_num", 0); 
			}
			List<CustomCommentVO> list = null;
			if(count > 0) {
				list = customCommentService.selectListComment(map);
			}else {
				list = Collections.emptyList();
			}

			Map<String,Object> mapJson = new HashMap<String,Object>();
			
			mapJson.put("count", count);
			mapJson.put("rowCount", rowCount);
			mapJson.put("list", list);

			return mapJson;
			
	
		}
		//댓글 삭제
		@RequestMapping("/customBoard/deleteComment.do")
		@ResponseBody
		public Map<String,String> deleteComment(
				@RequestParam("comment_num") int comment_num,
				@RequestParam("mem_num") int mem_num,
				HttpSession session){
	   
			if(log.isDebugEnabled()) {
				log.debug("<<comment_num>> : " + comment_num);
				log.debug("<<mem_num>> : " + mem_num);
			}

			Map<String,String> map = new HashMap<String,String>();

			MemberVO user = (MemberVO)session.getAttribute("user");
			if(user==null) {
				//로그인이 되어있지 않음
				map.put("result", "logout");
			}else if(user!=null && user.getMem_num()==mem_num) {
				//로그인 되어 있고 로그인한 아이디와 작성자 아이디 일치
				customCommentService.deleteComment(comment_num);
				map.put("result", "success");
			}else {
				//로그인 아이디와 작성자 아이디 불일치
				map.put("result", "wrongAccess");
			}		
			return map;
		}
		//댓글 수정
		@RequestMapping("/customBoard/updateComment.do")
		@ResponseBody
		public Map<String,String> modifyComment(
				CustomCommentVO customCommentVO,
				HttpSession session,
				HttpServletRequest request){

			if(log.isDebugEnabled()) {
				log.debug("<<CustomCommentVO>> : " + customCommentVO);
			}

			Map<String,String> map = new HashMap<String,String>();

			MemberVO user = (MemberVO)session.getAttribute("user");
			if(user==null) {
				//로그인이 안 되어있는 경우
				map.put("result", "logout");
			}else if(user!=null && user.getMem_num()==customCommentVO.getMem_num()){
				//로그인 회원 번호와 작성자 회원번호 일치
				
				//댓글 수정
				customCommentService.updateComment(customCommentVO);
				map.put("result", "success");
			}else {
				//로그인 아이디와 작성자 아이디 불일치
				map.put("result", "wrongAccess");
			}

			return map;
		}
		
		@RequestMapping("/customBoard/commentImageView.do")
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
