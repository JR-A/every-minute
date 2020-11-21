package kr.spring.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookStoreController {
	
	//게시판 폼 호출
	@RequestMapping("/bookStore/bookStoreList.do")
	public String getMain() {
		return "bookStoreList";
	}
}
