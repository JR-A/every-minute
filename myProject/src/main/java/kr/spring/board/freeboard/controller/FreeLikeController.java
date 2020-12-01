/*package kr.spring.board.freeboard.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.spring.board.freeboard.service.FreeBoardService;
import kr.spring.board.freeboard.service.FreeLikeService;
import kr.spring.board.freeboard.vo.FreeBoardVO;
import kr.spring.board.freeboard.vo.FreeLikeVO;
import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;

@Controller
public class FreeLikeController {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	MemberService memberService;
	
	@Resource
	FreeBoardService freeBoardService;
	
	@Resource
	FreeLikeService freeLikeService;

	
	//자바빈(VO) 초기화
	@ModelAttribute
	public FreeBoardVO initCommand() {
		return new FreeBoardVO();
	}
	
	//자바빈(VO) 초기화
	@ModelAttribute
	public FreeLikeVO initCommand2() {
		return new FreeLikeVO();
	}
	
	//좋아요 호출
	@ResponseBody
	@RequestMapping(value="/freeBoard/freeLikeCount.do",method=RequestMethod.POST)
	public String like_count(
	        @RequestBody String filterJSON,
	        HttpServletResponse response,
	        HttpServletRequest request,BindingResult result,
	        ModelMap model,FreeLikeVO freeLikeVO,HttpSession session) {
	    
		if(log.isDebugEnabled()) {
			log.debug("<<좋아요>>:"+freeLikeVO);
			
		}
		//유효성 체크 결과 오류가 있으면 폼 호출
		if(result.hasErrors()) {
			return "freeBoardView";
		}
		
		
		//회원 번호 셋팅
		MemberVO user = (MemberVO)session.getAttribute("user");
		freeLikeVO.setMem_num(user.getMem_num());
		
	    
	    JSONObject obj = new JSONObject();
	    obj.put("like_check",freeLikeVO.getLike_check());
	    obj.put("like_num", freeLikeVO.getLike_num());
	    obj.put("mem_num", freeLikeVO.getMem_num());
	    obj.put("post_num", freeLikeVO.getPost_num());
	    
		response.setContentType("application/x-json; charset=UTF-8");
	    	return"freeBoardView";
	}


	}
	 
	 

*/
