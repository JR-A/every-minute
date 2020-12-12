package kr.spring.board.customboard.vo;

import java.io.IOException;
import java.sql.Date;

import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

public class CustomPostVO {

	private int post_num; //게시판 번호
	private int board_num; //게시글 번호
	private int mem_num; //등록한 회원번호
	@NotEmpty
	private String content; //내용 _clob
	private Date reg_date; //등록일
	private Date modify_date; //수정일
	private byte[] uploadfile; //이미지 파일 _blob
	private String filename; //파일명
	private int anonymous; //익명여부

	private String photoname; //작성자 프로필 사진
	private String title; //게시판 번호
	private String id; //작성자
	private int comment_cnt; //댓글 수
	private int like_cnt; //추천 수
	private int fav_cnt; //즐겨찾기 수
    
	//이미지 업로드 파일 처리
	public void setUpload(MultipartFile upload)throws IOException{
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

	public int getBoard_num() {
		return board_num;
	}

	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}

	public int getMem_num() {
		return mem_num;
	}
	public int getComment_cnt() {
		return comment_cnt;
	}

	public void setComment_cnt(int comment_cnt) {
		this.comment_cnt = comment_cnt;
	}

	public int getLike_cnt() {
		return like_cnt;
	}

	public void setLike_cnt(int like_cnt) {
		this.like_cnt = like_cnt;
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

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	public Date getModify_date() {
		return modify_date;
	}

	public void setModify_date(Date modify_date) {
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getFav_cnt() {
		return fav_cnt;
	}

	public void setFav_cnt(int fav_cnt) {
		this.fav_cnt = fav_cnt;
	}

	public String getPhotoname() {
		return photoname;
	}

	public void setPhotoname(String photoname) {
		this.photoname = photoname;
	}

	@Override
	public String toString() {
		return "CustomPostVO [post_num=" + post_num + ", board_num=" + board_num + ", mem_num=" + mem_num + ", content="
				+ content + ", reg_date=" + reg_date + ", modify_date=" + modify_date + ", filename=" + filename
				+ ", anonymous=" + anonymous + "]";
	}

}
