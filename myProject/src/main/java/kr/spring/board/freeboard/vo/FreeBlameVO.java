package kr.spring.board.freeboard.vo;

public class FreeBlameVO {
	private int blame_num; //신고 번호
	private int post_num; //게시글 번호
	private int comment_num; //댓글 번호
	private int mem_num; //신고한 회원 번호
	
	public int getBlame_num() {
		return blame_num;
	}
	public void setBlame_num(int blame_num) {
		this.blame_num = blame_num;
	}
	public int getPost_num() {
		return post_num;
	}
	public void setPost_num(int post_num) {
		this.post_num = post_num;
	}
	public int getComment_num() {
		return comment_num;
	}
	public void setComment_num(int comment_num) {
		this.comment_num = comment_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	
	
}
