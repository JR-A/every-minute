package kr.spring.board.infoboard.vo;

public class InfoCommentLikeVO {
	private int like_num;
	private int comment_num;
	private int mem_num;
	
	public int getLike_num() {
		return like_num;
	}
	public void setLike_num(int like_num) {
		this.like_num = like_num;
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
	
	@Override
	public String toString() {
		return "InfoCommentLikeVO [like_num=" + like_num + ", comment_num=" + comment_num + ", mem_num=" + mem_num
				+ "]";
	}
	
}
