package kr.spring.member.dao;

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
	
	@Select("SELECT m.mem_num,m.id,m.auth,d.passwd FROM member_detail d RIGHT JOIN member m ON d.mem_num=m.mem_num WHERE m.id=#{id}")
	public MemberVO selectCheckMember(String id);
	
	@Select("SELECT * FROM member m JOIN member_detail d ON m.mem_num=d.mem_num WHERE m.mem_num=#{mem_num}")
	public MemberVO selectMember(int mem_num);
	
	@Update("UPDATE member_detail SET nickname=#{nickname},email=#{email},modify_date=SYSDATE WHERE mem_num=#{mem_num}")
	public void updateMember(MemberVO member);
	
	@Update("UPDATE spmember_detail SET passwd=#{passwd} WHERE mem_num=#{mem_num}")
	public void updatePassword(MemberVO member);
	
	@Update("UPDATE member SET auth=0 WHERE mem_num=#{mem_num}")
	public void deleteMember(Integer mem_num);
	
	@Delete("DELETE FROM member_detail WHERE mem_num=#{mem_num}")
	public void deleteMember_detail(Integer mem_num);
	
	//프로필 이미지 업데이트
	@Update("UPDATE member_detail SET photo=#{photo},photoname=#{photoname} WHERE mem_num=#{mem_num}")
	public void updateProfile(MemberVO member);

	//프로필 이미지 가져오기
	@Select("Select photo FROM member_detail where mem_num=#{mem_num}")
	public MemberVO getPhoto();
}
