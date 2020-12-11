package kr.spring.board.infoboard.vo;

public class InfoReplyVO {
	
	/*CREATE TABLE infoBoard_Comment(
			comment_num NUMBER NOT NULL,
			post_num NUMBER NOT NULL,
			mem_num NUMBER NOT NULL,
			content CLOB NOT NULL,
			reg_date  DATE DEFAULT SYSDATE NOT NULL,
			anonymous NUMBER(1) NOT NULL,(0:미허용 1:허용) 

			CONSTRAINT infocomment_pk PRIMARY KEY(comment_num),
			    CONSTRAINT infocomment_fk FOREIGN KEY (post_num) REFERENCES InfoBoard (post_num),
				CONSTRAINT infocomment_fk2 FOREIGN KEY (mem_num) REFERENCES Member (mem_num)
				
			);
			CREATE sequence info_Reply_seq START WITH 1 INCREMENT BY 1 MAXVALUE 10000;
*/
			
	private int comment_num;
	private int post_num;
	private int mem_num;
	private String content;
	private String reg_date;
	private int anonymous;
	private String id;
	private String photoname;
	private int like_cntR;
	//내가 쓴댓글 목록에서 글 제목 받아오려고 넣음
	private String title;
	
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
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public int getAnonymous() {
		return anonymous;
	}
	public void setAnonymous(int anonymous) {
		this.anonymous = anonymous;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPhotoname() {
		return photoname;
	}
	public void setPhotoname(String photoname) {
		this.photoname = photoname;
	}
	public int getLike_cntR() {
		return like_cntR;
	}
	public void setLike_cntR(int like_cntR) {
		this.like_cntR = like_cntR;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Override
	public String toString() {
		return "InfoReplyVO [comment_num=" + comment_num + ", post_num=" + post_num + ", mem_num=" + mem_num
				+ ", content=" + content + ", reg_date=" + reg_date + ", anonymous=" + anonymous + ", id=" + id
				+ ", photoname=" + photoname + ", like_cntR=" + like_cntR + ", title=" + title + "]";
	}
	
}
