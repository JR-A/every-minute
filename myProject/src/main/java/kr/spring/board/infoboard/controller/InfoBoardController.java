package kr.spring.board.infoboard.controller;

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

import kr.spring.board.infoboard.service.InfoBoardService;
import kr.spring.board.infoboard.vo.InfoBoardVO;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.PagingUtil;

@Controller
public class InfoBoardController {
	
	//로그처리를 위해 생성
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource//       ┌ BoardService를 주입받음
	InfoBoardService InfoBoardService;
	
	//자바빈(VO) 초기화
	@ModelAttribute//@ModelAttribute는 controller에 있는 메서드 중에서 가장 먼저 실행이 된다.
	public InfoBoardVO initCommand() {//속성명을 정해주지 않음  (소문자)infoBoardVO로 정해짐 (commandName)
		return new InfoBoardVO();//form에서 전달된 데이터를 InfoBoardVO로 보냄 (초기화 작업)
	}
	
	//정보게시판 글 목록
	@RequestMapping("/infoBoard/infoBoardList.do")
	public ModelAndView process(@RequestParam(value="pageNum", defaultValue="1") int currentPage, @RequestParam(value="keyfield", defaultValue="")String keyfield,@RequestParam(value="keyword", defaultValue="") String keyword) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyfield", keyfield);//RequestParam으로 가져온 값을 변수keyfield 저장후 map에 넣어줌!
		map.put("keyword", keyword);//RequestParam으로 가져온 값을 변수keyword 저장후 map에 넣어줌!
		
		//총 글의 갯수 또는 검색된 글의 갯수
		int count = InfoBoardService.selectRowCount(map);
		if (log.isDebugEnabled()) {
			log.debug("<<count>> : " + count);
		}		
		
		PagingUtil page = new PagingUtil(keyfield, keyword, currentPage, count, 10,10,"infoBoardList.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<InfoBoardVO> list = null;
		if (count > 0) {
			//목록을 호출
			list = InfoBoardService.selectList(map);
			
			if (log.isDebugEnabled()) {
				log.debug("<<글 목록>> : " +  list);
			}
		}
		
		/*
		 * - Model과 View를 동시에 설정이 가능하며 컨트롤러는 
		 * ModelAndView객체만 리턴하지만 Model과 View가 모두 리턴 가능
		 * ModelAndView mav = new ModelAndView();
		 */
		ModelAndView mav = new ModelAndView();
		mav.setViewName("infoBoardList"); //infoBoard.xml이름과 동일 infoBoardList 폼을 호출
		mav.addObject("count",count);
		mav.addObject("list",list);
		mav.addObject("pagingHtml", page.getPagingHtml());
		return mav;
	}
	
	//글 등록 폼
	@RequestMapping(value="/infoBoard/write.do",method=RequestMethod.GET)//데이터를 GET방식으로 받을경우
	public String form() {
		return "infoBoardWrite";//board.xml로 return, 폼을 호출
	}
	//글 등록 처리 //데이터를 POST방식으로 받을경우
	@RequestMapping(value="/infoBoard/write.do",method=RequestMethod.POST)
	//									   ┌초기화된 자바빈(VO) 				┌오류처리 	         	 ┌회원번호
	public String submit(@Valid InfoBoardVO infoBoardVO, BindingResult result, HttpSession session) {
		//				   └유효성 검사!
		//BindingResult의 경우 ModelAttribute을 이용해 매개변수를 Bean에 binding 할 때,
		//발생한 오류 정보를 받기 위해 선언해야 하는 어노테이션
		if (log.isDebugEnabled()) {
			log.debug("<<게시판 글 저장>> : " +  infoBoardVO);
		}
			
		//유효성 체크 결과 오류가 있으면 폼 호출
		if (result.hasErrors()) {//hashErrors()메소드로 에러가 존재하는 지 확인
			return "infoBoardWrite";
		}
			
		//회원 번호 셋팅
		MemberVO vo = (MemberVO)session.getAttribute("user");
		infoBoardVO.setMem_num(vo.getMem_num());
			
		//글쓰기
		InfoBoardService.insertBoard(infoBoardVO);
			
		return "redirect:/infoBoard/infoBoardList.do"; //리다이렉트
	}
	//글상세
	@RequestMapping("/infoBoard/detail.do")
	public ModelAndView process(@RequestParam int post_num) {
		if (log.isDebugEnabled()) {
			log.debug("<<글 상세>>" + post_num);
		}
		
		//해당 글의 조회수 증가 (여기서는 필요 없음)
		//InfoBoardService.updateHit(board_num);
		
		//증가한 조회수를 읽어옴
		InfoBoardVO board = InfoBoardService.selectBoard(post_num);
		
		//						  ┌infoBoard.xml   ┌key   ┌value
		return new ModelAndView("infoBoardView", "board", board); //객체 생성
	}

