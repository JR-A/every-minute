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

	//게시글 추천 등록
	@RequestMapping("/customBoard/insertPostLike.do")
	@ResponseBody
	public Map<String,Object>  insertPostLike( CustomLikeVO customLikeVO, HttpSession session){
		
		if(log.isDebugEnabled()) {
			log.debug("<<CustomLikeVO 게시글 추천 등록>> :"+customLikeVO);
		}

		Map<String,Object> map = new HashMap<String,Object>();
		
		MemberVO user= (MemberVO)session.getAttribute("user");
		
		Map<String,Object> mapAjax = new HashMap<String,Object>();
		
		//로그인 여부 - interceptor에서 처리		

		//게시글의 추천 개수
		map.put("post_num", customLikeVO.getPost_num());
		map.put("mem_num", user.getMem_num());
		int myCount =customLikeService.likePostCount_user(map); //회원 중복 추천 여부
		int myPost = customLikeService.selectPostWriter(map); //게시글 작성자와 회원번호 동일 여부
		log.debug("<<myCount>>:"+myCount);
		
		if(myCount > 0) {
			mapAjax.put("result", "LikeFound"); //이미 추천 했습니다.
		
		}else if(myPost > 0){ //해당 게시글의 작성자와 로그인 한 회원 번호가 동일하면 1
			mapAjax.put("result", "SameID"); 
			
		}else{
			//추천 등록
			customLikeVO.setMem_num(user.getMem_num());
			customLikeService.insertPostLike(customLikeVO);
			mapAjax.put("result", "success");
			
		}
			
		
		return mapAjax;
	}
	
	//게시글 추천 취소
	@RequestMapping("/customBoard/deletePostLike.do")
	@ResponseBody
	public Map<String,Object>  deletePostLike(CustomLikeVO customLikeVO, HttpSession session){
		
		if(log.isDebugEnabled()) {
			log.debug("<<CustomLikeVO 게시글 추천 취소>> :"+customLikeVO);
		}
		
		Map<String,Object> mapAjax = new HashMap<String,Object>();

		//회원 번호 세팅
		MemberVO user = (MemberVO)session.getAttribute("user");
		//게시글 번호 얻기
		int post_num = customLikeVO.getPost_num();
		//추천한 회원 번호 얻기
		int mem_num = user.getMem_num();
		//게시글 추천 취소
		customLikeService.deletePostLike_mem(post_num, mem_num);
		
		mapAjax.put("result", "success");

		return mapAjax;
	}

	//게시글 추천 개수
	@RequestMapping("/customBoard/getPostLikeCount.do")
	@ResponseBody
	public Map<String,Object> getPostList(@RequestParam("post_num") int post_num){

		Map<String,Object> map = new HashMap<String,Object>();
		map.put("post_num", post_num);

		//총 추천의 갯수
		int like_post_cnt = customLikeService.selectRowCount_postLike(map);

		Map<String,Object> mapAjax = 
				new HashMap<String,Object>();
		mapAjax.put("like_post_cnt", like_post_cnt);

		return mapAjax;

	}

	//댓글 추천 등록
	@RequestMapping("/customBoard/insertCommentLike.do")
	@ResponseBody
	public Map<String,Object>  insertCommentLike( CustomLikeVO customLikeVO, HttpSession session){
		
		if(log.isDebugEnabled()) {
			log.debug("<<CustomLikeVO 게시글 추천 등록>> :"+customLikeVO);
		}

		Map<String,Object> map = new HashMap<String,Object>();
		
		MemberVO user= (MemberVO)session.getAttribute("user");
		
		Map<String,Object> mapAjax = new HashMap<String,Object>();
		
		//로그인 여부 - interceptor에서 처리		

		//댓글에 달린 총 추천 수
		map.put("comment_num", customLikeVO.getComment_num());
		map.put("mem_num", user.getMem_num());
		int myCount =customLikeService.likeCommCount_user(map); //댓글 중복 추천 여부 확인
		int myComm = customLikeService.selectCommWriter(map); //댓글 작성자와 회원번호 동일 여부 확인
		log.debug("<<myCount>>:"+myCount);
		
		if(myCount > 0) {
			mapAjax.put("result", "LikeFound"); //이미 추천 했습니다.
		
		}else if(myComm > 0){ //해당 댓글의 작성자와 로그인 한 회원 번호가 동일하면 1
			mapAjax.put("result", "SameID"); 
			
		}else{
			//댓글 추천 등록
			customLikeVO.setMem_num(user.getMem_num());
			customLikeService.insertCommLike(customLikeVO);
			mapAjax.put("result", "success");
			
		}
			
		
		return mapAjax;
	}
	
	//댓글 추천 취소
	@RequestMapping("/customBoard/deleteCommentLike.do")
	@ResponseBody
	public Map<String,Object>  deleteCommentLike(CustomLikeVO customLikeVO, HttpSession session){
		
		if(log.isDebugEnabled()) {
			log.debug("<<CustomLikeVO 댓글 추천 취소>> :"+customLikeVO);
		}
		
		Map<String,Object> mapAjax = new HashMap<String,Object>();

		//회원 번호 세팅
		MemberVO user = (MemberVO)session.getAttribute("user");
		//댓글 번호 얻기
		int comment_num = customLikeVO.getComment_num();
		//추천한 회원 번호 얻기
		int mem_num = user.getMem_num();
		//댓글 추천 취소
		customLikeService.deleteCommLike_mem(comment_num, mem_num);
		
		mapAjax.put("result", "success");

		return mapAjax;
	}

	//댓글 추천 개수
	@RequestMapping("/customBoard/getCommentLikeCount.do")
	@ResponseBody
	public Map<String,Object> getList(@RequestParam("comment_num") int comment_num){

		Map<String,Object> map = new HashMap<String,Object>();
		map.put("comment_num", comment_num);

		//댓글에 달린 총 추천 수
		int like_comm_cnt = customLikeService.selectRowCount_commLike(map);

		Map<String,Object> mapAjax = 
				new HashMap<String,Object>();
		mapAjax.put("like_comm_cnt", like_comm_cnt);

		return mapAjax;

	}

}
