package kr.spring.member.vo;

import java.io.IOException;
import java.sql.Date;
import java.util.Arrays;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class MemberVO {
	
	private int mem_num;
	//정규표현식을 이용해서 유효성 체크
	@Pattern(regexp="^[a-zA-Z0-9]{4,15}$")
	private String id;
	private int auth;
	@Size(min=4,max=15)
	private String passwd;
	@Email
	@NotEmpty
	private String email;
	@NotEmpty
	@Size(min=2,max=10)
	private String nickname;
	
	private String major;
	private String stu_num;
	
	private String zipcode;
	private String address1;
	private String address2;
	
	private Date reg_date;
	private Date modify_date;
	
	private byte[] photo;
	private String photoname;
	private String authKey;
	private int authstatus;
	//비밀번호 변경시 현재 비밀번호를 저장하는 용도로 사용
	@Size(min=4,max=10)
	private String passwdCk;

	public void setUpload(MultipartFile upload) throws IOException{
		//MultipartFile -> byte[]
		setPhoto(upload.getBytes());
		//파일 이름
		setPhotoname(upload.getOriginalFilename());
		}
	
	
	//==============비밀번호 일치 여부 체크============//
	public boolean isCheckedPasswd(String userPasswd) {
		
		if(auth > 1 && passwd.equals(userPasswd)) {
			return true;
		}
		return false;
	}


	
	//Getters and Setters

	public int getMem_num() {
		return mem_num;
	}


	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public int getAuth() {
		return auth;
	}


	public void setAuth(int auth) {
		this.auth = auth;
	}


	public String getPasswd() {
		return passwd;
	}


	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getNickname() {
		return nickname;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	public String getMajor() {
		return major;
	}


	public void setMajor(String major) {
		this.major = major;
	}


	public String getStu_num() {
		return stu_num;
	}


	public void setStu_num(String stu_num) {
		this.stu_num = stu_num;
	}


	public String getZipcode() {
		return zipcode;
	}


	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}


	public String getAddress1() {
		return address1;
	}


	public void setAddress1(String address1) {
		this.address1 = address1;
	}


	public String getAddress2() {
		return address2;
	}


	public void setAddress2(String address2) {
		this.address2 = address2;
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


	public byte[] getPhoto() {
		return photo;
	}


	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}


	public String getPhotoname() {
		return photoname;
	}


	public void setPhotoname(String photoname) {
		this.photoname = photoname;
	}


	public String getAuthKey() {
		return authKey;
	}


	public void setAuthKey(String authKey) {
		this.authKey = authKey;
	}


	public String getPasswdCk() {
		return passwdCk;
	}


	public void setPasswdCk(String passwdCk) {
		this.passwdCk = passwdCk;
	}

	
	public int getAuthstatus() {
		return authstatus;
	}


	public void setAuthstatus(int authstatus) {
		this.authstatus = authstatus;
	}


	@Override
	public String toString() {
		return "MemberVO [mem_num=" + mem_num + ", id=" + id + ", auth=" + auth + ", passwd=" + passwd + ", email="
				+ email + ", nickname=" + nickname + ", major=" + major + ", stu_num=" + stu_num + ", zipcode="
				+ zipcode + ", address1=" + address1 + ", address2=" + address2 + ", reg_date=" + reg_date
				+ ", modify_date=" + modify_date + ", photo=" + Arrays.toString(photo) + ", photoname=" + photoname
				+ ", authkey=" + authKey + ", passwdCk=" + passwdCk + "]";
	}




	
}
