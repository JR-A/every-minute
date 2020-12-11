package kr.spring.board.freeboard.controller;

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
import kr.spring.board.freeboard.service.FreeBlameService;
import kr.spring.board.freeboard.vo.FreeBlameVO;
import kr.spring.member.vo.MemberVO;

@Controller
public class FreeBlameController {
	private Logger log = Logger.getLogger(this.getClass());

	
	@Resource
	FreeBlameService freeBlameService;
	

	//게시판 신고하기
	@RequestMapping("/freeBoard/insertPostBlame.do")
	@ResponseBody
	public Map<String,Object>  startBlame(
			FreeBlameVO freeBlameVO,
			HttpSession session){
		
		if(log.isDebugEnabled()) {
			log.debug("<<FreeBlameVO>> :"+freeBlameVO);
		}

		Map<String,Object> map = new HashMap<String,Object>();
		
		MemberVO user= (MemberVO)session.getAttribute("user");
		
		Map<String,Object> mapAjax = new HashMap<String,Object>();
		
		//로그인 여부 - interceptor에서 처리		
		if(user!=null) {

			
			map.put("post_num", freeBlameVO.getPost_num());
			map.put("mem_num", user.getMem_num());
			int myCountB =freeBlameService.blameCount_user(map); 
			log.debug("<<myCount>>:"+myCountB);
			
			if(myCountB > 0) {
				mapAjax.put("result", "BlameFound"); //이미 추천 했습니다.
			
			} else{
		
				freeBlameVO.setMem_num(user.getMem_num());
				freeBlameService.insertPostBlame(freeBlameVO);
				mapAjax.put("result", "success");
			}
			
		}
		
		return mapAjax;
	}
	//댓글 신고 접수
	@RequestMapping("/freeBoard/insertCommentBlame.do")
	@ResponseBody
	public Map<String,Object>  insertCommentBlame( FreeBlameVO freeBlameVO, HttpSession session){
		
		if(log.isDebugEnabled()) {
			log.debug("<<freeBlameVO>> 댓글 신고 접수 :"+freeBlameVO);
		}

		Map<String,Object> map = new HashMap<String,Object>();
		MemberVO user= (MemberVO)session.getAttribute("user");
		Map<String,Object> mapAjax = new HashMap<String,Object>();
		
		map.put("comment_num", freeBlameVO.getComment_num());
		map.put("mem_num", user.getMem_num());
		
		int myCount =freeBlameService.blameCommCount_user(map); //동일 회원 신고접수 중복 여부
		
		if(myCount > 0) {
			mapAjax.put("result", "BlameFound"); //중복 신고 접수 
		
		} else{
			//댓글 정상 신고 접수
			freeBlameVO.setMem_num(user.getMem_num());
			freeBlameService.insertCommentBlame(freeBlameVO);
			mapAjax.put("result", "success");
		}
		
		return mapAjax;
	}
}
