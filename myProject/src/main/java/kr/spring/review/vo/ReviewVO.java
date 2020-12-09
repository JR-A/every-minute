package kr.spring.review.vo;

import java.sql.Date;

//수강평
public class ReviewVO {
	private int post_num;
	private int mem_num;
	private int sub_num;
	private String sub_name;	//중복되지만 출력속도 위해 프로퍼티로 가짐(테이블 속성엔 없음)
	private String prof_name;	//중복되지만 출력속도 위해 프로퍼티로 가짐(테이블 속성엔 없음)
	private String content;
	private int rate;
	private Date reg_date;
	
	public int getPost_num() {
		return post_num;
	}
	public void setPost_num(int post_num) {
		this.post_num = post_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public int getSub_num() {
		return sub_num;
	}
	public void setSub_num(int sub_num) {
		this.sub_num = sub_num;
	}
	public String getSub_name() {
		return sub_name;
	}
	public void setSub_name(String sub_name) {
		this.sub_name = sub_name;
	}
	public String getProf_name() {
		return prof_name;
	}
	public void setProf_name(String prof_name) {
		this.prof_name = prof_name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	
	@Override
	public String toString() {
		return "ReviewVO [post_num=" + post_num + ", mem_num=" + mem_num + ", sub_num=" + sub_num + ", sub_name="
				+ sub_name + ", prof_name=" + prof_name + ", content=" + content + ", rate=" + rate + ", reg_date="
				+ reg_date + "]";
	}
	
}
