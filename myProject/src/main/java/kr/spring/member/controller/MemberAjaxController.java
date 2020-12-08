package kr.spring.member.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.board.freeboard.service.FreeBoardService;
import kr.spring.board.freeboard.vo.FreeBoardVO;
import kr.spring.board.freeboard.vo.FreeReplyVO;
import kr.spring.board.infoboard.service.InfoBoardService;
import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.PagingUtil;

@Controller
public class MemberAjaxController {
	private Logger log = Logger.getLogger(this.getClass());
	
	
	@Resource
	private MemberService memberService;
	@Resource//       ┌ BoardService를 주입받음
	InfoBoardService InfoBoardService;
	@Resource
	FreeBoardService freeBoardService;
	
	//아이디 중복체크
	@RequestMapping("/member/confirmId.do")
	@ResponseBody
	public Map<String,String> idCheck(
			             @RequestParam("id") String id){
		
		Map<String,String> map =
				new HashMap<String,String>();
		
		MemberVO member = memberService.selectCheckMember(id);
		if(member!=null) {
			//아이디 중복
			map.put("result", "idDuplicated");
		}else {
			//아이디 미중복
			map.put("result", "idNotFound");
		}
		
		return map;
	}
	
	//이메일 중복체크 
	@RequestMapping("/member/confirmEmail.do")
	@ResponseBody
	public Map<String,String> emailCheck(
			             @RequestParam("email") String email){
		
		Map<String,String> map =
				new HashMap<String,String>();
		
		MemberVO member = memberService.selectCheckEmail(email);
		if(member!=null) {
			//아이디 중복
			map.put("result", "emailDuplicated");
		}else {
			//아이디 미중복
			map.put("result", "emailNotFound");
		}
		
		return map;
	}
	
	//닉네임 중복체크
	@RequestMapping("/member/confirmNickname.do")
	@ResponseBody
	public Map<String,String> nicknameCheck(
			             @RequestParam("nickname") String nickname){
		
		Map<String,String> map =
				new HashMap<String,String>();
		
		MemberVO member = memberService.selectCheckNickname(nickname);
		if(member!=null) {
			//아이디 중복
			map.put("result", "nicknameDuplicated");
		}else {
			//아이디 미중복
			map.put("result", "nicknameNotFound");
		}
		
		return map;
	}
	
	
	//회원수정 사진 업데이트
	@RequestMapping("/member/updateMyPhoto.do")
	@ResponseBody
	public Map<String,String> processProfile(MemberVO memberVO,HttpSession session){
		
		Map<String,String> map = new HashMap<String,String>();
		
		MemberVO user = (MemberVO)session.getAttribute("user");
	
		memberVO.setMem_num(user.getMem_num());
		
		if(user==null) {
			//로그인 되지 않은 경우
			map.put("result", "logout");
		}else {
			//로그인 된 경우
			memberService.updateProfile(memberVO);	
			session.setAttribute("user", memberService.selectCheckMember_num((Integer)user.getMem_num()));
			map.put("result", "success");
		}
		
		return map;
	}
	


	//회원수정 사진 기본이미지로
	@RequestMapping("/member/resetMyPhoto.do")
	@ResponseBody
	public Map<String,String> processResetProfile(MemberVO memberVO,HttpSession session){
		
		
		Map<String,String> map = new HashMap<String,String>();
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		memberVO.setMem_num((Integer)user.getMem_num());
		
		if(user==null) {
			//로그인 되지 않은 경우
			map.put("result", "logout");
		}else {
			//로그인 된 경우
		 	memberService.resetPhoto(memberVO);	
		 	
			session.setAttribute("user", memberService.selectCheckMember_num((Integer)user.getMem_num()));
			map.put("result", "success");
		}
		
		return map;
	}


	//자유게시판에 쓴 댓글 호출   
			@RequestMapping("/member/writedFreeBoardCommentlist.do")
			@ResponseBody
			public Map<String,Object> getList(
					@RequestParam(value="pageNum",defaultValue="1")
					int currentPage,
					HttpSession session){
				//(******주의)댓글 좋아요 처리시만 HttpSession 넣을 것
				if(log.isDebugEnabled()) {
					log.debug("<<currentPage>> : " + currentPage);
				}

				Map<String,Object> map = 
						new HashMap<String,Object>();
				MemberVO memberVO = (MemberVO)session.getAttribute("user");
				map.put("mem_num", memberVO.getMem_num());
				//총 댓글의 갯수
				int count = memberService.myFreeCommentSelectRowCount(map);
				log.debug("<<<<count>>>>>>>>:"+count);
				PagingUtil page = new PagingUtil(currentPage,count,
						10,10,"writedFreeBoardComment.do");
				map.put("start", page.getStartCount());
				map.put("end", page.getEndCount());
				
				List<FreeReplyVO> list = null;
				if(count > 0) {
					list = memberService.selectFreeWritedListReply(map);
				}else {
					list = Collections.emptyList();
				}

				Map<String,Object> mapJson = 
						new HashMap<String,Object>();
				
				mapJson.put("count", count);
				mapJson.put("rowCount", 10);
				mapJson.put("list", list);
				
				return mapJson;
				
		
			}
}










