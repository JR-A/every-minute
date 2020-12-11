package kr.spring.board.infoboard.vo;

import java.io.IOException;

import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

/*
   post_num NUMBER NOT NULL,
   mem_num NUMBER NOT NULL,
   tag_num NUMBER,
   title VARCHAR2(100) NOT NULL,
   content CLOB NOT NULL,
   reg_date DATE DEFAULT SYSDATE NOT NULL,
   modify_date DATE DEFAULT SYSDATE NOT NULL,
   uploadfile BLOB,
   filename VARCHAR2(100),
    anonymous NUMBER(1) DEFAULT 1 NOT NULL,  /* (0:미허용 1:허용) 
    */

public class InfoBoardVO {
	private int post_num;//게시판 번호
	private int mem_num;//멤버 번호
	private int tag_num;//태그 넘버
	@NotEmpty
	private String title;//제목
	@NotEmpty
	private String content; //내용
	private String reg_date; //등록일
	private String modify_date; //수정일
	private byte[] uploadfile; //이미지 파일 toString을 추가할때에는 byte[]배열은 빼고 생성해야합니다. 엄청난 오류발생 ㄷㄷ
	private String filename; //파일 이름
	private int anonymous; //익명여부
	private String id; //회원 아이디
	private int reply_cnt; // 댓글 수
	private int like_cnt; // 좋아요 수
	private int like_cntR;//댓글 추천 갯수
	private int target_mem_num; //게시판 신고/추천 당한 멤버 번호
	
	//이미지 업로드 파일
	public void setUpload(MultipartFile upload) throws IOException {
		//MultipartFile -> byte[] 반환
		setUploadfile(upload.getBytes());
		//파일명 구하기
		setFilename(upload.getOriginalFilename());
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

	public int getTag_num() {
		return tag_num;
	}

	public void setTag_num(int tag_num) {
		this.tag_num = tag_num;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getModify_date() {
		return modify_date;
	}

	public void setModify_date(String modify_date) {
		this.modify_date = modify_date;
	}

	public byte[] getUploadfile() {
		return uploadfile;
	}

	public void setUploadfile(byte[] uploadfile) {
		this.uploadfile = uploadfile;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
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

	public int getReply_cnt() {
		return reply_cnt;
	}

	public void setReply_cnt(int reply_cnt) {
		this.reply_cnt = reply_cnt;
	}

	public int getLike_cnt() {
		return like_cnt;
	}

	public void setLike_cnt(int like_cnt) {
		this.like_cnt = like_cnt;
	}

	public int getLike_cntR() {
		return like_cntR;
	}

	public void setLike_cntR(int like_cntR) {
		this.like_cntR = like_cntR;
	}

	public int getTarget_mem_num() {
		return target_mem_num;
	}

	public void setTarget_mem_num(int target_mem_num) {
		this.target_mem_num = target_mem_num;
	}

	@Override
	public String toString() {
		return "InfoBoardVO [post_num=" + post_num + ", mem_num=" + mem_num + ", tag_num=" + tag_num + ", title="
				+ title + ", content=" + content + ", reg_date=" + reg_date + ", modify_date=" + modify_date
				+ ", filename=" + filename + ", anonymous=" + anonymous + ", id=" + id + ", reply_cnt=" + reply_cnt
				+ ", like_cnt=" + like_cnt + ", like_cntR=" + like_cntR + ", target_mem_num=" + target_mem_num + "]";
	}
	


	
}