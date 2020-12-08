package kr.spring.board.freeboard.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.board.freeboard.service.FreeBoardService;
import kr.spring.board.freeboard.service.FreeLikeService;
import kr.spring.board.freeboard.vo.FreeBoardVO;
import kr.spring.board.freeboard.vo.FreeLikeVO;
import kr.spring.member.vo.MemberVO;

@Controller
public class FreeLikeController {

	private Logger log = Logger.getLogger(this.getClass());

	@Resource
	FreeBoardService freeBoardService;

	@Resource
	FreeLikeService freeLikeService;

	//좋아요 insert
	@RequestMapping("/freeBoard/insertLike.do")
	@ResponseBody
	public Map<String,Object>  startLike(
			FreeLikeVO freeLikeVO,
			HttpSession session){
		if(log.isDebugEnabled()) {
			log.debug("<<FreeLikeVO>> :"+freeLikeVO);
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
			map.put("post_num", freeLikeVO.getPost_num());
			map.put("mem_num", user.getMem_num());
			int myCount = freeLikeService.selectRowCountLikeByMem_num(map);
			int myPost = freeLikeService.selectSameMember(map);
			log.debug("<<myCount>>:"+myCount);
			
			
			if(myCount > 0) {
				
				mapAjax.put("result", "LikeFound");
			
			}else if(myPost > 0){
			
				mapAjax.put("result", "SameID");
			}else{
				//추천	
				freeLikeVO.setMem_num(user.getMem_num());
				freeLikeService.insertLike(freeLikeVO);
				mapAjax.put("result", "success");
				
			}
			
		}
		
		return mapAjax;
	}

	//좋아요 select
	@RequestMapping("/freeBoard/getLikeCount.do")
	@ResponseBody
	public Map<String,Object> getList(
			@RequestParam("post_num") int post_num){

		Map<String,Object> map = 
				new HashMap<String,Object>();
		map.put("post_num", post_num);

		//총 추천의 갯수
		int like_check = freeLikeService.selectRowCountLike(map);

		Map<String,Object> mapAjax = 
				new HashMap<String,Object>();
		mapAjax.put("like_cnt", like_check);

		return mapAjax;

	}

}
