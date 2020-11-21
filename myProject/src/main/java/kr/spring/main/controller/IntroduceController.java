package kr.spring.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IntroduceController {
	@RequestMapping("/main/introduce.do")
	public String getMain() {
		return "main/introduce"; //template definition이 아닌 jsp호출
	}
}
