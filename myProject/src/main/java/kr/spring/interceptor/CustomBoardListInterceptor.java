package kr.spring.interceptor;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.spring.board.customboard.service.CustomBoardService;
import kr.spring.board.customboard.vo.CustomBoardVO;

public class CustomBoardListInterceptor extends HandlerInterceptorAdapter{

	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	CustomBoardService customBoardService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		
		List<CustomBoardVO> customBoardlist = customBoardService.selectBoardList(); 
		session.setAttribute("customBoardlist", customBoardlist);

		if(log.isDebugEnabled()) {
			log.debug("<<게시판 목록>> : " + customBoardlist);
		}
		
		//사용자 게시판 정보를 List 타입으로 받아서 session에 저장
		
		return true;
	}
	
}
