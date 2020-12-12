package kr.spring.member.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import kr.spring.board.customboard.service.CustomBoardService;
import kr.spring.board.customboard.service.CustomPostService;
import kr.spring.board.customboard.vo.CustomBoardVO;
import kr.spring.board.customboard.vo.CustomPostVO;
import kr.spring.board.freeboard.service.FreeBoardService;
import kr.spring.board.freeboard.service.FreeReplyService;
import kr.spring.board.freeboard.vo.FreeBoardVO;
import kr.spring.board.freeboard.vo.FreeReplyVO;
import kr.spring.board.infoboard.service.InfoBoardService;
import kr.spring.board.infoboard.vo.InfoBoardVO;
import kr.spring.mail.service.MailSendServiceImpl;
import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.CustomPagingUtil;
import kr.spring.util.LoginCheckException;
import kr.spring.util.PagingUtil;

@Controller
public class MemberController {

	//프로퍼티
	@Resource
	private MemberService memberService;
	@Resource//       ┌ BoardService를 주입받음
	InfoBoardService InfoBoardService;
	@Resource
	FreeBoardService freeBoardService;	
	@Resource
    private MailSendServiceImpl mss;
	@Resource
	CustomBoardService customBoardService;
	@Resource
	CustomPostService customPostService;
	@Resource
	FreeReplyService freeReplyService;
	
	
	//로그 처리(로그 대상 지정)
	private Logger log = Logger.getLogger(this.getClass());
	
	//자바빈(VO) 초기화후 request에 등록
	@ModelAttribute
	public MemberVO initCommand() {
		return new MemberVO();
	}
	//자바빈 초기화
	@ModelAttribute
	public CustomPostVO initCommand2() {
		return new CustomPostVO();
	}
	
	
	//로그인 폼 - GET방식으로 전송시
	@RequestMapping(value = "/member/memberLogin.do", method = RequestMethod.GET)
	public String formLogin() {
		return "member/memberLogin"; // definition name이 아닌 jsp 호출
	}

	//로그인 처리 - POST방식으로 전송시
	@RequestMapping(value = "/member/memberLogin.do", method = RequestMethod.POST)
	public String loginSub(@Valid MemberVO memberVO, BindingResult result, HttpSession session) {
		
		//로그 처리
		if(log.isDebugEnabled()) {
			log.debug("<<MemberVO>> : " + memberVO);
		}
				
		//탈퇴 or 정지회원 체크
		String auth=memberService.selectAuth(memberVO.getId());
		if(auth!=null) {
			if(auth.equals("1")) {
				result.reject("bannedMember");
				return formLogin();
			}
			if(auth.equals("0")) {
				result.reject("outMember");
				return formLogin();
			}
		}
		
		//로그인 체크(id와 비밀번호 일치 여부 체크)
		try {		
			boolean check = false;
			MemberVO member = memberService.selectCheckMember(memberVO.getId());

				
			if (member != null) {
				//이메일인증이되지않으면 emailNotAuth로가고 메일인증이됬다면 비밀번호 일치 여부 체크
				if(member.getAuthstatus()>0) {
				check = member.isCheckedPasswd(memberVO.getPasswd());
				}else {
					result.reject("notAuthMember");
					return formLogin(); 
				}
			}
			
			if (check) {
				//인증 성공, 로그인 처리
				session.setAttribute("user", member);
				
				return "redirect:/main/main_board.do";
			} else {
				//인증 실패
				throw new LoginCheckException();
			}
		} catch (LoginCheckException e) {
			//인증 실패로 로그인폼 호출
			result.reject("invalidIdOrPassword");

			return formLogin();
		}
	}
	
	//회원가입 폼 - GET방식으로 전송시
	@RequestMapping(value = "/member/memberRegister.do", method = RequestMethod.GET)
	public String formRegister() {
		return "member/memberRegister"; // definition name이 아닌 jsp 호출
	}
	
