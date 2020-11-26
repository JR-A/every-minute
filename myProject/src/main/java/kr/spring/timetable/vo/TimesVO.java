package kr.spring.timetable.vo;

//SubjectVO의 시간, 장소 등을 매핑하기 위한 VO
public class TimesVO {
	int sub_num;
	String sub_name;
	int day;	//요일(월:0 화:1 수:2 목:3 금:4)
	int starttime;	//시작교시
	int endtime;	//끝나는교시
	String classRoom; //강의실
	String prof_name;
	int color;		//0~30
	
	
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public String getSub_name() {
		return sub_name;
	}
	public void setSub_name(String sub_name) {
		this.sub_name = sub_name;
	}
	public int getSub_num() {
		return sub_num;
	}
	public void setSub_num(int sub_num) {
		this.sub_num = sub_num;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getStarttime() {
		return starttime;
	}
	public void setStarttime(int starttime) {
		this.starttime = starttime;
	}
	public int getEndtime() {
		return endtime;
	}
	public void setEndtime(int endtime) {
		this.endtime = endtime;
	}
	public String getProf_name() {
		return prof_name;
	}
	public void setProf_name(String prof_name) {
		this.prof_name = prof_name;
	}
	public String getClassRoom() {
		return classRoom;
	}
	public void setClassRoom(String classRoom) {
		this.classRoom = classRoom;
	}
	
	@Override
	public String toString() {
		return "TimesVO [sub_num=" + sub_num + ", day=" + day + ", starttime=" + starttime + ", endtime=" + endtime
				+ ", classRoom=" + classRoom + ", prof_name=" + prof_name + "]";
	}

}
