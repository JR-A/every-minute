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

import kr.spring.board.customboard.service.CustomFavoriteService;
import kr.spring.board.customboard.service.CustomPostService;
import kr.spring.board.customboard.vo.CustomFavoriteVO;
import kr.spring.board.customboard.vo.CustomLikeVO;
import kr.spring.member.vo.MemberVO;

@Controller
public class CustomFavoriteController {

	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	CustomPostService customPostService;
	
	@Resource
	CustomFavoriteService customFavoriteService;
	
	//좋아요 insert
	@RequestMapping("/customBoard/insertFavorite.do")
	@ResponseBody
	public Map<String,Object> startLike(CustomFavoriteVO customFavoriteVO, HttpSession session){
		
		if(log.isDebugEnabled()) {
			log.debug("<<CustomFavoriteVO>> :"+ customFavoriteVO);
		}

		Map<String,Object> map = new HashMap<String,Object>();
		
		MemberVO user= (MemberVO)session.getAttribute("user");
		
		Map<String,Object> mapAjax = new HashMap<String,Object>();
		
		//로그인 여부 - interceptor에서 처리		
		if(user!=null) {

			//총 추천의 갯수
			map.put("post_num", customFavoriteVO.getPost_num());
			map.put("mem_num", user.getMem_num());
			int myCount =customFavoriteService.favoriteCount_user(map); //회원 중복 추천 여부
			log.debug("<<myCount>>:"+myCount);
			
			if(myCount > 0) {
				mapAjax.put("result", "FavFound"); //이미 추천 했습니다.
			
			} else{
				//즐겨찾기 추가
				customFavoriteVO.setMem_num(user.getMem_num());
				customFavoriteService.insertFavorite(customFavoriteVO);
				mapAjax.put("result", "success");
				
			}
			
		}
		
		return mapAjax;
	}

	//즐겨찾기 취소
	@RequestMapping("/customBoard/deleteFavorite.do")
	@ResponseBody
	public Map<String,Object>  deleteLike(CustomFavoriteVO customFavoriteVO, HttpSession session){
		
		if(log.isDebugEnabled()) {
			log.debug("<<CustomFavoriteVO 즐겨찾기 취소>> :" + customFavoriteVO);
		}

		Map<String,Object> mapAjax = new HashMap<String,Object>();
		
		//게시글 번호 얻기
		int post_num = customFavoriteVO.getPost_num();
		//게시글 즐겨찾기 취소
		customFavoriteService.deleteFavorite(post_num);
		
		mapAjax.put("result", "success");

		return mapAjax;
	}

	//게시글을 즐겨찾기한 수
	@RequestMapping("/customBoard/getFavCount.do")
	@ResponseBody
	public Map<String,Object> getList(@RequestParam("post_num") int post_num){

		Map<String,Object> map = new HashMap<String,Object>();
		map.put("post_num", post_num);

		//총 즐겨찾기 개수
		int fav_cnt = customFavoriteService.selectRowCountFav(map);

		Map<String,Object> mapAjax = new HashMap<String,Object>();
		mapAjax.put("fav_cnt", fav_cnt);

		return mapAjax;

	}

}

