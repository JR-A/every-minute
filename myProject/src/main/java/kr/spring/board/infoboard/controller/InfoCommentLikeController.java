package kr.spring.board.infoboard.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.board.infoboard.service.InfoBoardService;
import kr.spring.board.infoboard.service.InfoCommentLikeService;
import kr.spring.board.infoboard.vo.InfoCommentLikeVO;
import kr.spring.member.vo.MemberVO;

@Controller
public class InfoCommentLikeController {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	InfoBoardService infoBoardService;
	
	@Resource
	InfoCommentLikeService infoCommentLikeService;
	
	//좋아요 insert
	@RequestMapping("/infoBoard/insertReplyLike.do")
	@ResponseBody
	public Map<String,Object> startLike_R(InfoCommentLikeVO infoCommentLikeVO, HttpSession session){
		if(log.isDebugEnabled()) {
			log.debug("<<InfoCommentLikeVO>> :"+ infoCommentLikeVO);
		}

		Map<String,Object> map = new HashMap<String,Object>();
		MemberVO user=(MemberVO)session.getAttribute("user");
		Map<String,Object> mapAjax = new HashMap<String,Object>();
		if(user==null) {
			//로그인 안 됨
			mapAjax.put("result", "logout");
		}else{

			//총 추천의 갯수
			map.put("comment_num", infoCommentLikeVO.getComment_num());
			map.put("mem_num", user.getMem_num());
			int myCount = infoCommentLikeService.selectRowCountLike_RByMem_num(map);
			int myPost = infoCommentLikeService.selectSameMember_R(map);
			log.debug("<<myLikeCount>>:"+myCount);
			
			
			if(myCount > 0) {
				mapAjax.put("result", "LikeFound");
			
			}else if(myPost > 0){
			
				mapAjax.put("result", "SameID");
			}else{
				//추천	
				infoCommentLikeVO.setMem_num(user.getMem_num());
				infoCommentLikeService.insertLike_R(infoCommentLikeVO);
				mapAjax.put("result", "success");
				
			}
			
		}
		
		return mapAjax;
	}
	
	//좋아요 select
	@RequestMapping("/infoBoard/getReplyLikeCount.do")
	@ResponseBody
	public Map<String,Object> getList_R(@RequestParam("comment_num") int comment_num){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("comment_num",comment_num);
		
		//총 추천의 갯수
		int like_cntR= infoCommentLikeService.selectRowCountLike_R(map);
		log.debug("<<like_cntR 댓글 좋아요 갯수>> :"+ like_cntR);
		Map<String,Object> mapAjax = new HashMap<String,Object>();
		mapAjax.put("replyLike", like_cntR);

		return mapAjax;
		}
	}

