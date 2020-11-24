package kr.spring.timetable.vo;

import java.sql.Date;

public class TimetableVO {
	private int t_num;
	private int mem_num;		//REFERENCES Member (member)
	private String semester;	
	private String t_name;		//default: '시간표'
	private Date reg_date;		//default: SYSDATE
	private Date modify_date;	//default: SYSDATE
	private int isPrimary;		//default: 0
	
	//Getters and Setters
	public int getT_num() {
		return t_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public String getSemester() {
		return semester;
	}
	public String getT_name() {
		return t_name;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public Date getModify_date() {
		return modify_date;
	}
	public void setT_num(int t_num) {
		this.t_num = t_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public void setT_name(String t_name) {
		this.t_name = t_name;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}
	public int getIsPrimary() {
		return isPrimary;
	}
	public void setIsPrimary(int isPrimary) {
		this.isPrimary = isPrimary;
	}
	
	@Override
	public String toString() {
		return "TimetableVO [t_num=" + t_num + ", mem_num=" + mem_num + ", semester=" + semester + ", t_name=" + t_name
				+ ", reg_date=" + reg_date + ", modify_date=" + modify_date + ", isPrimary=" + isPrimary + "]";
	}
	
}
