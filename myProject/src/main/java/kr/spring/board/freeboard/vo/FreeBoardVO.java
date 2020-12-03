package kr.spring.board.freeboard.vo;

import java.io.IOException;
import java.sql.Date;
import java.util.Arrays;
import java.util.Map;

import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

public class FreeBoardVO {

	private int post_num;
	private int mem_num;
	@NotEmpty
	private String title;
	@NotEmpty
	private String content;
	private String reg_date;
	private String modify_date;
	private byte[] uploadfile;
	private String filename;
	private int anonymous;
	private String id;
	private String photoname;
	private int reply_cnt; //댓글 갯수
	
	//이미지 업로드 파일 처리
	public void setUpload(MultipartFile upload)throws IOException{
		//MultipartFile -> byte[] 반환(byte배열로 변환)
		setUploadfile(upload.getBytes());
		//파일명 구하기
		setFilename(upload.getOriginalFilename());
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

	public String getPhotoname() {
		return photoname;
	}

	public void setPhotoname(String photoname) {
		this.photoname = photoname;
	}
	
	public int getReply_cnt() {
		return reply_cnt;
	}

	public void setReply_cnt(int reply_cnt) {
		this.reply_cnt = reply_cnt;
	}

	@Override
	public String toString() {
		return "FreeBoardVO [post_num=" + post_num + ", mem_num=" + mem_num + ", title=" + title + ", content="
				+ content + ", reg_date=" + reg_date + ", modify_date=" + modify_date + ", filename=" + filename
				+ ", anonymous=" + anonymous + ", id=" + id + ", photoname=" + photoname + ", reply_cnt=" + reply_cnt
				+ "]";
	}
}

