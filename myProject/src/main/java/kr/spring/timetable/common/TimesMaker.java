package kr.spring.timetable.common;

import java.util.ArrayList;
import java.util.List;

import kr.spring.timetable.vo.CustomSubjectVO;
import kr.spring.timetable.vo.SubjectVO;
import kr.spring.timetable.vo.TimesVO;

public class TimesMaker {
	
	//시간표에 보여주기 위해 subjectList로 시작시간, 끝나는시간, 교실 구하기
	public List<TimesVO> makeTimesVO(List<SubjectVO> subjectList, List<CustomSubjectVO> csubjectList){
		List<TimesVO> timesList = new ArrayList<TimesVO>();
		
		if(subjectList!=null) {
			//for(SubjectVO subject:subjectList) {	 //index가 필요해서 확장for문 대신 그냥 for문
			for(int s=0; s<subjectList.size(); s++) {
				
				String[] sub_time = subjectList.get(s).getSub_time().split(","); //ex. 월12,화23 ','기준 쪼개기
				String[] sub_class = subjectList.get(s).getSub_classRoom().split(","); //ex. T701,T702 ','기준 쪼개기		
				
				for(int i=0; i<sub_time.length; i++) {
					TimesVO times = new TimesVO();				//TimesVO 객체 생성
					times.setSub_num(subjectList.get(s).getSub_num());		//sub_num 저장
					times.setSub_name(subjectList.get(s).getSub_name());	//sub_name저장
					times.setColor(s+1);
					
					switch(sub_time[i].charAt(0)) {				//월12 에서 '월'을 요일로 설정
						case '월': times.setDay(0); break;
						case '화': times.setDay(1); break;
						case '수': times.setDay(2); break;
						case '목': times.setDay(3); break;
						case '금': times.setDay(4); break;
					}
					//char -> int 위해  -'0' 해줌
					times.setStarttime(sub_time[i].charAt(1) - '0');	//월12에서 '1'을 시작교시로 설정
					times.setEndtime(sub_time[i].charAt(sub_time[i].length()-1) - '0');	//월12에서 '2'를 끝나는교시로 설정
					times.setClassRoom(sub_class[i]);	//강의실을 T701로 설정
					times.setProf_name(subjectList.get(s).getProf_name());
					
					timesList.add(times);
				}
			}
		}
		
		if(csubjectList!=null) {
			for(int s=0; s<csubjectList.size(); s++) {
				
				String[] sub_time = csubjectList.get(s).getCsub_time().split(","); //ex. 월12,화23 ','기준 쪼개기
				String[] sub_class = null;
				if(csubjectList.get(s).getCsub_classRoom() != null) {
					sub_class = csubjectList.get(s).getCsub_classRoom().split(","); //ex. T701,T702 ','기준 쪼개기		
				}
				for(int i=0; i<sub_time.length; i++) {
					TimesVO times = new TimesVO();				//TimesVO 객체 생성
					times.setSub_num(csubjectList.get(s).getCsub_num());		//sub_num 저장
					times.setSub_name(csubjectList.get(s).getCsub_name());	//sub_name저장
					times.setColor(s+15);
					
					switch(sub_time[i].charAt(0)) {				//월12 에서 '월'을 요일로 설정
						case '월': times.setDay(0); break;
						case '화': times.setDay(1); break;
						case '수': times.setDay(2); break;
						case '목': times.setDay(3); break;
						case '금': times.setDay(4); break;
					}
					//char -> int 위해  -'0' 해줌
					times.setStarttime(sub_time[i].charAt(1) - '0');	//월12에서 '1'을 시작교시로 설정
					times.setEndtime(sub_time[i].charAt(sub_time[i].length()-1) - '0');	//월12에서 '2'를 끝나는교시로 설정
					if(csubjectList.get(s).getProf_name() != null) {times.setProf_name(csubjectList.get(s).getProf_name());}
					
					timesList.add(times);
				}
				if(sub_class != null) {
					for(int i=0; i<sub_class.length; i++) {
						timesList.get(i).setClassRoom(sub_class[i]);
					}
				}
			}
		}
		return timesList;
	}
}
