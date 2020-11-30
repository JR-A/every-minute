package kr.spring.bookstore.controller;

import java.util.ArrayList;
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

import kr.spring.bookstore.service.BookStoreService;
import kr.spring.bookstore.vo.BookStoreVO;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.PagingUtil;

@Controller
public class BookStoreController {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	BookStoreService bookStoreService;
	
	@ModelAttribute
	public BookStoreVO initCommand() {
		return new BookStoreVO();
	}
	
	//list
	@RequestMapping("/bookStore/bookStoreList.do")
	public ModelAndView listForm(@RequestParam(value="pageNum", defaultValue="1") int currentPage,
			@RequestParam(value="keyfield", defaultValue="") String keyfield,
			@RequestParam(value="keyword", defaultValue="") String keyword) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		
		int count = bookStoreService.selectRowCount(map);
		if(log.isDebugEnabled()) {
			log.debug("<<count>> : " + count);
		}
		
		PagingUtil page = new PagingUtil(keyfield, keyword, currentPage, count, 10, 10, "bookStoreList.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<BookStoreVO> list = null;
		if(count > 0) {
			list = bookStoreService.selectList(map);
			if(log.isDebugEnabled()) {
				log.debug("<<list>> : " + list);
			}
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("bookStoreList");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("pagingHtml", page.getPagingHtml());
		
		return mav;
	}
	
	//write form
	@RequestMapping(value="/bookStore/bookStoreWrite.do", method=RequestMethod.GET)
	public String writeForm(BookStoreVO bookStoreVO) {
		return "bookStoreWrite";
	}
	
	//write submit
	@RequestMapping(value="/bookStore/bookStoreWrite.do", method=RequestMethod.POST)
	public String writeSubmit(@Valid BookStoreVO bookStoreVO, BindingResult result,
			HttpServletRequest request, HttpSession session) {
		if(log.isDebugEnabled()) {
			log.debug("<<writeForm>> : " + bookStoreVO);
		}
		
		if(result.hasErrors()) {
			return "bookStoreWrite";
		}
		
		MemberVO memberVO = (MemberVO)session.getAttribute("user");
		bookStoreVO.setMem_num(memberVO.getMem_num());
		
		bookStoreService.insertBoard(bookStoreVO);
		
		return "redirect:/bookStore/bookStoreList.do";
	}
	
	//view form
	@RequestMapping("/bookStore/bookStoreView.do")
	public ModelAndView viewForm(@RequestParam int bs_num) {
		if(log.isDebugEnabled()) {
			log.debug("<<view>> : " + bs_num);
		}
		
		BookStoreVO bookStoreVO = bookStoreService.selectBoard(bs_num);
		
		return new ModelAndView("bookStoreView", "bookStoreVO", bookStoreVO);
	}
	
	//image output
	@RequestMapping("/bookStore/imageView.do")
	public ModelAndView viewImage(@RequestParam int bs_num) {
		BookStoreVO bookStoreVO = bookStoreService.selectBoard(bs_num);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		mav.addObject("imageFile", bookStoreVO.getUploadfile());
		mav.addObject("filename", bookStoreVO.getFilename());
		
		return mav;
	}
	
	//view delete
	@RequestMapping("/bookStore/delete.do")
	public String viewDelete(@RequestParam int bs_num, Model model, HttpServletRequest request) {
		if(log.isDebugEnabled()) {
			log.debug("<<delete>> : " + bs_num);
		}
		
		bookStoreService.deleteBoard(bs_num);
		
		model.addAttribute("message", "삭제 완료");
		model.addAttribute("url", request.getContextPath()+"/bookStore/bookStoreList.do");
		
		return "common/result";
	}
}
