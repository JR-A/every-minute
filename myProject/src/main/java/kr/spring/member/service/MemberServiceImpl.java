package kr.spring.member.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.board.customboard.vo.CustomPostVO;
import kr.spring.board.freeboard.vo.FreeBoardVO;
import kr.spring.board.freeboard.vo.FreeReplyVO;
import kr.spring.board.infoboard.vo.InfoBoardVO;
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
	
	
	@Override
	public MemberVO findId(MemberVO member) {
		return memberMapper.findId(member);
	}

	@Override
	public Integer findMem_num(MemberVO member) {
		return memberMapper.findMem_num(member);
	}

	@Override
	public void changePasswd(Map<String,String> map) {
		memberMapper.changePasswd(map);
		
	}

	@Override
	public void resetPhoto(MemberVO memberVO) {
		memberMapper.resetPhoto(memberVO);
		
	}

	@Override
	public MemberVO selectCheckMember_num(int mem_num) {
		return memberMapper.selectCheckMember_num(mem_num);
		
	}

	@Override
	public List<FreeBoardVO> myFreeselectList(Map<String, Object> map) {
		return memberMapper.myFreeselectList(map);
	}

	@Override
	public int myFreeselectRowCount(Map<String, Object> map) {
		return memberMapper.myFreeselectRowCount(map);
	}

	@Override
	public List<InfoBoardVO> myInfoselectList(Map<String, Object> map) {
		return memberMapper.myInfoselectList(map);
	}

	@Override
	public int myInfoselectRowCount(Map<String, Object> map) {
		return memberMapper.myInfoselectRowCount(map);
	}

	@Override
	public List<CustomPostVO> myCustomselectPostList(Map<String, Object> map) {
		return memberMapper.myCustomselectPostList(map);
	}

	@Override
	public int myCustomselectRowCount(Map<String, Object> map) {
		return memberMapper.myCustomselectRowCount(map);
	}

	@Override
	public int myFreeCommentSelectRowCount(Map<String, Object> map) {
		return memberMapper.myFreeCommentSelectRowCount(map);
	}

	@Override
	public List<FreeReplyVO> selectFreeWritedListReply(Map<String, Object> map) {
		return memberMapper.selectFreeWritedListReply(map);
	}



}
