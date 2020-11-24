package kr.spring.timetable.vo;

public class SubjectVO {
	private int sub_num;
	private String sub_category;
	private String sub_name;
	private String prof_name;
	private int sub_credit;			//default: 3
	private String sub_time;
	private String sub_classRoom;
	private int sub_capacity;		//default: 100
	private int sub_online;			//default: 0
	private String sub_remark;
	
	//Getters and Setters
	public int getSub_num() {
		return sub_num;
	}
	public String getSub_category() {
		return sub_category;
	}
	public String getSub_name() {
		return sub_name;
	}
	public String getProf_name() {
		return prof_name;
	}
	public int getSub_credit() {
		return sub_credit;
	}
	public String getSub_time() {
		return sub_time;
	}
	public String getSub_classRoom() {
		return sub_classRoom;
	}
	public int getSub_capacity() {
		return sub_capacity;
	}
	public int getSub_online() {
		return sub_online;
	}
	public String getSub_remark() {
		return sub_remark;
	}
	public void setSub_num(int sub_num) {
		this.sub_num = sub_num;
	}
	public void setSub_category(String sub_category) {
		this.sub_category = sub_category;
	}
	public void setSub_name(String sub_name) {
		this.sub_name = sub_name;
	}
	public void setProf_name(String prof_name) {
		this.prof_name = prof_name;
	}
	public void setSub_credit(int sub_credit) {
		this.sub_credit = sub_credit;
	}
	public void setSub_time(String sub_time) {
		this.sub_time = sub_time;
	}
	public void setSub_classRoom(String sub_classRoom) {
		this.sub_classRoom = sub_classRoom;
	}
	public void setSub_capacity(int sub_capacity) {
		this.sub_capacity = sub_capacity;
	}
	public void setSub_online(int sub_online) {
		this.sub_online = sub_online;
	}
	public void setSub_remark(String sub_remark) {
		this.sub_remark = sub_remark;
	}
	
	@Override
	public String toString() {
		return "SubjectVO [sub_num=" + sub_num + ", sub_category=" + sub_category + ", sub_name=" + sub_name
				+ ", prof_name=" + prof_name + ", sub_credit=" + sub_credit + ", sub_time=" + sub_time
				+ ", sub_classRoom=" + sub_classRoom + ", sub_capacity=" + sub_capacity + ", sub_online=" + sub_online
				+ ", sub_remark=" + sub_remark + "]";
	}
	
}
