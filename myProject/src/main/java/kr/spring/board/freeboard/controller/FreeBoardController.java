package kr.spring.board.freeboard.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.board.freeboard.service.FreeBoardService;
import kr.spring.board.freeboard.service.FreeReplyService;
import kr.spring.board.freeboard.vo.FreeBoardVO;
import kr.spring.board.freeboard.vo.FreeReplyVO;
import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.PagingUtil;

@Controller
public class FreeBoardController {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	MemberService memberService;
	
	@Resource
	FreeBoardService freeBoardService;
	
	@Resource
	FreeReplyService replyService;

	
	//자바빈(VO) 초기화
	@ModelAttribute
	public FreeBoardVO initCommand() {
		return new FreeBoardVO();
	}
	

	//자바빈(VO) 초기화
	@ModelAttribute
	public FreeReplyVO initCommand2() {
		return new FreeReplyVO();
	
	}
	
	//자유게시판 글 목록
	@RequestMapping("/freeBoard/freeBoardList.do")
	public ModelAndView process (
			@RequestParam(value="pageNum",defaultValue="1")
			int currentPage,
			@RequestParam(value="keyfield",defaultValue="")
			String keyfield,
			@RequestParam(value="keyword",defaultValue="")
			String keyword) {
		
		Map<String,Object> map =
				new HashMap<String,Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		
		//총 글의 갯수 또는 검색된 글의 갯수
		int count = freeBoardService.selectRowCount(map);
		
		if(log.isDebugEnabled()) {
			log.debug("<<count>> :"+count);
		}
		
		PagingUtil page = new PagingUtil(keyfield,keyword,
								currentPage,count,10,10,"freeBoardList.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<FreeBoardVO> list = null;
		if(count > 0) {
			list = freeBoardService.selectList(map);
			
			if(log.isDebugEnabled()) {
				log.debug("<<글 목록>>:"+list);
			}
			
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("freeBoardList");
		mav.addObject("count",count);
		mav.addObject("list",list);
		mav.addObject("pagingHtml",page.getPagingHtml());
		return mav;
	}

//글 등록 폼
		@RequestMapping(value="/freeBoard/freeBoardWrite.do",method=RequestMethod.GET)
		public String form() {
		
			return "freeBoardWrite";
		}
	//글 등록 처리
		@RequestMapping(value="/freeBoard/freeBoardWrite.do",method=RequestMethod.POST)
		public String submit(@Valid FreeBoardVO freeBoardVO,BindingResult result,
							 HttpServletRequest request,
							 HttpSession session,Model model) {
			if(log.isDebugEnabled()) {
				log.debug("<<게시판 글 저장>>:"+freeBoardVO);
				
			}
			//유효성 체크 결과 오류가 있으면 폼 호출
			if(result.hasErrors()) {
				return "freeBoardWrite";
			}
			
	
			//회원 번호 셋팅
			MemberVO vo = (MemberVO)session.getAttribute("user");
			freeBoardVO.setMem_num(vo.getMem_num());
			
			//글쓰기
			freeBoardService.insertBoard(freeBoardVO);
			
			
			
			
			return "redirect:/freeBoard/freeBoardList.do";
			
}		
		
		
		
		//글 상세
		@RequestMapping("/freeBoard/detail.do")
		public ModelAndView process(@RequestParam int post_num,Model model) {
			
			if(log.isDebugEnabled()) {
				log.debug("<<글 상세>>:"+post_num);

			}
			
			
			
			FreeBoardVO freeboard = freeBoardService.selectBoard(post_num);
			
			return new ModelAndView("freeBoardView","freeboard",freeboard);
		}
		//이미지 출력
		@RequestMapping("/freeBoard/imageView.do")
		public ModelAndView viewImage(@RequestParam int post_num) {
			
			FreeBoardVO board = freeBoardService.selectBoard(post_num);
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName("imageView");
										//byte[]타입의 데이터
			mav.addObject("imageFile",board.getUploadfile());
			mav.addObject("filename",board.getFilename());
			
			return mav;
		}
		
		//수정 폼 호출
		@RequestMapping(value="/freeBoard/update.do",
						method=RequestMethod.GET)
		public String form(@RequestParam int post_num,Model model) {
			
			FreeBoardVO freeboardVO = freeBoardService.selectBoard(post_num);
			model.addAttribute("freeboardVO",freeboardVO);
		
			return "freeBoardModify";
		}
		
		//글 수정 처리
		@RequestMapping(value="/freeBoard/update.do",method=RequestMethod.POST)
		public String submitUpdate(@Valid FreeBoardVO freeboardVO,BindingResult result,
						 HttpServletRequest request,HttpSession session,
						 Model model) {
			
			if(log.isDebugEnabled()) {
				log.debug("<<글 정보 수정>>:"+freeboardVO);
			}
			if(result.hasErrors()) {
				return "freeBoardModify";
			}
			
			//회원 번호 셋팅
			MemberVO user = (MemberVO)session.getAttribute("user");
			freeboardVO.setMem_num(user.getMem_num());
			
			//글 수정
			freeBoardService.updateBoard(freeboardVO);
			
			//View에 표시할 메시지
			model.addAttribute("message","글 수정 완료!");
			model.addAttribute("url",request.getContextPath()+"/freeBoard/freeBoardList.do");
			
			//타일스 설정에 아래 뷰 이름이 없으면 단독으로 JSP 호출
			return "common/result";
		}		
		
		//글 삭제 처리
		@RequestMapping("/freeBoard/delete.do")
		public String submitDelete(@RequestParam int post_num,HttpServletRequest request,
								Model model) {
			if(log.isDebugEnabled()) {
				log.debug("<<게시판 글 삭제>>:" + post_num);
			}
			
			//글 삭제
			freeBoardService.deleteBoard(post_num);
			
			model.addAttribute("message","글 삭제 완료!!");
			model.addAttribute("url",request.getContextPath()+"/freeBoard/freeBoardList.do");
			
			return "common/result";
		}

		
		//댓글 부분
		
		
}

		