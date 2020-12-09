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
	

	//좋아요 insert
	@RequestMapping("/customBoard/insertPostBlame.do")
	@ResponseBody
	public Map<String,Object>  startBlame(
			CustomBlameVO customBlameVO,
			HttpSession session){
		
		if(log.isDebugEnabled()) {
			log.debug("<<CustomBlameVO>> :"+customBlameVO);
		}

		Map<String,Object> map = new HashMap<String,Object>();
		
		MemberVO user= (MemberVO)session.getAttribute("user");
		
		Map<String,Object> mapAjax = new HashMap<String,Object>();
		
		//로그인 여부 - interceptor에서 처리		
		if(user!=null) {

			//총 추천의 갯수
			map.put("post_num", customBlameVO.getPost_num());
			map.put("mem_num", user.getMem_num());
			int myCount =customBlameService.blameCount_user(map); //회원 중복 추천 여부
			//int myPost = customBlameService.selectPostWriter(map); //게시글 작성자와 회원번호 동일 여부
			log.debug("<<myCount>>:"+myCount);
			
			if(myCount > 0) {
				mapAjax.put("result", "BlameFound"); //이미 추천 했습니다.
			
			} else{
				//추천 등록
				customBlameVO.setMem_num(user.getMem_num());
				customBlameService.insertPostBlame(customBlameVO);
				mapAjax.put("result", "success");
			}
			
		}
		
		return mapAjax;
	}

}
