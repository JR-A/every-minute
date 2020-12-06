package kr.spring.timetable.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.member.vo.MemberVO;
import kr.spring.timetable.common.TimesMaker;
import kr.spring.timetable.service.TimetableService;
import kr.spring.timetable.vo.CustomSubjectVO;
import kr.spring.timetable.vo.SubjectVO;
import kr.spring.timetable.vo.TimesVO;
import kr.spring.timetable.vo.TimetableVO;

@Controller
public class TimetableAjaxController {
	
	//프로퍼티
	@Resource
	private TimetableService timetableService;
	@Resource
	private TimesMaker timesMaker;
	
	//로그 처리(로그 대상 지정)
	private Logger log = Logger.getLogger(this.getClass());
	
	//시간표 생성
	@RequestMapping("/timetable/createTimetable.do")
	@ResponseBody
	public Map<String, String> createTimetable(@RequestParam("semester") String semester, HttpSession session) {
		
		Map<String, String> map = new HashMap<String, String>();
		TimetableVO timetable = new TimetableVO();
		
		//세션에 저장된 회원 정보 반환
		MemberVO member = (MemberVO)session.getAttribute("user");
	
		//로그처리
		if(log.isDebugEnabled()) {
			log.debug("<<시간표 생성>> : " + member.getId() + ", " + semester);
		}
		
		//시간표 생성
		timetable.setMem_num(member.getMem_num());	
		timetable.setSemester(semester);
		timetable.setIsPrimary(0);
		
		int count = timetableService.selectTimetableCountOfUser(timetable);
		
		timetable.setT_name("시간표"+(count + 1));	//기본 시간표이름 (더 생각해보기)
		int t_num = timetableService.insertTimetable(timetable);	//시간표 추가
		
		map.put("t_num", Integer.toString(t_num));
		map.put("semester", timetable.getSemester());
		
		return map;
	}
	
	//과목 목록 가져오기
	@RequestMapping("/timetable/loadSubjects.do")
	@ResponseBody
	public List<SubjectVO> loadSubjects() {
		
		List<SubjectVO> subjectList = timetableService.selectSubjectList();
		
		//a~c를 10~12로 대체
		for(SubjectVO subject : subjectList) {
			subject.setSub_time(subject.getSub_time().replace("a", "10"));
			subject.setSub_time(subject.getSub_time().replace("b", "11"));
			subject.setSub_time(subject.getSub_time().replace("c", "12"));
		}
		
		return subjectList;
	}
	
	//과목 미리보기
	@RequestMapping("/timetable/previewSubject.do")
	@ResponseBody
	public List<TimesVO> makeTimesVO(@RequestParam(value="sub_num") String sub_num){
		List<TimesVO> timesList = new ArrayList<TimesVO>();
		List<SubjectVO> subjectList = new ArrayList<SubjectVO>();
		
		SubjectVO subject = timetableService.selectSubject(Integer.parseInt(sub_num));
		subjectList.add(subject);
		
		timesList = timesMaker.makeTimesVO(subjectList, null);
		
		return timesList;
	}
	
