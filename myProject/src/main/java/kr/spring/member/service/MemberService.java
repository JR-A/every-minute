package kr.spring.member.service;

import java.util.List;
import java.util.Map;

import kr.spring.board.customboard.vo.CustomPostVO;
import kr.spring.board.freeboard.vo.FreeBoardVO;
import kr.spring.board.freeboard.vo.FreeReplyVO;
import kr.spring.board.infoboard.vo.InfoBoardVO;
import kr.spring.board.infoboard.vo.InfoReplyVO;
import kr.spring.member.vo.MemberVO;

//트랜잭션 처리하므로 작업단위를 묶음
public interface MemberService {
	public void insertMember(MemberVO member);
	public MemberVO selectCheckMember(String id);
	public MemberVO selectMember(int mem_num);
	public MemberVO selectCheckEmail(String email);
	public MemberVO selectCheckNickname(String nickname);
	public void updateMember(MemberVO member);
	public void updatePassword(MemberVO member);
	public void deleteMember(Integer mem_num);
	public void updateProfile(MemberVO member);
	public MemberVO getPhoto(int mem_num);
	public void updateAuthKey(Map<String,String> map);
	public void updateAuthStatus(Map<String,String> map);
	public String selectAuth(String id);
	public void changeNickname(MemberVO member);
	public void emailUpdate(Map<String,String> map);
	public MemberVO findId(MemberVO member);
	public Integer findMem_num(MemberVO member);
	public void changePasswd(Map<String,String> map);
	public void resetPhoto(MemberVO memberVO);
	public MemberVO selectCheckMember_num(int mem_num);
	public List<FreeBoardVO> myFreeselectList(Map<String,Object> map);
	public int myFreeselectRowCount(Map<String,Object> map);
	public List<InfoBoardVO> myInfoselectList(Map<String,Object> map);
	public int myInfoselectRowCount(Map<String,Object> map);
	public List<CustomPostVO> myCustomselectPostList(Map<String, Object> map); 
	public int myCustomselectRowCount(Map<String, Object> map);
	public int myFreeCommentSelectRowCount(Map<String, Object> map);
	public List<FreeReplyVO> selectFreeWritedListReply(Map<String, Object> map);
	public int myInfoCommentSelectRowCount(Map<String, Object> map);
	public List<InfoReplyVO> selectInfoWritedListReply(Map<String, Object> map);
}