	//회원가입 처리 - POST방식으로 전송시
	@RequestMapping(value = "/member/memberRegister.do", method = RequestMethod.POST)
	public String submitRegister(@Valid MemberVO memberVO, BindingResult result, HttpSession session) {
		
		
		//전송된 데이터 유효성 체크 -> @Valid 어노테이션으로 체크
		
		//로그 처리
		if(log.isDebugEnabled()) {
			log.debug("<<회원 가입>> : " + memberVO);
		}
		
		/*
		 * BindingResult에 유효성 체크 결과 오류에 대한 내용이 저장돼있으면 form을 호출
		 */
		if(result.hasErrors()) {
			return formRegister();
		}
		

		
		//임의의 authKey 생성 & 이메일 발송
		String authKey = mss.sendAuthMail(memberVO.getEmail());
		memberVO.setAuthKey(authKey);
		//에러가 없으면 회원 가입 처리
		memberService.insertMember(memberVO);
        
        Map<String, String> map = new HashMap<String, String>();
        map.put("email", memberVO.getEmail());
        map.put("authKey", memberVO.getAuthKey());
        System.out.println(map);
        	
        //DB에 authKey 업데이트
        memberService.updateAuthKey(map);
		

		
		return "member/memberRegistercheck";	//메일인증후 로그인가능하다는 페이지 호출
	}
	
	//메일인증 성공시
	 @RequestMapping(value="/member/signUpConfirm.do" ,method=RequestMethod.GET)
	 public String signUpConfirm(@RequestParam("authKey")String authKey,@RequestParam("email")String email){
		 
		 Map<String,String> map= new HashMap<String,String>();
		 map.put("authKey",authKey);
		 map.put("email",email);
		 //email, authKey 가 일치할경우 authStatus 1로 업데이트
	    memberService.updateAuthStatus(map);
	    
	    return "member/emailChecking";
	}
	
	
	//로그아웃 처리
	@RequestMapping("/member/logout.do")
	public String processLogout(HttpSession session) {
		//로그아웃
		session.invalidate(); //세션 무효화
		
		return formLogin();
	}
	

	//마이페이지
	@RequestMapping("/member/myPage.do")
	public String mypage() {
		
		return "memberView";
	}



	//이미지 출력
	@RequestMapping("/member/photoView.do")
	public ModelAndView viewImage(HttpSession session) {
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		MemberVO memberVO = memberService.selectMember(user.getMem_num());
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		mav.addObject("imageFile", memberVO.getPhoto());
		mav.addObject("filename", memberVO.getPhotoname());
		
		return mav;
	}
	
	//게시판 관리
	@RequestMapping("/member/boardManagement.do")
	public String boardMangae() {
		return null;
	}
	
	//비밀번호 변경폼
	@RequestMapping(value="/member/changePasswd.do",method=RequestMethod.GET)
	public String changePasswdForm() {
			return "memberChangePasswd";
		}
		
	//비밀번호 변경
	@RequestMapping(value="/member/changePasswd.do",method=RequestMethod.POST)
	public String changePasswd(MemberVO memberVO,@RequestParam("changePasswd") String changePasswd,BindingResult result,HttpSession session) {
		
		MemberVO member=  (MemberVO)session.getAttribute("user");	
		

		
		if(member.getPasswd().equals(memberVO.getPasswd())) {
			
			if(member.getPasswd().equals(changePasswd)) {
				result.reject("samePass");
				return "memberChangePasswd";
			}
			
			Map<String,String> map = new HashMap<String,String>();
			map.put("passwd", changePasswd);
			map.put("mem_num",String.valueOf(member.getMem_num()));
			memberService.changePasswd(map);
			session.invalidate();
			return "member/passwordChangeComplete";
		}
			result.reject("passwrong");
			return "memberChangePasswd";
		}
	
	//이메일 변경폼
	@RequestMapping(value="/member/changeEmail.do",method=RequestMethod.GET)
	public String changeEmailForm() {
		
		return "memberChangeE";
	}
	
