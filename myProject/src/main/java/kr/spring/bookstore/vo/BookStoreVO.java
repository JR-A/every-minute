package kr.spring.bookstore.vo;

import java.io.IOException;
import java.sql.Date;

import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

public class BookStoreVO {
	private int bs_num;
	private int mem_num;
	private int bs_selling_price;
	private String bs_comment;
	private String bs_condition;
	private String bs_method;
	private String bs_address;
	private Date reg_date;
	private Date modify_date;
	private byte[] uploadfile;
	private String filename;
	private int bs_complete;
	private String id;
	private String isbn;
	
	public void setUpload(MultipartFile upload) throws IOException {
		setUploadfile(upload.getBytes());
		setFilename(upload.getOriginalFilename());
	}
	
	public int getBs_num() {
		return bs_num;
	}
	public void setBs_num(int bs_num) {
		this.bs_num = bs_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public int getBs_selling_price() {
		return bs_selling_price;
	}
	public void setBs_selling_price(int bs_selling_price) {
		this.bs_selling_price = bs_selling_price;
	}
	public String getBs_comment() {
		return bs_comment;
	}
	public void setBs_comment(String bs_comment) {
		this.bs_comment = bs_comment;
	}
	public String getBs_condition() {
		return bs_condition;
	}
	public void setBs_condition(String bs_condition) {
		this.bs_condition = bs_condition;
	}
	public String getBs_method() {
		return bs_method;
	}
	public void setBs_method(String bs_method) {
		this.bs_method = bs_method;
	}
	public String getBs_address() {
		return bs_address;
	}
	public void setBs_address(String bs_address) {
		this.bs_address = bs_address;
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
	public int getBs_complete() {
		return bs_complete;
	}
	public void setBs_complete(int bs_complete) {
		this.bs_complete = bs_complete;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	@Override
	public String toString() {
		return "BookStoreVO [bs_num=" + bs_num + ", mem_num=" + mem_num + ", bs_selling_price=" + bs_selling_price
				+ ", bs_comment=" + bs_comment + ", bs_condition=" + bs_condition + ", bs_method=" + bs_method
				+ ", bs_address=" + bs_address + ", reg_date=" + reg_date + ", modify_date=" + modify_date
				+ ", filename=" + filename + ", bs_complete=" + bs_complete + ", id=" + id + ", isbn=" + isbn + "]";
	}

}
