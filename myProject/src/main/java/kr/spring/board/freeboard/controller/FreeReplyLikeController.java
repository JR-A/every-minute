package kr.spring.board.freeboard.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.board.freeboard.service.FreeBoardService;
import kr.spring.board.freeboard.service.FreeReplyLikeService;
import kr.spring.board.freeboard.vo.FreeReplyLikeVO;
import kr.spring.member.vo.MemberVO;

@Controller
public class FreeReplyLikeController {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	FreeBoardService freeBoardService;
	
	@Resource
	FreeReplyLikeService freeReplyLikeService;
	
	//좋아요 insert
	@RequestMapping("/freeBoard/insertReplyLike.do")
	@ResponseBody
	public Map<String,Object> startLike_R(
			FreeReplyLikeVO freeReplyLikeVO,
			HttpSession session){
		if(log.isDebugEnabled()) {
			log.debug("<<FreeReplyLikeVO>> :"+freeReplyLikeVO);
		}

		Map<String,Object> map = 
				new HashMap<String,Object>();
		MemberVO user=
				(MemberVO)session.getAttribute("user");
		Map<String,Object> mapAjax = 
				new HashMap<String,Object>();
		if(user==null) {
			//로그인 안 됨
			mapAjax.put("result", "logout");
		}else{

			//총 추천의 갯수
			map.put("comment_num", freeReplyLikeVO.getComment_num());
			map.put("mem_num", user.getMem_num());
			int myCount = freeReplyLikeService.selectRowCountLike_RByMem_num(map);
			int myPost = freeReplyLikeService.selectSameMember_R(map);
			log.debug("<<myLikeCount>>:"+myCount);
			
			
			if(myCount > 0) {
				
				mapAjax.put("result", "LikeFound");
			
			}else if(myPost > 0){
			
				mapAjax.put("result", "SameID");
			}else{
				//추천	
				freeReplyLikeVO.setMem_num(user.getMem_num());
				freeReplyLikeService.insertLike_R(freeReplyLikeVO);
				mapAjax.put("result", "success");
				
			}
			
		}
		
		return mapAjax;
	}

	//좋아요 select
	@RequestMapping("/freeBoard/getReplyLikeCount.do")
	@ResponseBody
	public Map<String,Object> getList_R(
			@RequestParam("comment_num") int comment_num){

		Map<String,Object> map = 
				new HashMap<String,Object>();
		map.put("comment_num",comment_num);

		//총 추천의 갯수
		int like_cntR= freeReplyLikeService.selectRowCountLike_R(map);

		Map<String,Object> mapAjax = 
				new HashMap<String,Object>();
		mapAjax.put("replyLike", like_cntR);

		return mapAjax;

	}

}