	//이메일 변경
	@RequestMapping(value="/member/changeEmail.do",method=RequestMethod.POST)
	public String changeEmail(MemberVO memberVO,@RequestParam("changeEmail")String changeEmail,HttpSession session,BindingResult result) {
		
		
		MemberVO member=(MemberVO)session.getAttribute("user");
		//현재이메일과 세션의 이메일의 값이 같으면 
		if((memberVO.getEmail()).equals(member.getEmail())) {
			//변경할 이메일로 승인메일 전송
		String authKey = mss.sendChangeMail(changeEmail);
		Map<String,String> map = new HashMap<String,String>();
		map.put("email",member.getEmail());
		map.put("authKey", authKey);
		memberService.updateAuthKey(map);
		return "memberChangeEmailSend";
		}
		
		result.reject("wrongEmail");
		return "memberChangeE";
	}
	
	//이메일변경 메일 인증 성공시
	 @RequestMapping(value="/member/emailChangeConfirm.do" ,method=RequestMethod.GET)
	 public String emailChangeConfirm(@RequestParam("authKey")String authKey,@RequestParam("email")String email){
		 
		 Map<String,String> map= new HashMap<String,String>();
		 map.put("authKey",authKey);
		 map.put("email",email);
		 //email, authKey 가 일치할경우 이메일 업데이트
	     memberService.emailUpdate(map);
	    
	    return "member/emailChangeComplete";
	}
	
	//닉네임 변경폼
	@RequestMapping(value="/member/changeNickname.do",method=RequestMethod.GET)
	public String changeNickForm() {
		return "memberChangeNick";
	
	
	}
	
	//닉네임 변경
	@RequestMapping(value="/member/changeNickname.do",method=RequestMethod.POST)
	public String changeNick(MemberVO memberVO,HttpSession session,BindingResult result) {
		
		
		MemberVO member = (MemberVO)session.getAttribute("user");
		member.setNickname(memberVO.getNickname());
		
		memberService.changeNickname(member);
		session.setAttribute("user",member);
		return "memberView";
	
	
	}
	
	//회원탈퇴폼
	@RequestMapping(value="/member/deleteMember.do",method=RequestMethod.GET)
	public ModelAndView deleteForm(Model model,HttpSession session) {
		
		ModelAndView mav = new ModelAndView();
		
		MemberVO member = new MemberVO(); 
		
		member = (MemberVO)session.getAttribute("user");
		
		
		MemberVO memberVO = memberService.selectMember(member.getMem_num());
		

		
		
		model.addAttribute("memberVO",memberVO);
		
		mav.setViewName("memberDelete");
		
		return mav;
	}


	//회원탈퇴
			@RequestMapping(value="/member/deleteMember.do",method=RequestMethod.POST)
			public String delete(@Valid MemberVO memberVO,BindingResult result,HttpSession session) { 
					
				
				int mem_num = (int)memberVO.getMem_num();
					
				MemberVO member =memberService.selectMember(mem_num);
					
					

					if(mem_num==member.getMem_num()) {
						if(member.getPasswd().equals(memberVO.getPasswd())) {		
							memberService.deleteMember(mem_num);					
							session.invalidate();	
							return "member/deleteComplete";
						
						}
					}
					
					result.reject("invalidPassword");
					return "memberDelete";

			}

			//아이디/비밀번호 찾기폼 
			 @RequestMapping(value="/member/findId.do" ,method=RequestMethod.GET)
			 public String findIdForm(){
				 

			    
			    return "member/memberFindId";
			 }
			
			 //아이디/비밀번호 찾기
			 @RequestMapping(value="/member/findId.do" ,method=RequestMethod.POST)
			 public String findId(@Valid MemberVO memberVO,BindingResult result){
				 	
				 
				 if(memberService.findMem_num(memberVO)!=null) {
				 MemberVO member =memberService.findId(memberVO);
				 mss.sendIdPasswd(memberVO.getEmail(), member.getId(), member.getPasswd());
				 return "member/memberFindcomplete";
				 }
			     
				 result.reject("wrongE");
			    return "member/memberFindId";
			 }


