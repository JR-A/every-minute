package kr.spring.board.freeboard.vo;

import java.sql.Date;

public class ReplyVO {

	private int comment_num;
	private int post_num;
	private int mem_num;
	private String content;
	private Date reg_date;
	private int anonymous;
	
	@Override
	public String toString() {
		return "ReplyVO [comment_num=" + comment_num + ", post_num=" + post_num + ", mem_num=" + mem_num + ", content="
				+ content + ", reg_date=" + reg_date + ", anonymous=" + anonymous + "]";
	}
	public int getComment_num() {
		return comment_num;
	}
	public void setComment_num(int comment_num) {
		this.comment_num = comment_num;
	}
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public int getAnonymous() {
		return anonymous;
	}
	public void setAnonymous(int anonymous) {
		this.anonymous = anonymous;
	}
}
