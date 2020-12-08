package kr.spring.interceptor;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.spring.board.customboard.service.CustomBoardService;
import kr.spring.board.customboard.vo.CustomBoardVO;
import kr.spring.member.vo.MemberVO;

public class CustomBoardWriterCheck extends HandlerInterceptorAdapter{
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private CustomBoardService customBoardService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		if(log.isDebugEnabled()) {
			log.debug("<<로그인 아이디와 작성자 아이디 일치 여부 체크>>");
		}
		
		//작성자의 회원 번호 구하기
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		CustomBoardVO board = customBoardService.selectCustomBoard(board_num);
		
		
		HttpSession session = request.getSession();
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		if (log.isDebugEnabled()) {
			log.debug("<<로그인 회원 번호>>" + user.getMem_num());
			log.debug("<<게시판 생성회원 번호>>" + board.getMem_num());
		}
		
		//로그인 회원 번호와 작성자 회원 번호 일치 여부 체크
		if(user==null || user.getMem_num() != board.getMem_num()) {
			if(log.isDebugEnabled()) {
				log.debug("<<게시판 접근 권한이 없습니다>>");
			}

			//로그인 아이디와 작성자 아이디가 불일치할 때는 경고 페이지 호출
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/common/notice.jsp");
			dispatcher.forward(request, response);
		
			return false;
		}
		
		if (log.isDebugEnabled()) {
			log.debug("<<게시판 생성자와 로그인 한 아이디가 동일합니다>>");
		}
		return true;
	}
}
