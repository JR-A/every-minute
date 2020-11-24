package kr.spring.timetable.vo;

public class CustomSubjectVO {
	private int csub_num;
	private int t_num;		//REFERENCES Timetable (t_num)
	private String csub_name;
	private String prof_name;
	private String csub_time;
	private String csub_classRoom;
	
	//Getters and Setters
	public int getCsub_num() {
		return csub_num;
	}
	public int getT_num() {
		return t_num;
	}
	public String getCsub_name() {
		return csub_name;
	}
	public String getProf_name() {
		return prof_name;
	}
	public String getCsub_time() {
		return csub_time;
	}
	public String getCsub_classRoom() {
		return csub_classRoom;
	}
	public void setCsub_num(int csub_num) {
		this.csub_num = csub_num;
	}
	public void setT_num(int t_num) {
		this.t_num = t_num;
	}
	public void setCsub_name(String csub_name) {
		this.csub_name = csub_name;
	}
	public void setProf_name(String prof_name) {
		this.prof_name = prof_name;
	}
	public void setCsub_time(String csub_time) {
		this.csub_time = csub_time;
	}
	public void setCsub_classRoom(String csub_classRoom) {
		this.csub_classRoom = csub_classRoom;
	}
	
	@Override
	public String toString() {
		return "CustomSubjectVO [csub_num=" + csub_num + ", t_num=" + t_num + ", csub_name=" + csub_name
				+ ", prof_name=" + prof_name + ", csub_time=" + csub_time + ", csub_classRoom=" + csub_classRoom + "]";
	}
	
}
