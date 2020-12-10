package kr.spring.board.customboard.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.board.customboard.service.CustomBlameService;
import kr.spring.board.customboard.service.CustomPostService;
import kr.spring.board.customboard.vo.CustomBlameVO;
import kr.spring.member.vo.MemberVO;

@Controller
public class CustomBlameController {

	private Logger log = Logger.getLogger(this.getClass());

	@Resource
	CustomPostService customPostService;
	
	@Resource
	CustomBlameService customBlameService;
	

	//게시글 신고 접수
	@RequestMapping("/customBoard/insertPostBlame.do")
	@ResponseBody
	public Map<String,Object>  insertPostBlame( CustomBlameVO customBlameVO, HttpSession session){
		
		if(log.isDebugEnabled()) {
			log.debug("<<CustomBlameVO>> 게시글 신고 접수 :"+customBlameVO);
		}

		Map<String,Object> map = new HashMap<String,Object>();
		MemberVO user= (MemberVO)session.getAttribute("user");
		Map<String,Object> mapAjax = new HashMap<String,Object>();
		
		map.put("post_num", customBlameVO.getPost_num()); 
		map.put("mem_num", user.getMem_num());
		
		int myCount =customBlameService.blamePostCount_user(map); //동일 회원 신고접수 중복 여부
		
		if(myCount > 0) {
			mapAjax.put("result", "BlameFound"); //중복 신고 접수 
		
		} else{
			//정상 신고 접수
			customBlameVO.setMem_num(user.getMem_num());
			customBlameService.insertPostBlame(customBlameVO);
			mapAjax.put("result", "success");
		}
		
		return mapAjax;
	}
	
	//댓글 신고 접수
	@RequestMapping("/customBoard/insertCommentBlame.do")
	@ResponseBody
	public Map<String,Object>  insertCommentBlame( CustomBlameVO customBlameVO, HttpSession session){
		
		if(log.isDebugEnabled()) {
			log.debug("<<CustomBlameVO>> 댓글 신고 접수 :"+customBlameVO);
		}

		Map<String,Object> map = new HashMap<String,Object>();
		MemberVO user= (MemberVO)session.getAttribute("user");
		Map<String,Object> mapAjax = new HashMap<String,Object>();
		
		map.put("comment_num", customBlameVO.getComment_num());
		map.put("mem_num", user.getMem_num());
		
		int myCount =customBlameService.blameCommCount_user(map); //동일 회원 신고접수 중복 여부
		
		if(myCount > 0) {
			mapAjax.put("result", "BlameFound"); //중복 신고 접수 
		
		} else{
			//댓글 정상 신고 접수
			customBlameVO.setMem_num(user.getMem_num());
			customBlameService.insertCommBlame(customBlameVO);
			mapAjax.put("result", "success");
		}
		
		return mapAjax;
	}

}
