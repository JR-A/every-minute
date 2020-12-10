package kr.spring.board.customboard.vo;

public class CustomLikeVO {
	private int like_num; //추천 번호
	private int post_num; //게시글 번호
	private int comment_num; //댓글 번호
	private int mem_num; //좋아요 누른 회원 번호
	
	private int like_post_cnt; //게시글에 달린 총 추천 수
	private int post_writer_num; //게시글 작성자 번호
	private int like_comm_cnt; //댓글에 달린 총 추천 수
	private int comment_writer_num; //댓글 작성자 번호
	
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
	public int getLike_post_cnt() {
		return like_post_cnt;
	}
	public void setLike_post_cnt(int like_post_cnt) {
		this.like_post_cnt = like_post_cnt;
	}
	public int getPost_writer_num() {
		return post_writer_num;
	}
	public void setPost_writer_num(int post_writer_num) {
		this.post_writer_num = post_writer_num;
	}
	public int getLike_comment_cnt() {
		return like_comm_cnt;
	}
	public void setLike_comment_cnt(int like_comment_cnt) {
		this.like_comm_cnt = like_comment_cnt;
	}
	public int getComment_writer_num() {
		return comment_writer_num;
	}
	public void setComment_writer_num(int comment_writer_num) {
		this.comment_writer_num = comment_writer_num;
	}
	
}
