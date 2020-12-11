package kr.spring.message.vo;

import java.sql.Date;

public class MessageVO {

	private int msg_num;
	private int post_num;
	private int mem_num;
	private int target_mem_num;
	private String content;
	private int msg_check;
	private Date reg_date;
	private String board_which;
	private String id;
    private String target_id;
	private int parent_msg_num;
	private int anonymous;
    
	public int getMsg_num() {
		return msg_num;
	}
	public void setMsg_num(int msg_num) {
		this.msg_num = msg_num;
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
	public int getTarget_mem_num() {
		return target_mem_num;
	}
	public void setTarget_mem_num(int target_mem_num) {
		this.target_mem_num = target_mem_num;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getMsg_check() {
		return msg_check;
	}
	public void setMsg_check(int msg_check) {
		this.msg_check = msg_check;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public String getBoard_which() {
		return board_which;
	}
	public void setBoard_which(String board_which) {
		this.board_which = board_which;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTarget_id() {
		return target_id;
	}
	public void setTarget_id(String target_id) {
		this.target_id = target_id;
	}
	
	public int getParent_msg_num() {
		return parent_msg_num;
	}
	public void setParent_msg_num(int parent_msg_num) {
		this.parent_msg_num = parent_msg_num;
	}
	public int getAnonymous() {
		return anonymous;
	}
	public void setAnonymous(int anonymous) {
		this.anonymous = anonymous;
	}
	@Override
	public String toString() {
		return "MessageVO [msg_num=" + msg_num + ", post_num=" + post_num + ", mem_num=" + mem_num + ", target_mem_num="
				+ target_mem_num + ", content=" + content + ", msg_check=" + msg_check + ", reg_date=" + reg_date
				+ ", board_which=" + board_which + ", id=" + id + ", target_id=" + target_id + ", parent_msg_num="
				+ parent_msg_num + ", anonymous=" + anonymous + "]";
	}
}
