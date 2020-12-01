package kr.spring.member.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.member.vo.MemberVO;

public interface MemberMapper {
	@Select("SELECT member_seq.nextval FROM dual")
	public int selectMem_num();
	
	@Insert("INSERT INTO member (mem_num, id) VALUES (#{mem_num},#{id})")
	public void insertMember(MemberVO member);
	
	@Insert("INSERT INTO member_detail (mem_num,passwd,email,nickname,major,stu_num,zipcode,address1,address2) VALUES (#{mem_num},#{passwd},#{email},#{nickname},#{major},#{stu_num},#{zipcode},#{address1},#{address2})")
	public void insertMember_detail(MemberVO member);
	
	// 정지or탈퇴회원 체크위해서 auth 넘버 가져오기
	@Select("SELECT auth FROM member WHERE id=#{id}")
	public String selectAuth(String id);
	
	@Select("SELECT m.mem_num,m.id,m.auth,d.passwd,d.nickname,d.authstatus,d.stu_num,d.major,d.email,d.photoname FROM member_detail d RIGHT JOIN member m ON d.mem_num=m.mem_num WHERE m.id=#{id}")
	public MemberVO selectCheckMember(String id);
	
	@Select("SELECT m.mem_num,m.id,m.auth,d.passwd,d.nickname,d.authstatus,d.stu_num,d.major,d.email,d.photoname FROM member_detail d RIGHT JOIN member m ON d.mem_num=m.mem_num WHERE m.mem_num=#{mem_num}")
	public MemberVO selectCheckMember_num(int mem_num);
	
	@Select("SELECT mem_num FROM member_detail WHERE email=#{email}")
	public MemberVO selectCheckEmail(String email);
	
	@Select("SELECT mem_num FROM member_detail WHERE nickname=#{nickname}")
	public MemberVO selectCheckNickname(String nickname);
	
	@Select("SELECT * FROM member m JOIN member_detail d ON m.mem_num=d.mem_num WHERE m.mem_num=#{mem_num}")
	public MemberVO selectMember(int mem_num);
	
	@Update("UPDATE member_detail SET nickname=#{nickname},email=#{email},modify_date=SYSDATE WHERE mem_num=#{mem_num}")
	public void updateMember(MemberVO member);
	
	@Update("UPDATE pmember_detail SET passwd=#{passwd} WHERE mem_num=#{mem_num}")
	public void updatePassword(MemberVO member);
	
	@Update("UPDATE member SET auth=0 WHERE mem_num=#{mem_num}")
	public void deleteMember(Integer mem_num);
	
	//비밀번호 변경
	@Update("UPDATE member_detail SET passwd=#{passwd} WHERE mem_num=#{mem_num}")
	public void changePasswd(Map<String,String> map);
	
	//닉네임 변경 
	@Update("UPDATE Member_detail SET nickname=#{nickname} WHERE mem_num=#{mem_num}")
	public void changeNickname(MemberVO member);
	
	@Delete("DELETE FROM member_detail WHERE mem_num=#{mem_num}")
	public void deleteMember_detail(Integer mem_num);
		
	//이메일 변경 업데이트
	@Update("UPDATE member_detail SET email=#{email} where authkey=#{authKey}")
	public void emailUpdate(Map<String,String> map);
	
	//프로필 이미지 업데이트
	@Update("UPDATE member_detail SET photo=#{photo},photoname=#{photoname} WHERE mem_num=#{mem_num}")
	public void updateProfile(MemberVO member);

	//프로필 이미지 가져오기
	@Select("Select photo FROM member_detail where mem_num=#{mem_num}")
	public MemberVO getPhoto();
	
	//프로필 이미지 기본값으로
	@Update("UPDATE member_detail SET photo=null,photoname=null WHERE mem_num=#{mem_num}")
	public void resetPhoto(MemberVO memberVO);
	
	//인증키 저장 
	@Update("UPDATE member_detail SET authkey=#{authKey} WHERE email=#{email}")
	public void updateAuthKey(Map<String,String> map);
	
	//이메일 인증성공시 authstatus  1 로 변환해서 로그인
	@Update("UPDATE member_detail SET authstatus=1  WHERE email=#{email} AND authkey=#{authKey}")
	public void updateAuthStatus(Map<String,String> map);

	//아이디/비밀번호찾기 이메일로 아이디,패스워드를 불러옴
	@Select("SELECT id,passwd FROM member_detail d join member m ON d.mem_num=m.mem_num WHERE d.email=#{email}")
	public MemberVO findId(MemberVO member); 

	//이메일로 mem_num 찾기
	@Select("SELECT mem_num from member_detail WHERE email=#{email}")
	public Integer findMem_num(MemberVO member);
}