		//내가 쓴 글 
		@RequestMapping("/member/writedBoardlist.do")
		public String writedBoardMain() {
			return "writedBoardLi";
		}
		//자유게시판에 쓴 글
		@RequestMapping("/member/freedBoardWritedlist.do")
		public ModelAndView freeWritedBoardMain(@RequestParam(value="freepageNum", defaultValue="1") int freecurrentPage,
				 @RequestParam(value="freekeyfield", defaultValue="")String freekeyfield,
				 @RequestParam(value="freekeyword", defaultValue="") String freekeyword,
				 HttpSession session) {
			 ModelAndView mav = new ModelAndView();
			 MemberVO member=(MemberVO)session.getAttribute("user");
			 	
			 Map<String,Object> freemap =
						new HashMap<String,Object>();
				freemap.put("keyfield", freekeyfield);
				freemap.put("keyword", freekeyword); 
				freemap.put("mem_num", member.getMem_num());
				
				//자유게시판 글의 갯수 또는 검색된 글의 갯수--------------------------------
				int freecount = memberService.myFreeselectRowCount(freemap); 
				
				if(log.isDebugEnabled()) {
					log.debug("<<count>> :"+freecount);
				}
				PagingUtil freepage = new PagingUtil(freekeyfield,freekeyword,
						freecurrentPage,freecount,10,10,"freedBoardWritedlist.do");
				freemap.put("start", freepage.getStartCount());
				freemap.put("end", freepage.getEndCount());

				List<FreeBoardVO> freelist = null;
				if(freecount > 0) {
				freelist = memberService.myFreeselectList(freemap);
	
				if(log.isDebugEnabled()) {
					log.debug("<<글 목록>>:"+freelist);
				}
					
				}

		

				mav.addObject("freecount",freecount);
				mav.addObject("freelist",freelist);
				mav.addObject("freepagingHtml",freepage.getPagingHtml());
			
				mav.setViewName("freewritedBoardLi");
			return mav;
		}
		//인포게시판에 쓴 글
		@RequestMapping("/member/infoBoardWritedlist.do")
		public ModelAndView infoWritedBoardMain(@RequestParam(value="infopageNum", defaultValue="1") int infocurrentPage, 
				@RequestParam(value="infokeyfield", defaultValue="")String infokeyfield,
				 @RequestParam(value="infokeyword", defaultValue="") String infokeyword,
				 HttpSession session) {
			
			 ModelAndView mav =new ModelAndView();
			 MemberVO member=(MemberVO)session.getAttribute("user");
			
			 Map<String,Object> infomap =
						new HashMap<String,Object>();
			 	infomap.put("keyfield", infokeyfield);
				infomap.put("keyword", infokeyword); 
				infomap.put("mem_num", member.getMem_num());
			
			//인포글의 갯수 또는 검색된 글의 갯수
			int infocount = memberService.myInfoselectRowCount(infomap);
			if (log.isDebugEnabled()) {
				log.debug("<<count>> : " + infocount);
			}		
			
			
			PagingUtil infopage = new PagingUtil(infokeyfield, infokeyword, infocurrentPage, infocount, 10,10,"infoBoardWritedlist.do");
			infomap.put("start", infopage.getStartCount());
			infomap.put("end", infopage.getEndCount());
			
			List<InfoBoardVO> infolist = null;
			if (infocount > 0) {
				//목록을 호출
				infolist = memberService.myInfoselectList(infomap);
				
				if (log.isDebugEnabled()) {
					log.debug("<<글 목록>> : " +  infolist);
				}
			}
			
			/*
			 * - Model과 View를 동시에 설정이 가능하며 컨트롤러는 
			 * ModelAndView객체만 리턴하지만 Model과 View가 모두 리턴 가능
			 * ModelAndView mav = new ModelAndView();
			 */
			
			mav.addObject("infocount",infocount);
			mav.addObject("infolist",infolist);
			mav.addObject("infopagingHtml", infopage.getPagingHtml());	
			//여기까지 인포게시판-----------------------------------------------
		 mav.setViewName("infowritedBoardLi");
		 return mav;
		}
		
