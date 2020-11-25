package kr.spring.interceptor;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.spring.board.infoboard.service.InfoBoardService;
import kr.spring.board.infoboard.vo.InfoBoardVO;
import kr.spring.member.vo.MemberVO;

public class WriterCheckInterceptor extends HandlerInterceptorAdapter{
	private Logger log = Logger.getLogger(this.getClass());
	
	//작성자의 회원번호를 알아내기 위해 필요함
	@Resource
	private InfoBoardService InfoBoardService;
	
	//preHandle를 재정의
	@Override//┌요청한 페이지 이전에 실행됨
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		if (log.isDebugEnabled()) {
			log.debug("<<로그인 아이디와 작성자 아이디 일치 여부 체크>>");
		}
		//작성자의 회원 번호 구하기								┌파라미터값으로 회원번호가 넘어옴
		int post_num = Integer.parseInt(request.getParameter("post_num"));
		InfoBoardVO board = InfoBoardService.selectBoard(post_num);
		
		//로그인 회원번호 구하기
		HttpSession session = request.getSession();
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		if (log.isDebugEnabled()) {
			log.debug("<<로그인 회원 번호>>" + user.getMem_num());
			log.debug("<<작성자 회원 번호>>" + board.getMem_num());
		}
		
		//로그인 회원 번호와 작성자 회원 번호 일치 여부 체크
		if (user == null || user.getMem_num() != board.getMem_num()) { //로그인 되어있지 않은 경우
			if (log.isDebugEnabled()) {
				log.debug("<<로그인 아이디와 작성자 아이디 불일치>>");
			}
			//로그인 아이디와 작성자 아이디가 불일치할 때는 경고 페이지 호출
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/common/notice.jsp");
			dispatcher.forward(request, response);
			
			//이후의 동작은 진행되지 않게!
			//요청에 의해 호출되는 페이지가 호출되지 않음!
			return false;
		}
			if (log.isDebugEnabled()) {
				log.debug("<<로그인 아이디와 작성자 아이디 일치>>");
			}
		return true;
	}
}












