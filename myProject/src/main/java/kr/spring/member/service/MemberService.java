package kr.spring.member.service;

import kr.spring.member.vo.MemberVO;

//트랜잭션 처리하므로 작업단위를 묶음
public interface MemberService {
	public void insertMember(MemberVO member);
	public MemberVO selectCheckMember(String id);
	public MemberVO selectMember(int mem_num);
	public void updateMember(MemberVO member);
	public void updatePassword(MemberVO member);
	public void deleteMember(Integer mem_num);
	public void updateProfile(MemberVO member);
	public MemberVO getPhoto(int mem_num);
}
