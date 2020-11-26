package kr.spring.member.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.member.dao.MemberMapper;
import kr.spring.member.vo.MemberVO;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
	
	//프로퍼티
	@Resource
	private MemberMapper memberMapper;
	
	//여러 작업을 작업 단위로 묶음. 트랜잭션 처리
	@Override
	public void insertMember(MemberVO member) {
		member.setMem_num(memberMapper.selectMem_num());	
		memberMapper.insertMember(member);	
		memberMapper.insertMember_detail(member);
	}

	@Override
	public MemberVO getPhoto(int mem_num) {
		return memberMapper.getPhoto();
	}
	
	@Override
	public MemberVO selectCheckMember(String id) {
		return memberMapper.selectCheckMember(id);
	}

	@Override
	public MemberVO selectMember(int mem_num) {
		return memberMapper.selectMember(mem_num);
	}

	@Override
	public void updateMember(MemberVO member) {
		memberMapper.updateMember(member);
	}

	@Override
	public void updatePassword(MemberVO member) {
		memberMapper.updatePassword(member);
	}

	@Override
	public void deleteMember(Integer mem_num) {
		memberMapper.deleteMember(mem_num);
		memberMapper.deleteMember_detail(mem_num);
	}
	
	@Override
	public void updateProfile(MemberVO member) {
		memberMapper.updateProfile(member);
	}

	@Override
	public void updateAuthKey(Map<String, String> map) {
		memberMapper.updateAuthKey(map);
		
	}

	@Override
	public void updateAuthStatus(Map<String,String> map) {
		memberMapper.updateAuthStatus(map);
		
	}

	@Override
	public MemberVO selectCheckEmail(String email) {
		return memberMapper.selectCheckEmail(email);
		
	}

	@Override
	public MemberVO selectCheckNickname(String nickname) {
		return memberMapper.selectCheckNickname(nickname);
		
	}

	@Override
	public String selectAuth(String id) {
		return memberMapper.selectAuth(id);
	}

	@Override
	public void changeNickname(MemberVO member) {
		 memberMapper.changeNickname(member);
		
	}


	@Override
	public void emailUpdate(Map<String, String> map) {
		memberMapper.emailUpdate(map);
		
	}
	
	
}
