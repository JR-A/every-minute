package kr.spring.message.controller;

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

import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import kr.spring.message.service.MessageService;
import kr.spring.message.vo.MessageVO;
import kr.spring.util.PagingUtil;
@Controller
public class MessageController {

	private Logger log = Logger.getLogger(this.getClass());

	@Resource
	MessageService messageService;
	@Resource
	MemberService memberService;

	@ModelAttribute
	public MessageVO initCommand() {
		return new MessageVO();
	}

	@RequestMapping("/message/messageList.do")
	public ModelAndView process(@RequestParam(value="pageNum", defaultValue="1") int currentPage,HttpSession session) {

		MemberVO vo = (MemberVO)session.getAttribute("user");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mem_num", vo.getMem_num());
		int count = messageService.selectRowCount(map);
		if (log.isDebugEnabled()) {
			log.debug("<<count>> : " + count);
		}		

		PagingUtil page = new PagingUtil(currentPage, count, 10,10,"messageList.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());

		List<MessageVO> list = null;
		if (count > 0) {

			list = messageService.selectList(map);

			if (log.isDebugEnabled()) {
				log.debug("<<쪽지 목록>> : " +  list);
			}
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("messageList");
		mav.addObject("count",count);
		mav.addObject("list",list);
		return mav;
	}

	@RequestMapping("/message/messageSendList.do")
	public ModelAndView processSend(@RequestParam(value="pageNum", defaultValue="1") int currentPage,HttpSession session) {

		MemberVO vo = (MemberVO)session.getAttribute("user");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mem_num", vo.getMem_num());
		int count = messageService.selectSendRowCount(map);
		if (log.isDebugEnabled()) {
			log.debug("<<count>> : " + count);
		}		

		PagingUtil page = new PagingUtil(currentPage, count, 10,10,"messageSendList.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());

		List<MessageVO> list = null;
		if (count > 0) {

			list = messageService.selectSendList(map);

			if (log.isDebugEnabled()) {
				log.debug("<<쪽지 목록>> : " +  list);
			}
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("messageList");
		mav.addObject("count",count);
		mav.addObject("list",list);
		return mav;
	}

	
	@RequestMapping(value="/message/sendMessage.do")
	public String formMessage(@RequestParam int anony, @RequestParam int target_mem_num,Model model) {
		
		model.addAttribute("anonymous", anony);
		model.addAttribute("target_mem_num", target_mem_num);
		
		return "messageSend";
	}
	
	@RequestMapping(value="/message/write.do",method=RequestMethod.GET)
	public String form() {
		return "messageWrite";
	}
	@RequestMapping(value="/message/write.do",method=RequestMethod.POST)								   
	public String submit(@Valid MessageVO messageVO, BindingResult result, HttpSession session) {
		if (log.isDebugEnabled()) {
			log.debug("<<메시지 저장>> : " +  messageVO);
		}

		if (result.hasErrors()) {
			return "messageWrite"; 
		}

		MemberVO vo = (MemberVO)session.getAttribute("user");
		messageVO.setMem_num(vo.getMem_num());

		if(messageVO.getTarget_id() != null) {
			MemberVO member = (MemberVO)memberService.selectCheckMember(messageVO.getTarget_id());
			messageVO.setTarget_mem_num(member.getMem_num());
		}
		messageService.insertBoard(messageVO);

		return "redirect:/message/messageList.do";
	}

	//메세지 상세보기
	@RequestMapping("/message/messageView.do")
	public String message(@RequestParam int msg_num, Model model,HttpSession session) throws Exception{

		MemberVO user = (MemberVO)session.getAttribute("user");
		MessageVO vo = messageService.selectMessage(msg_num);
		List<MessageVO> replyList = null;
		if(vo.getMem_num() != user.getMem_num()) {
			//쪽지 읽기 처리
			messageService.updateMsg_check(msg_num);
			replyList = messageService.selectReplyList(msg_num);
		}

		model.addAttribute("messageVO", vo);
		model.addAttribute("replyList", replyList);

		return "messageView";
	}
	
	//글삭제
	@RequestMapping("/message/delete.do")
	//												┌게시판 번호  ┌Controller에서 생성한 데이터를 담아서 View로 전달할 때 사용하는 객체.						
	public String submitDelete(@RequestParam int msg_num, Model model, HttpServletRequest request) {
	//																							└
		if (log.isDebugEnabled()) {
			log.debug("<<쪽지 삭제>> : " + msg_num);
		}
		
		//글 삭제
		messageService.deleteBoard(msg_num);
		
		//View에 표시할 메시지 Model 객체를 파라미터로 받는다.
		model.addAttribute("message", "쪽지삭제 완료");
		model.addAttribute("msg_num", msg_num);
		return "redirect:/message/messageList.do";
	}
}
