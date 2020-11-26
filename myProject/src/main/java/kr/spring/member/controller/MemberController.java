package kr.spring.member.controller;

import java.util.HashMap;
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
import org.springframework.web.servlet.ModelAndView;

import kr.spring.mail.service.MailSendServiceImpl;
import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.LoginCheckException;

@Controller
public class MemberController {

	//프로퍼티
	@Resource
	private MemberService memberService;
    @Resource
    private MailSendServiceImpl mss;
	//로그 처리(로그 대상 지정)
	private Logger log = Logger.getLogger(this.getClass());

	//자바빈(VO) 초기화후 request에 등록
	@ModelAttribute
	public MemberVO initCommand() {
		return new MemberVO();
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
					return "member/emailNotAuth"; 
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
	
	//비밀번호 변경
	@RequestMapping("/member/changePasswd.do")
	public String changePass() {
		return null;
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
	public String changeNick(MemberVO memberVO,HttpSession session) {
		
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
}
