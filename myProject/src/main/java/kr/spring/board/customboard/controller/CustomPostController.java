package kr.spring.board.customboard.controller;

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

import kr.spring.board.customboard.service.CustomPostService;
import kr.spring.board.customboard.vo.CustomBoardVO;
import kr.spring.board.customboard.vo.CustomPostVO;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.PagingUtil;

@Controller
public class CustomPostController {

	private Logger log = Logger.getLogger(this.getClass());

	@Resource
	CustomPostService customPostService;

	//자바빈 초기화
	@ModelAttribute
	public CustomPostVO initCommand() {
		return new CustomPostVO();
	}

	//글 목록 (게시글VO를 List타입으로 반환)
	@RequestMapping("/customBoard/customPostList.do")
	public ModelAndView boardList(
			//pageNum으로 전달되는 값이 있으면 매핑
			@RequestParam(value="pageNum", defaultValue="1") int currentPage,
			@RequestParam(value="keyfield", defaultValue="") String keyfield,
			@RequestParam(value="keyword", defaultValue="") String keyword,
			@RequestParam int board_num) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyfield", keyfield); //검색 항목
		map.put("keyword", keyword); //검색 내용
		map.put("board_num", board_num); //게시판 번호

		//총 글의 개수 또는 검색된 글의 개수
		int count = customPostService.selectRowCount(map);
		if(log.isDebugEnabled()) {
			log.debug("<<count>> : " + count);
		}

		//검색할 때 페이징처리
		PagingUtil page = new PagingUtil(keyfield, keyword, currentPage, count, 10, 10, "customPostList.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());

		CustomBoardVO boardInfo = customPostService.selectBoardInfo(board_num); //게시판 정보
		if(log.isDebugEnabled()) {
			log.debug("<<Custom게시판-게시판 정보>> : " + boardInfo);
		}

		List<CustomPostVO> postList = null; //게시글 목록

		if(log.isDebugEnabled()) {
			log.debug("Custom게시판번호>> : " + board_num );
			log.debug("<<Map데이터>> : " + map);
		}

		if(count > 0) {			
			postList = customPostService.selectPostList(map);

			if(log.isDebugEnabled()) {
				log.debug("<<Custom게시판-글 목록>> : " + postList);
			}
		}

		//customBoard.anonymous가 1이면 전체 익명 - 0이면 실명
		
		ModelAndView mav = new ModelAndView();

		mav.setViewName("customPostList");
		mav.addObject("count", count);
		mav.addObject("boardInfo", boardInfo);
		mav.addObject("postList", postList);
		mav.addObject("pagingHtml", page.getPagingHtml());

		return mav; 	
	}


	//글 상세 (게시글VO 하나만 반환)
	@RequestMapping("/customBoard/customPostDetail.do")
	public ModelAndView boardDetail(
			@RequestParam(value="post_num") int post_num, 
			@RequestParam(value="board_num") int board_num) {

		if(log.isDebugEnabled()) {
			log.debug("<<사용자 생성 게시글 상세>> post_num:" + post_num + " board_num:" +board_num);
		}

		//게시판 title, subtitle
		CustomBoardVO boardInfo = customPostService.selectBoardInfo(board_num); //게시판 정보
		if(log.isDebugEnabled()) {
			log.debug("<<Custom게시판-글 상세>> : " + boardInfo);
		}

		//게시글 자바빈 반환
		CustomPostVO customPost = customPostService.selectCustomPost(board_num); //게시글 정보
		String id = customPostService.selectPostWriter(post_num);
		
		if(log.isDebugEnabled()) {
			log.debug("<<Custom게시글 작성자>> : " + id);
		}
		
		ModelAndView mav = new ModelAndView();
		// view 이름
		mav.setViewName("customPostView");
		mav.addObject("id", id); //작성자 id 저장
		mav.addObject("customPost", customPost);
		mav.addObject("boardInfo", boardInfo);
		return mav;
	}

	//이미지 출력
	@RequestMapping("/customBoard/customPostImageView.do")
	public ModelAndView viewImage(@RequestParam int post_num) {

		CustomPostVO customPost = customPostService.selectCustomPost(post_num);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("PostImageView");
		//byte[]타입의 데이터
		mav.addObject("imageFile", customPost.getUploadfile());
		mav.addObject("filename", customPost.getFilename());
		//
		return mav;
	}

	//글 등록
	@RequestMapping(value="/customBoard/customPostWrite.do",method=RequestMethod.GET)
	public String form(@RequestParam int board_num, Model model) {

		//게시판 title, subtitle
		CustomBoardVO boardInfo = customPostService.selectBoardInfo(board_num); //게시판 정보
		if(log.isDebugEnabled()) {
			log.debug("<<Custom게시판-글 등록>> : " + boardInfo);
		}

		model.addAttribute("boardInfo", boardInfo);
		return "customPostWrite";
	}

	//글 등록 처리
	@RequestMapping(value="/customBoard/customPostWrite.do",method=RequestMethod.POST)
	public String submit(@Valid CustomPostVO customPostVO, BindingResult result, HttpServletRequest request, HttpSession session) {

		if (log.isDebugEnabled()) {
			log.debug("<<게시판 글 등록>> : " +  customPostVO);
		}

		if (result.hasErrors()) {
			return  "customPostWrite";
		}

		//회원 번호 세팅
		MemberVO vo = (MemberVO)session.getAttribute("user");
		customPostVO.setMem_num(vo.getMem_num());
		
		//글작성
		customPostService.insertPost(customPostVO);

		return "redirect:/customBoard/customPostList.do?board_num="+customPostVO.getBoard_num(); 
	}

	//글 삭제 처리
	@RequestMapping("/customBoard/customPostDelete.do")
	public String submitDelete(@RequestParam int post_num, Model model, HttpServletRequest request) {

		if(log.isDebugEnabled()) {
			log.debug("<<생성자 게시판 글 삭제>> : " + post_num);
		}

		//글 삭제
		customPostService.deletePost(post_num);

		model.addAttribute("message", "글 삭제 완료!!");
		model.addAttribute("url", request.getContextPath()+"/main/main_board.do");

		return "common/result";
	}

}
