package kr.spring.board.customboard.vo;

import javax.validation.constraints.NotEmpty;

public class CustomBoardVO {

	private int board_num; //게시판 번호
	private int mem_num; //생성한 회원번호
	@NotEmpty
	private String title; 
	private String subtitle; //게시판 설명
	private int type; //형식
	private int anonymous; //익명여부
	
	public int getBoard_num() {
		return board_num;
	}
	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getAnonymous() {
		return anonymous;
	}
	public void setAnonymous(int anonymous) {
		this.anonymous = anonymous;
	}
	
	@Override
	public String toString() {
		return "CustomBoardVO [board_num=" + board_num + ", mem_num=" + mem_num + ", title=" + title + ", subtitle="
				+ subtitle + ", type=" + type + ", anonymous=" + anonymous + "]";
	}
	
}
