package kr.spring.board.infoboard.vo;

public class InfoLikeVO {
	private int post_num;//게시판 번호
	private int mem_num;//멤버 번호
	private int like_cnt; // 좋아요 수
	
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
	
	@Override
	public String toString() {
		return "InfoLikeVO [post_num=" + post_num + ", mem_num=" + mem_num + ", like_cnt=" + like_cnt + "]";
	}
	

}
