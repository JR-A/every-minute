package kr.spring.board.freeboard.vo;

import java.io.IOException;
import java.sql.Date;
import java.util.Arrays;

import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

public class FreeBoardVO {

	private int post_num;
	private int mem_num;
	@NotEmpty
	private String title;
	@NotEmpty
	private String content;
	private Date reg_date;
	private Date modify_date;
	private byte[] uploadfile;
	private String filename;
	private int anonumous;
	private String id;
	//이미지 업로드 파일 처리
	public void setUpload(MultipartFile upload)throws IOException{
		//MultipartFile -> byte[] 반환(byte배열로 변환)
		setUploadfile(upload.getBytes());
		//파일명 구하기
		setFilename(upload.getOriginalFilename());
	}
	
	@Override
	public String toString() {
		return "FreeBoardVO [post_num=" + post_num + ", mem_num=" + mem_num + ", title=" + title + ", content="
				+ content + ", reg_date=" + reg_date + ", modify_date=" + modify_date + ", filename=" + filename
				+ ", anonumous=" + anonumous + ", id=" + id + "]";
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
	public int getAnonumous() {
		return anonumous;
	}
	public void setAnonumous(int anonumous) {
		this.anonumous = anonumous;
	}
	
	
}