	//이미지 출력
	@RequestMapping("/infoBoard/imageView.do") //boardView.jsp
	public ModelAndView viewImage(@RequestParam int post_num) {
			
		InfoBoardVO infoBoardVO = InfoBoardService.selectBoard(post_num);
		/*
		 * - Model과 View를 동시에 설정이 가능하며 컨트롤러는 
		 * ModelAndView객체만 리턴하지만 Model과 View가 모두 리턴 가능
		 * ModelAndView mav = new ModelAndView();
	     */
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");//ImageView.java 호출
			
		mav.addObject("imageFile", infoBoardVO.getUploadfile());
		mav.addObject("filename", infoBoardVO.getFilename());
			
		return mav;
	}
	
	//수정 폼 호출
	@RequestMapping(value="/infoBoard/update.do",method=RequestMethod.GET)
	//										┌게시판 번호	┌Controller에서 생성한 데이터를 담아서 View로 전달할 때 사용하는 객체.
	public String form(@RequestParam int post_num, Model model) {
	
		InfoBoardVO infoBoardVO = InfoBoardService.selectBoard(post_num);
			
		//model을 통해서 Request에 저장될 수 있도록함
		//addAttribute("키", "값") 메소드를 사용하여 전달할 데이터 세팅
		model.addAttribute("infoBoardVO", infoBoardVO);
	
		return "infoBoardModify";
	}
	//글 수정 처리
	@RequestMapping(value="/infoBoard/update.do",method=RequestMethod.POST)
	//	  									     ┌초기화된 자바빈(VO)					 ┌오류처리 	    			┌아이피	      			┌회원번호	 	┌Controller에서 생성한 데이터를 담아서 View로 전달할 때 사용하는 객체.
	public String submitUpdate(@Valid InfoBoardVO infoBoardVO, BindingResult result, HttpServletRequest request, HttpSession session, Model model) {
		
		if (log.isDebugEnabled()) {
			log.debug("<<글 정보 수정>>" + infoBoardVO);
		}
		//유효성 체크 결과 오류가 있으면 폼 호출
		if (result.hasErrors()) {
			return "infoBoardModify";// infoBoard.xml 호출
		}
			
		//회원 번호 셋팅
		MemberVO vo = (MemberVO)session.getAttribute("user");
		infoBoardVO.setMem_num(vo.getMem_num());
		
		//글 수정     ┌updateBoard에 초기화된 boardVO를 넘겨줌
		InfoBoardService.updateBoard(infoBoardVO);
		
		//View에 표시할 메시지 Model 객체를 파라미터로 받는다.
		model.addAttribute("message", "글 수정 완료");
		model.addAttribute("url", request.getContextPath()+ "/infoBoard/infoBoardList.do");
			
		//타일스 설정에 아래 뷰 이름이 없으면 단독으로 JSP 호출
		return "common/result";
	}	
	//글삭제
	@RequestMapping("/infoBoard/delete.do")
	//												┌게시판 번호  ┌Controller에서 생성한 데이터를 담아서 View로 전달할 때 사용하는 객체.						
	public String submitDelete(@RequestParam int post_num, Model model, HttpServletRequest request) {
	//																							└ 경로
		if (log.isDebugEnabled()) {
			log.debug("<<게시판 글 삭제>> : " + post_num);
		}
		
		//글 삭제
		InfoBoardService.deleteBoard(post_num);
			
		//View에 표시할 메시지 Model 객체를 파라미터로 받는다.
		model.addAttribute("message", "글삭제 완료");
		model.addAttribute("url", request.getContextPath()+"/infoBoard/infoBoardList.do");
		return "common/result";
	}
	
	
	
}