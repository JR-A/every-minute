package kr.spring.board.customboard.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.board.customboard.service.CustomBoardService;
import kr.spring.board.customboard.service.CustomLikeService;
import kr.spring.board.customboard.service.CustomPostService;
import kr.spring.board.customboard.vo.CustomLikeVO;
import kr.spring.member.vo.MemberVO;

@Controller
public class CustomLikeController {

	private Logger log = Logger.getLogger(this.getClass());

	@Resource
	CustomBoardService customBoardService;
	
	@Resource
	CustomPostService customPostService;
	
	@Resource
	CustomLikeService customLikeService;

	//좋아요 insert
	@RequestMapping("/customBoard/insertLike.do")
	@ResponseBody
	public Map<String,Object>  insertLike( CustomLikeVO customLikeVO, HttpSession session){
		
		if(log.isDebugEnabled()) {
			log.debug("<<CustomLikeVO 추천 등록>> :"+customLikeVO);
		}

		Map<String,Object> map = new HashMap<String,Object>();
		
		MemberVO user= (MemberVO)session.getAttribute("user");
		
		Map<String,Object> mapAjax = new HashMap<String,Object>();
		
		//로그인 여부 - interceptor에서 처리		
		if(user!=null) {

			//총 추천의 갯수
			map.put("post_num", customLikeVO.getPost_num());
			map.put("mem_num", user.getMem_num());
			int myCount =customLikeService.likeCount_user(map); //회원 중복 추천 여부
			int myPost = customLikeService.selectPostWriter(map); //게시글 작성자와 회원번호 동일 여부
			log.debug("<<myCount>>:"+myCount);
			
			if(myCount > 0) {
				mapAjax.put("result", "LikeFound"); //이미 추천 했습니다.
			
			}else if(myPost > 0){ //해당 게시글의 작성자와 로그인 한 회원 번호가 동일하면 1
				mapAjax.put("result", "SameID"); 
				
			}else{
				//추천 등록
				customLikeVO.setMem_num(user.getMem_num());
				customLikeService.insertLike(customLikeVO);
				mapAjax.put("result", "success");
				
			}
			
		}
		
		return mapAjax;
	}
	
	//좋아요 취소
	@RequestMapping("/customBoard/deleteLike.do")
	@ResponseBody
	public Map<String,Object>  deleteLike(CustomLikeVO customLikeVO, HttpSession session){
		
		if(log.isDebugEnabled()) {
			log.debug("<<CustomLikeVO 추천 취소>> :"+customLikeVO);
		}

		Map<String,Object> mapAjax = new HashMap<String,Object>();
		
		//게시글 번호 얻기
		int post_num = customLikeVO.getPost_num();
		//게시글 추천 취소
		customLikeService.delete_like(post_num);
		
		mapAjax.put("result", "success");

		return mapAjax;
	}

	//좋아요 select
	@RequestMapping("/customBoard/getLikeCount.do")
	@ResponseBody
	public Map<String,Object> getList(@RequestParam("post_num") int post_num){

		Map<String,Object> map = new HashMap<String,Object>();
		map.put("post_num", post_num);

		//총 추천의 갯수
		int like_cnt = customLikeService.selectRowCountLike(map);

		Map<String,Object> mapAjax = 
				new HashMap<String,Object>();
		mapAjax.put("like_cnt", like_cnt);

		return mapAjax;

	}

}
