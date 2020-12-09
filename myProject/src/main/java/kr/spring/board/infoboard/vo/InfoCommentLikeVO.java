package kr.spring.board.infoboard.vo;

public class InfoCommentLikeVO {
	private int comment_num;//댓글 번호
	private int mem_num;//멤버 번호
	private int commentLike_cnt; // 댓글 좋아요 수
	
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
	public int getCommentLike_cnt() {
		return commentLike_cnt;
	}
	public void setCommentLike_cnt(int commentLike_cnt) {
		this.commentLike_cnt = commentLike_cnt;
	}
	
	@Override
	public String toString() {
		return "InfoCommentLikeVO [comment_num=" + comment_num + ", mem_num=" + mem_num + ", commentLike_cnt="
				+ commentLike_cnt + "]";
	}
	
}
