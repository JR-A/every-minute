package kr.spring.board.customboard.vo;

public class CustomFavoriteVO {
	
	private int fav_num; //즐겨찾기 번호
	private int post_num; //게시글 번호
	private int mem_num; //즐겨찾기 한 회원번호
	
	private int fav_cnt; //게시글을 즐겨찾기 한 수
	
	public int getFav_num() {
		return fav_num;
	}
	public void setFav_num(int fav_num) {
		this.fav_num = fav_num;
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
	public int getFav_cnt() {
		return fav_cnt;
	}
	public void setFav_cnt(int fav_cnt) {
		this.fav_cnt = fav_cnt;
	}
	
}