		//커스텀게시판에 쓴 글
		@RequestMapping("/member/customBoardWritedlist.do")
		public ModelAndView customWritedBoardMain(//pageNum으로 전달되는 값이 있으면 매핑
				@RequestParam(value="pageNum", defaultValue="1") int currentPage,
				@RequestParam(value="keyfield", defaultValue="") String keyfield,
				@RequestParam(value="keyword", defaultValue="") String keyword,
				@RequestParam(defaultValue="1") int board_num,
				HttpSession session) {
			
			ModelAndView mav =new ModelAndView();
			MemberVO member=(MemberVO)session.getAttribute("user");
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("mem_num", member.getMem_num());
			map.put("keyfield", keyfield); //검색 항목
			map.put("keyword", keyword); //검색 내용
			map.put("board_num", board_num); //게시판 번호

			//총 글의 개수 또는 검색된 글의 개수
			int count = memberService.myCustomselectRowCount(map);
			if(log.isDebugEnabled()) {
				log.debug("<<count>> : " + count);
			}

			//검색할 때 페이징처리
			CustomPagingUtil page = new CustomPagingUtil(keyfield, keyword, currentPage, count, 10, 10,"customBoardWritedlist.do",board_num);
			map.put("start", page.getStartCount());
			map.put("end", page.getEndCount());

			CustomBoardVO boardInfo = customBoardService.selectCustomBoard(board_num); //게시판 정보
			if(log.isDebugEnabled()) {
				log.debug("<<Custom게시판-게시판 정보>> : " + boardInfo);
			}

			List<CustomPostVO> postList = null; //게시글 목록

			if(log.isDebugEnabled()) {
				log.debug("Custom게시판번호>> : " + board_num );
				log.debug("<<Map데이터>> : " + map);
			}

			if(count > 0) {			
				postList = memberService.myCustomselectPostList(map);

				if(log.isDebugEnabled()) {
					log.debug("<<Custom게시판-글 목록>> : " + postList);
				}
			}

			//customBoard.anonymous가 1이면 전체 익명 - 0이면 실명
			

			mav.setViewName("customwritedBoardLi");
			mav.addObject("count", count);
			mav.addObject("boardInfo", boardInfo);
			mav.addObject("postList", postList);
			mav.addObject("pagingHtml", page.getPagingHtml());
			return mav;
		}
		

		//내가단 댓글 페이지
		@RequestMapping("/member/writedCommentList.do")
		public String writedCommentList() {
			return "writedCommentLi";
		}
		//자유게시판에 쓴 댓글 폼 
		@RequestMapping("/member/writedFreeBoardComment.do")
		public ModelAndView writedFreeCommentForm(@RequestParam(value="pageNum",defaultValue="1")
		int currentPage,HttpSession session) {
			ModelAndView mav = new ModelAndView();
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
			mav.addObject("pagingHtml",page.getPagingHtml());
			mav.addObject("page_num", currentPage);
			mav.setViewName("writedFreeCommentLi");
			return mav;
		}
		
		//정보게시판에 쓴 댓글 폼
		@RequestMapping("/member/writedInfoBoardComment.do")
		public ModelAndView writedInfoCommentForm(@RequestParam(value="pageNum",defaultValue="1")
		int currentPage,HttpSession session) {
			ModelAndView mav = new ModelAndView();
			if(log.isDebugEnabled()) {
				log.debug("<<currentPage>> : " + currentPage);
			}

			Map<String,Object> map = 
					new HashMap<String,Object>();
			MemberVO memberVO = (MemberVO)session.getAttribute("user");
			map.put("mem_num", memberVO.getMem_num());
			//총 댓글의 갯수
			int count = memberService.myInfoCommentSelectRowCount(map);
			log.debug("<<<<count>>>>>>>>:"+count);
			PagingUtil page = new PagingUtil(currentPage,count,
					10,10,"writedInfoBoardComment.do");
			mav.addObject("pagingHtml",page.getPagingHtml());
			mav.addObject("page_num", currentPage);
			mav.setViewName("writedInfoCommentLi");
			return mav;
		}
		/*//커스텀 게시판에 쓴 댓글
		@RequestMapping("/member/writedCustomBoardComment.do")
		public String writedCustomComment() {
			return null;
		}*/
}
