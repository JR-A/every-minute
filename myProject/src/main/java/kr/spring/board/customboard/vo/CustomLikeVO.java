package kr.spring.board.customboard.vo;

public class CustomLikeVO {
	private int like_num; //추천 번호
	private int post_num; //게시글 번호
	private int comment_num; //댓글 번호
	private int mem_num; //좋아요 누른 회원 번호
	
	private int like_cnt; //게시글에 달린 총 추천 수
	private int writer_num; //게시글 작성자 번호
	
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
	public int getLike_cnt() {
		return like_cnt;
	}
	public void setLike_cnt(int like_cnt) {
		this.like_cnt = like_cnt;
	}
	public int getWriter_num() {
		return writer_num;
	}
	public void setWriter_num(int writer_num) {
		this.writer_num = writer_num;
	}
	
}
