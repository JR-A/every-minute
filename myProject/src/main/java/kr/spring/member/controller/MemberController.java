package kr.spring.member.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.LoginCheckException;

@Controller
public class MemberController {

	//프로퍼티
	@Resource
	private MemberService memberService;

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
				
		/*
		 * BindingResult에 유효성 체크 결과 오류에 대한 내용이 저장돼있으면 form을 호출
		 * id와 passwd 필드만 체크!
		 */
		if(result.hasFieldErrors("id") || result.hasFieldErrors("passwd")) {
			return formLogin();
		}
		
		//로그인 체크(id와 비밀번호 일치 여부 체크)
		try {
			
			boolean check = false;
			MemberVO member = memberService.selectCheckMember(memberVO.getId());

			if (member != null) {
				//비밀번호 일치 여부 체크
				check = member.isCheckedPasswd(memberVO.getPasswd());
			}

			if (check) {
				//인증 성공, 로그인 처리
				session.setAttribute("user", member);
				session.setAttribute("mem_num", member.getMem_num());
				session.setAttribute("id", member.getId());
				
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
		
		//에러가 없으면 회원 가입 처리
		memberService.insertMember(memberVO);
		//세션에 정보 저장
		session.setAttribute("user", memberVO);
		session.setAttribute("mem_num", memberVO.getMem_num());
		session.setAttribute("id", memberVO.getId());
		
		return "main_board";	//메인화면으로 바로이동
	}
	
	//로그아웃 처리
	@RequestMapping("/member/logout.do")
	public String processLogout(HttpSession session) {
		//로그아웃
		session.invalidate(); //세션 무효화
		
		return formLogin();
	}
	
}