	//과목 추가하기
	@RequestMapping("/timetable/insertSubject.do")
	@ResponseBody
	public Map<String, Object> insertSubject(@RequestParam(value="sub_num") String sub_num, 
											 @RequestParam(value="t_num") String t_num){
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<TimesVO> originTimesList = new ArrayList<TimesVO>();
		List<SubjectVO> originSubjectList = new ArrayList<SubjectVO>();
		List<CustomSubjectVO> originCustomSubjectList = new ArrayList<CustomSubjectVO>();
		
		List<TimesVO> newTimesList = new ArrayList<TimesVO>();
		List<SubjectVO> newSubjectList = new ArrayList<SubjectVO>();
		
		//선택된 과목의 시간매핑
		SubjectVO subject = timetableService.selectSubject(Integer.parseInt(sub_num));
		newSubjectList.add(subject);
		newTimesList = timesMaker.makeTimesVO(newSubjectList, null);
		
		//시간표의 기존 과목들 시간매핑
		originSubjectList = timetableService.selectSubjectOfTimetable(Integer.parseInt(t_num));
		originCustomSubjectList = timetableService.selectCustomSubjectList(Integer.parseInt(t_num));
		originTimesList = timesMaker.makeTimesVO(originSubjectList, originCustomSubjectList);
		
		//이미 추가한 과목인 경우
		for(int i=0; i<originSubjectList.size(); i++) {
			if(subject.getSub_num() == originSubjectList.get(i).getSub_num()) {
				map.put("result", "duplicated");
				return map;
			}
		}
		
		//겹치는 시간이 있는 경우
		for(int i=0; i<newTimesList.size(); i++) {
			for(int j=0; j<originTimesList.size(); j++) {
				//요일이 같고
				if( newTimesList.get(i).getDay() == originTimesList.get(j).getDay()) {
					//시간이 겹치는 경우
					int newStarttime = newTimesList.get(i).getStarttime();
					int newEndtime = newTimesList.get(i).getEndtime();
					
					int starttime = originTimesList.get(j).getStarttime();
					int endtime = originTimesList.get(j).getEndtime();
					
					if( (starttime <= newStarttime && newStarttime <= endtime) || (starttime <= newEndtime && newEndtime <= endtime) ) {
						map.put("result", "overlapped");
						return map;
					}
				}
			}
		}
		
		//시간표에 과목 추가
		timetableService.insertSubject(Integer.parseInt(t_num), Integer.parseInt(sub_num));
		//수정시간 반영
		timetableService.updateModifyDate(Integer.parseInt(t_num));
		
		map.put("result", "success");
		map.put("timesList", newTimesList);
		
		return map;
	}
	
	
	//과목 삭제하기
	@RequestMapping("/timetable/deleteSubject.do")
	@ResponseBody
	public Map<String, String> deleteSubject(@RequestParam(value="sub_num") String sub_num, 
			 				  @RequestParam(value="t_num") String t_num) {
		
		Map<String, String> map = new HashMap<String, String>();
		
		//과목을 삭제
		timetableService.deleteSubject(Integer.parseInt(t_num), Integer.parseInt(sub_num));

		//수정시간 반영
		timetableService.updateModifyDate(Integer.parseInt(t_num));
		
		map.put("result", "success");
		
		return map;
	}
	
	//커스텀과목 추가하기, 수정하기
	@RequestMapping("/timetable/insertUpdateCustomSubject.do")
	@ResponseBody
	public Map<String, String> insertCustomSubject(@ModelAttribute CustomSubjectVO customSubject,
													HttpSession session){
		Map<String, String> map = new HashMap<String, String>();
		List<TimesVO> originTimesList = new ArrayList<TimesVO>();
		List<SubjectVO> originSubjectList = new ArrayList<SubjectVO>();
		List<CustomSubjectVO> originCustomSubjectList = new ArrayList<CustomSubjectVO>();
		
		List<TimesVO> newTimesList = new ArrayList<TimesVO>();
		List<CustomSubjectVO> newCustomSubjectList = new ArrayList<CustomSubjectVO>();
		
		//추가된 커스텀과목의 시간 매핑
		newCustomSubjectList.add(customSubject);
		newTimesList = timesMaker.makeTimesVO(null, newCustomSubjectList);
		
		//시간표의 기존 과목들 시간매핑
		originSubjectList = timetableService.selectSubjectOfTimetable(customSubject.getT_num());
		//커스텀 과목 번호가 없으면(커스텀과목 추가시) 기존 커스텀과목 시간매핑
		if(customSubject.getCsub_num() == 0) {	
			originCustomSubjectList = timetableService.selectCustomSubjectList(customSubject.getT_num());
		}else {
		//커스텀 과목 번호가 있으면(커스텀과목 수정시) 현재 커스텀과목 제외하고 타임매핑
			originCustomSubjectList = timetableService.selectCustomSubjectListExceptThis(customSubject);
		}
		originTimesList = timesMaker.makeTimesVO(originSubjectList, originCustomSubjectList);
		
		//겹치는 시간이 있는 경우
		for(int i=0; i<newTimesList.size(); i++) {
			for(int j=0; j<originTimesList.size(); j++) {
				//요일이 같고
				if( newTimesList.get(i).getDay() == originTimesList.get(j).getDay()) {
					//시간이 겹치는 경우
					int newStarttime = newTimesList.get(i).getStarttime();
					int newEndtime = newTimesList.get(i).getEndtime();
					
					int starttime = originTimesList.get(j).getStarttime();
					int endtime = originTimesList.get(j).getEndtime();
					
					if( (starttime <= newStarttime && newStarttime <= endtime) || (starttime <= newEndtime && newEndtime <= endtime) ) {
						map.put("result", "overlapped");
						return map;
					}
				}
			}
		}
		
		//커스텀 과목 번호가 없으면
		if(customSubject.getCsub_num() == 0) {
			//커스텀 과목을 시간표에 추가
			timetableService.insertCustomSubject(customSubject);
		}else {
			//커스텀 과목을 수정
			timetableService.upadateCustomSubject(customSubject);
		}
		//수정시간 반영
		timetableService.updateModifyDate(customSubject.getT_num());
		
		map.put("result", "success");
		
		return map;
	}
	
	//커스텀과목 수정
	//커스텀과목 정보 불러오기
	@RequestMapping("/timetable/selectCustomSubject.do")
	@ResponseBody
	public List<TimesVO> selectCustomSubject(@RequestParam(value="csub_num") int csub_num) {
		List<CustomSubjectVO> csList = new ArrayList<CustomSubjectVO>();
		//커스텀과목 가져오기
		CustomSubjectVO customSubject = timetableService.selectCustomSubject(csub_num);
		csList.add(customSubject);
		
		List<TimesVO> timesList = timesMaker.makeTimesVO(null, csList);
		
		return timesList;
	}
	
	//시간표 정보 불러오기
	@RequestMapping("/timetable/selectTimetable.do")
	@ResponseBody
	public TimetableVO selectTimetable(@RequestParam(value="t_num") String t_num){
		
		TimetableVO timetable = timetableService.selectTimetable(Integer.parseInt(t_num));

		return timetable;
	}
	
	//시간표 설정 변경
	@RequestMapping("/timetable/updateTimetable.do")
	@ResponseBody
	public Map<String, String> updateTimetable(@RequestParam(value="updatePrimary") int updatePrimary, 
											   @ModelAttribute("timetableVO") TimetableVO timetableVO,
											   HttpSession session){
		
		Map<String, String> map = new HashMap<String, String>();
		
		//세션에 저장된 회원 정보 반환
		MemberVO member = (MemberVO)session.getAttribute("user");
		timetableVO.setMem_num(member.getMem_num());
		
		if(updatePrimary == 1) {
			//기본시간표를 현재 시간표로 변경
			timetableService.setIsPrimary(timetableVO);
		}
		//시간표 이름 수정
		timetableService.updateTimetableName(timetableVO);
		
		map.put("result", "success");
		
		return map;	
	}
	
	//시간표 삭제
	@RequestMapping("/timetable/deleteTimetable.do")
	@ResponseBody
	public Map<String, String> deleteTimetable(@RequestParam(value="t_num") String t_num) {
		Map<String, String> map = new HashMap<String, String>();
		
		TimetableVO timetable = timetableService.selectTimetable(Integer.parseInt(t_num));
		//해당 학기의 시간표 모두 가져오기
		List<TimetableVO> list = timetableService.selectList(timetable);
		//다른 시간표가 존재하고 기본시간표를 삭제하는경우
		if(list.size()>1 && timetable.getIsPrimary() == 1) {
			for(TimetableVO vo : list) {
				if(vo.getT_num() != Integer.parseInt(t_num)) {
					//기본 시간표로 지정
					timetableService.setIsPrimary(vo);
					break;
				}
			}
		}
		
		timetableService.deleteTimetable(Integer.parseInt(t_num));
		
		map.put("result", "success");
		return map;	
	}
}
